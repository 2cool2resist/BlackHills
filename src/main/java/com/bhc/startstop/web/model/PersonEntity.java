package com.bhc.startstop.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

// import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.bhc.startstop.util.DateUtils;
import com.bhc.startstop.webservice.model.DebtInfo;

// @Entity
// @Table(name = "START_STOP_PERSON")
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "PERSON_TYPE", discriminatorType = DiscriminatorType.STRING)
@MappedSuperclass
public class PersonEntity implements Serializable, Person {

    private static final long serialVersionUID = 1L;
    private static final String NAME_SEPARATOR = " "; // ", ";

    // cis person types
    private static final String FRAUD_CIS_PERSON_TYPE = "F";
    private static final String DECEASED_CIS_PERSON_TYPE = "D";
    // THIRD_PARTY = "T"
    // EMERGENCY = "M"
    // COMMON OWN = "C"
    // VENDOR = "V"
    // BUILDER = "B"

    // person type values - we are using the same codeset as CIS (PersonWebServiceImpl) but could be anything
    public static final String PRIMARY_PERSON_TYPE = "P";
    public static final String FINANCIAL_PERSON_TYPE = "F";
    public static final String CONTACT_PERSON_TYPE = "C";

    // posid values - must be same as CIS
    public static final String PASS_POSID_CODE = "Y";
    public static final String PENDING_POSID_CODE = "P";
    // public static final String FAIL_POSID_CODE = "N"; //null or other values are treated as fail
    public static final Integer MAX_POSID_ATTEMPTS = 3; // on third attempt, we continue on
    public static final Integer MAX_SSN_MATCH_ATTEMPTS = 3; // on third attempt, we continue on
    
    
    /**
     * NOTE:  when adding new columns, also add them to the copy constructor, toString, and update
     */

    @Id
    @Column(name = "PERSON_ID", updatable = false, nullable = false)
    @GenericGenerator(name = "person_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "START_STOP_PERSON_ID_SEQ"),
            // @Parameter(name= "initial_value", value="1"),
            @Parameter(name = "increment_size", value = "1") })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    private Long id;

    // PERSON_TYPE - mapped as discriminator
    @Column(name = "PERSON_TYPE")
    private String personType;

    @Column(name = "CIS_PERSON_TYPE")
    private String cisType;
    // ACCOUNT_ID - optional foreign key for additional, contacts

    @Column(name = "CIS_PERSON_ID")
    private Long personId;

    @Column(name = "LANDLORD")
    @Type(type = "yes_no")
    private Boolean landlord;

    @Column(name = "POS_ID")
    private String posId;
    @Column(name = "POS_ID_MESSAGE")
    private String posIdMessage;
    @Column(name = "POS_ID_ATTEMPT_COUNT")
    private Integer posIdAttemptCount;
    @Column(name = "TOTAL_ARREARS")
    private BigDecimal utilityDebt;    // utility debt in same loe
    @Column(name = "NAME_PREFIX")
    private String namePrefix;
    @Column(name = "LAST_NAME")
    private String lastName;               // made uppercase
    @Column(name = "FIRST_NAME")
    private String firstName;              // made uppercase
    @Column(name = "MIDDLE_NAME")
    private String middleName;             // made uppercase
    // private String nameSuffix;
    @Column(name = "BUSINESS_NAME")
    private String businessName;           // made uppercase
    
    @Column(name = "BUSINESS_TYPE")
    private String businessType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "START_STOP_CIS_ACCOUNTS", joinColumns = @JoinColumn(name = "PERSON_ID"))
    @Column(name = "CIS_ACCOUNT_NUMBER")
    @Fetch(value = FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Long> cisAccountNumbers;

    @Column(name = "PRIMARY_PHONE_NUMBER")
    private String primaryPhoneNumber;
    //@Column(name = "PRIMARY_PHONE_TYPE")
    //private String primaryPhoneType;
    // SECONDARY_PHONE_NUMBER
    @Column(name = "SECONDARY_PHONE_NUMBER")  // we are using this for mobile number
    private String secondaryPhoneNumber;
    // SECONDARY_PHONE_TYPE
    //@Column(name = "SECONDARY_PHONE_TYPE")
    //private String secondaryPhoneType;
    // @DateTimeFormat(pattern = "MM/dd/yyyy") // allows binding when using @ModelAttribute
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "SSN")
    private String ssn;
    @Column(name = "EIN")
    private String ein;
    @Column(name = "SSN_MATCH_COUNT")
    private Integer ssnMatchCount;
    
    @Transient
    private String ssnMatchError;
    
    @Column(name = "RELATIONSHIP_TYPE")
    private String relationshipType;

    // private String employerName;
    @Column(name = "EMAIL")
    private String email;

    @Embedded
    private Address previousAddress;
    // private XMLGregorianCalendar startDate;
    /*
    private Boolean duplicateBillFlag;
    private String ebillToken;
    private String ebillEmailAddress;
    private String ebillToken255;
    private String pendingFinRespRemoval;
    private String printOnBillInd;
    */

    @Transient
    private DebtInfo debtInfo;
    @Transient
    private Long sequenceNumber;
    @Transient
    private List<String> ccErrorCodes;

    protected String formatPhoneNumber(String phoneNumber) {
        StringBuffer sb = new StringBuffer();
        if (phoneNumber != null && phoneNumber.length() == 10) {

            //sb.append("(");
            sb.append(phoneNumber.substring(0, 3));
            //sb.append(")");
            sb.append("-");

            sb.append(phoneNumber.substring(3, 6));
            sb.append("-");

            sb.append(phoneNumber.substring(6, 10));
        }
        return sb.toString();
    }

    public PersonEntity() {

        posIdAttemptCount = 0;
        ssnMatchCount = 0;
        utilityDebt = BigDecimal.ZERO;
        cisAccountNumbers = new HashSet<Long>();
        ccErrorCodes = new ArrayList<String>();
    }

    public PersonEntity(PersonEntity copy) {
        cisAccountNumbers = new HashSet<Long>();
        ccErrorCodes = new ArrayList<String>();

        this.id = copy.getId(); 
        this.cisType = copy.getCisType();
        this.personId = copy.getPersonId();
        this.personType = copy.getPersonType();
        this.landlord = copy.isLandlord();
        this.posId = copy.getPosId();
        this.posIdMessage = copy.getPosIdMessage();
        this.posIdAttemptCount = copy.getPosIdAttemptCount();
        this.utilityDebt = copy.getUtilityDebt();
        this.namePrefix = copy.getNamePrefix();
        this.firstName = copy.getFirstName();
        this.middleName = copy.getMiddleName();
        this.lastName = copy.getLastName();
        this.businessName = copy.getBusinessName();
        this.businessType = copy.getBusinessType();
        this.cisAccountNumbers.addAll(copy.getCisAccountNumbers());
        this.primaryPhoneNumber = copy.getPrimaryPhoneNumber();
        //this.primaryPhoneType = copy.getPrimaryPhoneType();
        this.secondaryPhoneNumber = copy.getSecondaryPhoneNumber();
        // this.secondaryPhoneType = copy.getSecondaryPhoneType();
        this.birthDate = copy.getBirthDate();
        this.ssn = copy.getSsn();
        this.ein = copy.getEin();
        this.ssnMatchCount = copy.getSsnMatchCount();
        this.ssnMatchError = copy.getSsnMatchError();
        this.relationshipType = copy.getRelationshipType();
        this.email = copy.getEmail();
        this.previousAddress = copy.getPreviousAddress();
        this.debtInfo = copy.getDebtInfo();
        this.sequenceNumber = copy.getSequenceNumber();
        this.ccErrorCodes.addAll(copy.getCcErrorCodes());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ")
                .append("id:" + id + ", ")
                .append("personType: " + personType + ", ")
                .append("cisPersonId: " + personId + ", ")
                .append("isFraud:" + isFraud() + ", ")
                .append("isDeceased: " + isDeceased() + ", ")
                .append("removable: " + isRemovable() + ", ")
                .append("landlord: " + landlord + ", ")
                .append("posId: " + posId + ", ")
                .append("posIdMessage: " + posIdMessage + ", ")
                .append("posIdAttemptCount: " + posIdAttemptCount + ", ")
                .append("passPosId: " + passPosId().toString() + ", ")
                .append("utilityDebt: " + utilityDebt + ", ")
                .append("lastName: " + lastName + ", ")
                .append("firstName: " + firstName + ", ")
                .append("middleName: " + middleName + ", ")
                .append("businessName: " + businessName + ", ")
                .append("businessType: " + businessType + ", ")
                .append("isBusiness: " + isBusiness() + ", ")
                .append("primaryPhoneNumber: " + primaryPhoneNumber + ", ")
        //        .append("primaryPhoneType: " + primaryPhoneType + ", ");
                .append("secondaryPhoneNumber: " + secondaryPhoneNumber + ", ");
        // .append("secondaryPhoneType: " + secondaryPhoneType + ", ")
        if (birthDate == null)
            sb.append("birthDate: null, ");
        else
            sb.append("birthDate: " + DateUtils.toDisplayFormat(birthDate.toLocalDate()) + ", ");
        sb.append("ssn: " + ssn + ", ")
                .append("ein:" + ein + ", ")
                .append("ssnMatchCount: " + ssnMatchCount + ", ")
                .append("ssnMatchError: " + ssnMatchError + ", ")
                .append("relationshipType: " + relationshipType + ", ")
                .append("email:" + email + ", ");
        if (cisAccountNumbers != null) {
            sb.append("cisAccountNumbers: {");
            for (Long num : cisAccountNumbers)
                sb.append(num).append(", ");
            sb.delete(sb.length() - 2, sb.length()).append("}, ");
        }
        else
            sb.append("cisAccountNumbers: null, ");
        if (previousAddress != null)
            sb.append("previousAddress:" + previousAddress.toString());
        else
            sb.append("previousAddress: null");
        // debtInfo
        // sequenceNumber
        // ccErrorCodes
        sb.append(" }");
        return sb.toString();
    }

    /**
     * Updates person based on this person's flags
     * Populates any fields that are unpopulated in this person with data from update:
     * 	 this field == null && updated field == value -> copy value to this
     * 	 this field == value && updated field == null -> retain this value
     *   this field == value && udpated field == another -> retain this value  (exceptions maintained in PersonService)   
     * 
     * @param update
     *        new person data
     */
    public void update(Person update) {
    	//this.id = update.getId(); // want to keep current id
    	//this.personType = update.getPersonType(); // want to keep this person type

    	if(StringUtils.isEmpty(this.cisType) && !StringUtils.isEmpty(update.getCisType()))
    		this.cisType = update.getCisType();

    	if(personId == null && update.getPersonId() != null)
    		this.personId = update.getPersonId();

    	if(this.landlord == null && update.isLandlord() != null)
    		this.landlord = update.isLandlord();

    	if(StringUtils.isEmpty(this.posId) && !StringUtils.isEmpty(update.getPosId()))
    		this.posId = update.getPosId();

    	if(StringUtils.isEmpty(this.posIdMessage) && !StringUtils.isEmpty(update.getPosIdMessage()))
    		this.posIdMessage = update.getPosIdMessage();
    	
    	if(posIdAttemptCount == 0 && this.getPosIdAttemptCount() > 0)
    		this.posIdAttemptCount = update.getPosIdAttemptCount();
    	
    	if(utilityDebt.compareTo(BigDecimal.ZERO) == 0 && update.getUtilityDebt().compareTo(BigDecimal.ZERO) != 0)
    		utilityDebt = update.getUtilityDebt();

    	if(StringUtils.isEmpty(this.namePrefix) && !StringUtils.isEmpty(update.getNamePrefix()))
    		this.namePrefix = update.getNamePrefix();

    	if(StringUtils.isEmpty(this.lastName) && !StringUtils.isEmpty(update.getLastName()))
       		this.lastName = update.getLastName();

       	if(StringUtils.isEmpty(this.firstName) && !StringUtils.isEmpty(update.getFirstName()))
       		this.firstName = update.getFirstName();

       	if(StringUtils.isEmpty(this.middleName) && !StringUtils.isEmpty(update.getMiddleName()))
       		this.middleName = update.getMiddleName();

       	if(StringUtils.isEmpty(this.businessName) && !StringUtils.isEmpty(update.getBusinessName()))
       		this.businessName = update.getBusinessName();
       		
       	if(StringUtils.isEmpty(this.businessType) && !StringUtils.isEmpty(update.getBusinessType()))
       		this.businessType = update.getBusinessType();

       	if(cisAccountNumbers.isEmpty() && !update.getCisAccountNumbers().isEmpty())
       		cisAccountNumbers.addAll(update.getCisAccountNumbers());

       	if(StringUtils.isEmpty(this.primaryPhoneNumber) && !StringUtils.isEmpty(update.getPrimaryPhoneNumber()))
       		this.primaryPhoneNumber = update.getPrimaryPhoneNumber();

       	/*if(StringUtils.isEmpty(this.primaryPhoneType) && !StringUtils.isEmpty(update.getPrimaryPhoneType()))
       		this.primaryPhoneType = update.getPrimaryPhoneType();*/
       	
       	if(StringUtils.isEmpty(this.secondaryPhoneNumber) && !StringUtils.isEmpty(update.getSecondaryPhoneNumber()))
       		this.secondaryPhoneNumber = update.getSecondaryPhoneNumber();

       	if(birthDate == null && update.getBirthDate() != null)
       		birthDate = update.getBirthDate();
       	
       	if(StringUtils.isEmpty(this.ssn) && !StringUtils.isEmpty(update.getSsn()))
       		this.ssn = update.getSsn();

       	if(StringUtils.isEmpty(this.ein) && !StringUtils.isEmpty(update.getEin()))
       		this.ein = update.getEin();

       	if(ssnMatchCount == null && update.getSsnMatchCount() > 0)
       		this.ssnMatchCount = update.getSsnMatchCount();
       	
       	if(StringUtils.isEmpty(ssnMatchError) && !StringUtils.isEmpty(update.getSsnMatchError()))
       		this.ssnMatchError = update.getSsnMatchError();

       	if(StringUtils.isEmpty(this.relationshipType) && !StringUtils.isEmpty(update.getRelationshipType()))
       		this.relationshipType = update.getRelationshipType();

       	if(StringUtils.isEmpty(this.email) && !StringUtils.isEmpty(update.getEmail()))
       		this.email = update.getEmail();
       	
       	if(previousAddress == null && update.getPreviousAddress() != null)
       		previousAddress = new Address(update.getPreviousAddress());
       	
       	if(debtInfo == null && update.getDebtInfo() != null)
       		debtInfo = new DebtInfo(update.getDebtInfo());
       	
       	if(sequenceNumber == null && update.getSequenceNumber() != null)
       		sequenceNumber = update.getSequenceNumber();
       	
       	if(ccErrorCodes.isEmpty() && !update.getCcErrorCodes().isEmpty()) {
       		ccErrorCodes.addAll(update.getCcErrorCodes());
       	}
    }

    /**
     * Returns the person's name formatted as
     * LastName, FirstName or CompanyName
     * Capitalization is not changed
     * 
     * @return
     */
    public String getFormattedName() {
        StringBuffer sb = new StringBuffer();
        /*if (!StringUtils.isBlank(lastName)) {
            sb.append(lastName);
            if (!StringUtils.isBlank(firstName)) {
                sb.append(NAME_SEPARATOR);
                sb.append(firstName);
            }*/
        if (!StringUtils.isBlank(firstName)) {
            sb.append(firstName);
            if (!StringUtils.isBlank(lastName)) {
                sb.append(NAME_SEPARATOR);
                sb.append(lastName);
            }
        }
        else if (!StringUtils.isBlank(businessName)) {
            sb.append(businessName);
        }
        return sb.toString();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Only populated if the person already exists in CIS
     * 
     * @return CIS personID
     */
    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public void setPersonId(String personId) {
        this.personId = Long.valueOf(personId);
    }

    public String getPersonType() {
        return personType;
    }
    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getCisType() {
        return cisType;
    }
    public void setCisType(String personType) {
        this.cisType = personType;
    }

    /**
     * Indicates if the person is flagged for fraud
     * Fraud is indicated by the cis person type (populated through personInfo or accountPersonInfo)
     */
    public Boolean isFraud() {
        if (StringUtils.isNotEmpty(cisType) && cisType.equals(FRAUD_CIS_PERSON_TYPE))
            return true;
        else
            return false;
    }

    /**
     * Indicates if teh person if flagged as deceased
     * Deceased is indicated by the cis person type (populated through personInfo or accountPersonInfo)
     * 
     * @return
     */
    public Boolean isDeceased() {
        if (StringUtils.isNotEmpty(cisType) && cisType.equals(DECEASED_CIS_PERSON_TYPE))
            return true;
        else
            return false;
    }

    /**
     * Designates if the Person can be edited
     * If a person is not edible, the primary phone number and email address
     * can be changed, all other fields are locked
     * 
     * @return
     */
    public Boolean isEditable() {
    	// existing business records are not editable
    	if(personId != null && this.isBusiness())
    		return false;
    	// if individual and has passed posid
    	if(!this.isBusiness() && this.passPosId())
    		return false;
    	else
    		return true;
    }

    /**
     * Designates if the person can be removed from the service request
     * Some situations cause a person to be locked in - if we add a person that
     * owes us money you can't just remove the person from the request, you have 
     * to handle the debt
     * 
     * Debt is defined as outstanding utility arrears or current utility writeoff in the same loe
     * 
     * @return
     */
    public Boolean isRemovable() {
        if (this.utilityDebt != null && this.utilityDebt.compareTo(BigDecimal.ZERO) > 0)
            return false;
        else
            return true;
    }

    public void setLandlord(Boolean isLandlord) {
        this.landlord = isLandlord;
    }
    public Boolean isLandlord() {
        return this.landlord;
    }

    /**
     * Designates if the person needs posId'd
     * 
     * @return
     *         {true} Person has been posId'd - does not need re-authenticated
     *         {false} Person has not been posId'd - needs to go through posId
     */
    public Boolean passPosId() {
        if (posId != null && posId.equals("Y"))
            return true;
        else
            return false;
    }

    public String getPosId() {
        return posId;
    }
    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPosIdMessage() {
        return posIdMessage;
    }
    public void setPosIdMessage(String posIdMessage) {
        this.posIdMessage = posIdMessage;
    }

    public Integer getPosIdAttemptCount() {
        return posIdAttemptCount;
    }
    public void setPosIdAttemptCount(Integer posIdAttemptCount) {
        this.posIdAttemptCount = posIdAttemptCount;
    }

    public void incrementPosIdAttemptCount() {
        if (posIdAttemptCount == null)
            posIdAttemptCount = 1;
        else
            ++posIdAttemptCount;
    }

    public Boolean isPosIdMaxAttempts() {
        if (posIdAttemptCount != null && posIdAttemptCount >= MAX_POSID_ATTEMPTS)
            return true;
        else
            return false;
    }

    public BigDecimal getUtilityDebt() {
        return utilityDebt;
    }
    public void setUtilityDebt(BigDecimal utilityDebt) {
        this.utilityDebt = utilityDebt;
    }

    public String getNamePrefix() {

        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    /**
     * Transforms text to upper case
     * CIS restraints:
     * 
     */
    public void setLastName(String lastName) {
        this.lastName = StringUtils.upperCase(lastName);
    }
    public String getLastName() {
        return lastName;
    }

    /**
     * Transforms text to upper case
     */
    public void setFirstName(String firstName) {
        this.firstName = StringUtils.upperCase(firstName);
    }
    public String getFirstName() {
        return firstName;
    }

    /**
     * Transforms text to upper case
     */
    public void setMiddleName(String middleName) {
        this.middleName = StringUtils.upperCase(middleName);
    }
    public String getMiddleName() {
        return middleName;
    }

    /*
    public String getNameSuffix() {
        return nameSuffix;
    }
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }
    */
    
    public Boolean isBusiness() {
    	if(StringUtils.isEmpty(this.businessName))
    		return false;
    	else
    		return true;
    }

    /**
     * Transforms text to upper case
     */
    public void setBusinessName(String businessName) {
        this.businessName = StringUtils.upperCase(businessName);
    }
    public String getBusinessName() {
        return businessName;
    }
    
    public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Set<Long> getCisAccountNumbers() {
        if (this.cisAccountNumbers == null)
            this.cisAccountNumbers = new HashSet<Long>();
        return this.cisAccountNumbers;
    }
    public void addCisAccountNumber(Long accountNumber) {
        if (this.cisAccountNumbers == null)
            this.cisAccountNumbers = new HashSet<Long>();
        this.cisAccountNumbers.add(accountNumber);
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }
    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }
    public String getPrimaryPhoneNumberUnformatted() {
        if (StringUtils.isNotBlank(primaryPhoneNumber))
            return primaryPhoneNumber.replace("-", "");
        else
            return null;
    }
    public String getFormattedPrimaryPhoneNumber() {
        return formatPhoneNumber(getPrimaryPhoneNumberUnformatted());
    }

    /*public String getPrimaryPhoneType() {
        return primaryPhoneType;
    }
    public void setPrimaryPhoneType(String primaryPhoneType) {
        this.primaryPhoneType = primaryPhoneType;
    }
    public PhoneType getPrimaryPhoneTypeCode() {
        if (this.primaryPhoneType != null)
            return PhoneType.valueOf(this.primaryPhoneType.toUpperCase());
        else
            return null;
    }
    public void setPrimaryPhoneType(PhoneType primaryPhoneType) {
        this.primaryPhoneType = primaryPhoneType.name();
    }*/

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }
    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }
    public String getSecondaryPhoneNumberUnformatted() {
        if (StringUtils.isNotBlank(secondaryPhoneNumber))
            return secondaryPhoneNumber.replace("-", "");
        else
            return null;
    }
    public String getFormattedSecondaryPhoneNumber() {
        return formatPhoneNumber(getSecondaryPhoneNumberUnformatted());
    }


    /*
    public String getSecondaryPhoneType() {
        return secondaryPhoneType;
    }
    public void setSecondaryPhoneType(String secondaryPhoneType) {
        this.secondaryPhoneType = secondaryPhoneType;
    }
    public PhoneType getSecondaryPhoneTypeCode() {
        return PhoneType.valueOf(this.secondaryPhoneType);
    }
    public void setSecondaryPhoneType(PhoneType secondaryPhoneType) {
        this.secondaryPhoneType = secondaryPhoneType.name();
    }*/

    public Date getBirthDate() {
        return this.birthDate;
    }
    public LocalDate getBirthDateAsLocalDate() {
        if (birthDate != null)
            return birthDate.toLocalDate();
        else
            return null;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public void setBirthDate(String date) {
    	if(!StringUtils.isEmpty(date)) {
	        if (date.contains("/")) // date.matches("^\\d{2}/\\d{2}/\\d{4}$"))
	            this.birthDate = Date.valueOf(DateUtils.fromDisplayFormat(date));
	        else // if(date.matches("^\d{8}$")) // yyyymmdd
	            this.birthDate = Date.valueOf(DateUtils.fromCisFormat(date));
    	}
    	else
    		this.birthDate = null;
    }

    /**
     * Looks at person id and current birth date to see if birth date can be updated
     * We want customers to be able to add a birth date if we don't have one but
     * not change an existing value.
     * ** logic is also replicated in additionalperson.js on JsonPerson
     * @return
     */
    public Boolean isBirthDateEditable() {
    	// doesn't call isEditable because we can still add a value if the existing one was empty
    	if(this.personId != null && this.birthDate != null)
    		return false;
    	else
    		return true;
    }

    public String getSsn() {
        return ssn;
    }
    public String getSsnUnformatted() {
    	// this is the value we sent to CIS+, don't return it if we only have the final 4
        if (StringUtils.isNotBlank(ssn)) {
        	String social = ssn.replace("-", "").replace("*", "");
        	if(social.length() < 9)
        		return null;
    		else
    			return social;
        }
        else
            return null;
    }
    public String getMaskedSsn() {
        if (StringUtils.isNotBlank(ssn) && ssn.length() >= 4)
            return "***-**-" + ssn.substring(ssn.length() - 4, ssn.length());
        else
            return null;
    }
    public void setSsn(String ssn) {
        // if (ssn == null || !ssn.startsWith("***"))
        this.ssn = ssn;
        // else retain ssn value - looks like we are trying to set to the masked value
    }

    /**
     * Can be either a full 9 digit value or a 4 digit suffix (only 4 digits are returned from CIS)
     */
    public String getEin() {
        return ein;
    }
    public String getEinUnformatted() {
    	// this is the value we sent to CIS+, don't return it if we only have the final 4
        if (StringUtils.isNotBlank(ein)) {
        	String unformatted = ein.replace("-", "").replace("*", "");
        	if(unformatted.length() < 9)
        		return null;
    		else
    			return unformatted;
        }
    	else
    		return null;
    }
    public String getMaskedEin() {
    	if(StringUtils.isNotBlank(ein) && ein.length() >= 4) {
    		return "**-***" + ein.substring(ein.length()-4,  ein.length());
    	}
    	else
    		return null;
    }
    public void setEin(String ein) {
        this.ein = ein;
    }
    
    /**
     * Looks at person id and current ein to see if EIN can be updated
     * We want customers to be able to add a ein if we don't have one but
     * not change an existing value.
     * ** logic is also replicated in additionalperson.js on JsonPerson
     * @return
     */
    public Boolean isEinEditable() {
    	// doesn't call isEditable because we can still add a value if the existing one was empty
    	if(this.personId != null && !StringUtils.isEmpty(this.ein))
    		return false;
    	else
    		return true;
    }

    /*public String getEmployerName() {
        return employerName;
    }
    
    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }*/

    public Integer getSsnMatchCount() {
        return ssnMatchCount;
    }
    public void setSsnMatchCount(Integer ssnMatchCount) {
        this.ssnMatchCount = ssnMatchCount;
    }
    public Boolean isSsnMatchMaxAttempts() {
        if (ssnMatchCount != null && ssnMatchCount >= MAX_SSN_MATCH_ATTEMPTS)
            return true;
        else
            return false;
    }
    
    public String getSsnMatchError() {
		return ssnMatchError;
	}
	public void setSsnMatchError(String ssnMatchError) {
		this.ssnMatchError = ssnMatchError;
	}

	public String getRelationshipType() {
        return relationshipType;
    }
    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getPreviousAddress() {
        return previousAddress;
    }

    public void setPreviousAddress(Address previousAddress) {
        this.previousAddress = previousAddress;
    }

    public DebtInfo getDebtInfo() {
        return debtInfo;
    }

    public void setDebtInfo(DebtInfo debtInfo) {
        this.debtInfo = debtInfo;
    }
    
    public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = Long.valueOf(sequenceNumber);
	}
	
	public List<String> getCcErrorCodes() {
		return this.ccErrorCodes;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof PersonEntity))
            return false;
        if (((PersonEntity) obj).getId() == this.id)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /*
    public XMLGregorianCalendar getStartDate() {
    
        return startDate;
    }
    
    public void setStartDate(XMLGregorianCalendar startDate) {
    
        this.startDate = startDate;
    }
    
    public Boolean getDuplicateBillFlag() {
    
        return duplicateBillFlag;
    }
    
    public void setDuplicateBillFlag(Boolean duplicateBillFlag) {
    
        this.duplicateBillFlag = duplicateBillFlag;
    }
    
    public String getEbillToken() {
    
        return ebillToken;
    }
    
    public void setEbillToken(String ebillToken) {
    
        this.ebillToken = ebillToken;
    }
    
    public String getEbillEmailAddress() {
    
        return ebillEmailAddress;
    }
    
    public void setEbillEmailAddress(String ebillEmailAddress) {
    
        this.ebillEmailAddress = ebillEmailAddress;
    }
    
    public String getEbillToken255() {
    
        return ebillToken255;
    }
    
    public void setEbillToken255(String ebillToken255) {
    
        this.ebillToken255 = ebillToken255;
    }
    
    public String getPendingFinRespRemoval() {
    
        return pendingFinRespRemoval;
    }
    
    public void setPendingFinRespRemoval(String pendingFinRespRemoval) {
    
        this.pendingFinRespRemoval = pendingFinRespRemoval;
    }
    
    public String getPrintOnBillInd() {
    
        return printOnBillInd;
    }
    
    public void setPrintOnBillInd(String printOnBillInd) {
    
        this.printOnBillInd = printOnBillInd;
    }
    */
}
