package com.bhc.startstop.webservice.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bhc.soa.web.contract.entity.user.v1.BadPassword;
import com.bhc.soa.web.contract.entity.user.v1.EmailNotFound;
import com.bhc.soa.web.contract.entity.user.v1.ProcessFailed;
import com.bhc.soa.web.contract.entity.user.v1.UserServicesV1;
import com.bhc.soa.web.contract.entity.user.v1.UsernameExists;
import com.bhc.soa.web.types.common.v1.RequestContextType;
import com.bhc.soa.web.types.messages.user.v1.AddCisAccountRequestType;
import com.bhc.soa.web.types.messages.user.v1.BhcUserType;
import com.bhc.soa.web.types.messages.user.v1.CreateRequestType;
import com.bhc.soa.web.types.messages.user.v1.CreateResponseType;
import com.bhc.soa.web.types.messages.user.v1.FindRequestType;
import com.bhc.soa.web.types.messages.user.v1.FindResponseType;
import com.bhc.soa.web.types.messages.user.v1.SendUsernameEmailRequestType;
import com.bhc.soa.web.types.messages.user.v1.UserAccountType;
import com.bhc.startstop.exceptions.BadPasswordException;
import com.bhc.startstop.exceptions.DuplicateUserNameException;
import com.bhc.startstop.exceptions.WebProfileCreationException;
import com.bhc.startstop.exceptions.WebServiceException;
import com.bhc.startstop.user.exceptions.EmailNotFoundException;
import com.bhc.startstop.user.service.WebServiceHelper;
import com.bhc.startstop.util.ApplicationEnvironment;
import com.bhc.startstop.util.SoapHeaderInfo;
import com.bhc.startstop.web.model.Person;
import com.bhc.startstop.web.model.PrimaryPerson;
import com.bhc.startstop.webservice.UserWebService;

@Service
public class UserWebServiceImpl implements UserWebService {

    //@Value("${application.enable.company.change}")
    //private String enableCompanyChange;

    @Value("${auth.ldap.external.base.dn}")
    private String base;

    @Value("${auth.security.user.type}")
    private String userType;

    @Value("${user.service.ad.data.type}")
    private String adDataType;

    @Value("${user.service.dbad.data.type}")
    private String dbadDataType;

    @Value("${user.security.migration.status}")
    private String migrationStatus;

    @Value("${user.security.access.role}")
    private String role;

    private String creatorId = "REGISTRATION";

    private ApplicationEnvironment appEnvironment;
    private UserServicesV1 userServicesV1;
    private static final Logger log = LoggerFactory.getLogger(UserWebServiceImpl.class);

    public UserWebServiceImpl(ApplicationEnvironment applicationEnviornment, UserServicesV1 userServicesV1) {
        this.appEnvironment = applicationEnviornment;
        this.userServicesV1 = userServicesV1;
    }

    private FindRequestType generateFindRequestType(String userName) {

        FindRequestType request = new FindRequestType();
        request.setBase(base);
        request.setRequestContext(SoapHeaderInfo.createRequestContext());
        request.setType(dbadDataType);
        request.setUserName(userName);
        request.setUserType(userType);
        request.setLoginHistoryRequested(false);
        return request;
    }

    @Override
    public PrimaryPerson getLoggedInUserDetails(String userName) {

        PrimaryPerson profile = new PrimaryPerson();

        FindRequestType request = generateFindRequestType(userName);
        try {

            FindResponseType response = userServicesV1.find(request);
            if (StringUtils.isNotEmpty(response.getUser().getBusinessName())) {
                profile.setBusinessName(response.getUser().getBusinessName());
            } /* else {
                 profile.setBusinessName("");
              }*/
            profile.setEmail(response.getUser().getEmailAddress());
            // profile.setEnableCompanyChange(enableCompanyChange);
            profile.setFirstName(response.getUser().getFirstName());
            profile.setLastName(response.getUser().getLastName());
            profile.setPrimaryPhoneNumber(response.getUser().getPrimaryPhone());
            // set account numbers
            for (UserAccountType account : response.getUser().getAccountNbrs()) {
                profile.addCisAccountNumber(account.getCisAccountNbr());
            }
            /*if (response.getUser().getCompanyId() != null) {
                profile.setCompanyId(response.getUser().getCompanyId().toString());
            }*/

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        return profile;

    }

    @Override
    public void create(String username, String password, Person person)
            throws DuplicateUserNameException, BadPasswordException, WebProfileCreationException {

        CreateRequestType request = new CreateRequestType();
        RequestContextType context = SoapHeaderInfo.createRequestContext();
        BhcUserType user = new BhcUserType();
        user.setUserName(username);
        user.setFirstName(person.getFirstName());
        user.setLastName(person.getLastName());
        user.setBusinessName(person.getBusinessName());
        user.setEmailAddress(person.getEmail());
        user.setPrimaryPhone(person.getPrimaryPhoneNumberUnformatted());
        user.setUserType(userType);
        user.getGroups().add(role);
        user.setCompanyId(appEnvironment.getCompanyId());

        request.setRequestContext(context);
        request.setUser(user);
        request.setPassword(password);
        request.setDataType(dbadDataType);
        request.setType(dbadDataType);
        request.setMigrationStatus(migrationStatus);
        try {
            log.debug("Attempting to create profile " + request.getUser().getUserName());
            CreateResponseType response = userServicesV1.create(request);
            log.debug("Web Profile " + username + " created.  Status=" + response.getStatus());
        } catch (UsernameExists e) {
            throw new DuplicateUserNameException(e);
        } catch (BadPassword e) {
            throw new BadPasswordException(e);
        } catch (Exception e) {
            throw new WebProfileCreationException(e);
        }
    }

    /*
    @Override
    @CacheEvict(value = "account", allEntries = true)
    public boolean updateUser(Profile profile, String userName) {
    
        UpdateRequestType request = generateUpdateRequestType(profile, userName);
    
        try {
    
            UpdateResponseType response = userServicesV1.update(request);
        } catch (Exception e) {
    
            throw new RuntimeException(e);
        }
        return true;
    }
    
    private UpdateRequestType generateUpdateRequestType(Profile profile, String userName) {
    
        UpdateRequestType request = new UpdateRequestType();
        BhcUserType bhcUserType = new BhcUserType();
        if (profile.getBusinessName() != null && !profile.getBusinessName().isEmpty()) {
            bhcUserType.setBusinessName(profile.getBusinessName());
        }
        if (profile.getCompanyId() != null && !profile.getCompanyId().isEmpty()) {
            bhcUserType.setCompanyId(Integer.parseInt(profile.getCompanyId()));
        }
        bhcUserType.setEmailAddress(profile.getEmail());
        bhcUserType.setFirstName(profile.getFirstName());
        bhcUserType.setLastName(profile.getLastName());
        bhcUserType.setPrimaryPhone(getCompletePhone(profile.getPhone1(), profile.getPhone2(), profile.getPhone3()));
        request.setRequestContext(WebServiceHelper.createRequestContext());
        request.setType(dbadDataType);
        bhcUserType.setUserName(userName);
        bhcUserType.setUserType(userType);
        request.setUser(bhcUserType);
        request.setIsGroupChanged(Boolean.FALSE);
        request.setDataType("DBAD");
    
        return request;
    }
    
    protected String getCompletePhone(String phone1, String phone2, String phone3) {
    
        String phone = null;
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isBlank(phone1) && !StringUtils.isBlank(phone2) && !StringUtils.isBlank(phone3)) {
            sb.append(phone1);
            sb.append(phone2);
            sb.append(phone3);
            phone = sb.toString();
        } else {
            phone = "";
        }
        return phone;
    }
    
    @Override
    public boolean deleteUser(String userName) {
    
        DeleteRequestType request = generateDeleteRequestType(userName);
        try {
            DeleteResponseType response = userServicesV1.delete(request);
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    
    private DeleteRequestType generateDeleteRequestType(String userName) {
    
        DeleteRequestType request = new DeleteRequestType();
        request.setRequestContext(WebServiceHelper.createRequestContext());
        request.setType(dbadDataType);
        request.setUid(request.getRequestContext().getRequestUuid());
        request.setUserName(userName);
        request.setUserType(userType);
        return request;
    }
    */

    public void setBase(String base) {

        this.base = base;
    }

    public void setUserType(String userType) {

        this.userType = userType;
    }

    public void setAdDataType(String adDataType) {

        this.adDataType = adDataType;
    }

    public void setDbadDataType(String dbadDataType) {

        this.dbadDataType = dbadDataType;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
    @Override
    public void sendUsernamesEmail(String email) throws EmailNotFoundException {
    
        SendUsernameEmailRequestType request = new SendUsernameEmailRequestType();
        request.setEmail(email);
        request.setUserType(userType);
        request.setRequestContext(WebServiceHelper.createRequestContext());
        try {
            userServicesV1.sendUsernameEmail(request);
        } catch (ProcessFailed e) {
            throw new RuntimeException();
        } catch (EmailNotFound e) {
            throw new EmailNotFoundException();
        }
    
    }
    
    /*
    @Override
    @CacheEvict(value = "account", key = "#accountNumber")
    public void removeAccount(String username, long accountNumber) {
    
        RemoveCisAccountRequestType request = new RemoveCisAccountRequestType();
        request.setRequestContext(WebServiceHelper.createRequestContext());
        request.setAccountNumber(String.valueOf(accountNumber));
        request.setUserType(userType);
        request.setUserName(username);
        try {
            userServicesV1.removeCisAccount(request);
        } catch (ProcessFailed e) {
            throw new RuntimeException();
        }
    
    }
    
    @Override
    @CacheEvict(value = "account", key = "#account.accountNumber")
    public void addAccount(int companyId, String username, ManageAccountsForm account)
            throws InvalidCisAccountException {
    
        AddCisAccountRequestType request = new AddCisAccountRequestType();
        request.setAccountNumber(account.getAccountNumber());
        request.setUserName(username);
        request.setUserType(userType);
        request.setCreatorId(creatorId);
        request.setRequestContext(WebServiceHelper.createRequestContext());
        try {
            accountWebService.validateAccount(companyId, account);
        } catch (com.bhc.soa.web.contract.entity.customer.account.v1.ProcessFailed pf) {
            String message = "";
            switch (pf.getFaultInfo().getMessageId()) {
            case 100:
                message = "We could not find the Account Number you entered in our system.";
                break;
            case 101:
                message = "The name you entered does not match the name of the primary person on the account.";
                break;
            case 102:
                message = "The phone number you entered does not match the primary phone number on the account.";
                break;
            default:
                message = "This account can not be accessed online";
                break;
            }
            throw new InvalidCisAccountException(message);
    
        }
        try {
            userServicesV1.addCisAccount(request);
        } catch (ProcessFailed e) {
            throw new RuntimeException();
        }
    
    } */

    @Override
    public void addAccount(int companyId, String username, Long accountNumber) throws WebServiceException {

        AddCisAccountRequestType request = new AddCisAccountRequestType();
        request.setAccountNumber(accountNumber.toString());
        request.setUserName(username);
        request.setUserType(userType);
        request.setCreatorId(creatorId);
        request.setRequestContext(SoapHeaderInfo.createRequestContext());
        try {
            userServicesV1.addCisAccount(request);
        } catch (Exception e) {
            log.error("Unable to link account " + accountNumber + " to user " + username);
            throw new WebServiceException(e);
        }

    }
}
