package com.bhc.startstop.webservice;

import com.bhc.startstop.exceptions.BadPasswordException;
import com.bhc.startstop.exceptions.DuplicateUserNameException;
import com.bhc.startstop.exceptions.WebProfileCreationException;
import com.bhc.startstop.exceptions.WebServiceException;
import com.bhc.startstop.user.exceptions.EmailNotFoundException;
import com.bhc.startstop.web.model.Person;
import com.bhc.startstop.web.model.PrimaryPerson;

public interface UserWebService {
    public PrimaryPerson getLoggedInUserDetails(String userName);
    public void create(String username, String password, Person person)
            throws DuplicateUserNameException, BadPasswordException, WebProfileCreationException;
    public void addAccount(int companyId, String username, Long accountNumber) throws WebServiceException;
    
    // for user functions - can be removed when sso is implemented:
    public void sendUsernamesEmail(String email) throws EmailNotFoundException;

}