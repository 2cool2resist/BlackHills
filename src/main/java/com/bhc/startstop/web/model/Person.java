package com.bhc.startstop.web.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.bhc.startstop.webservice.model.DebtInfo;

public interface Person {

    public Long getId();
    public void setId(Long id);

    public void update(Person update);

    /**
     * Returns the person's name formatted as
     * LastName, FirstName or CompanyName
     * Capitalization is not changed
     * 
     * @return
     */
    public String getFormattedName();

    public Long getPersonId();
    public void setPersonId(Long personId);
    public void setPersonId(String personId);

    public String getPersonType();
    public void setPersonType(String personType);

    public String getCisType();
    public void setCisType(String personType);

    /**
     * Indicates if the person is flagged for fraud
     * Fraud is indicated by the cis person type (populated through personInfo or accountPersonInfo)
     */
    public Boolean isFraud();

    /**
     * Indicates if teh person if flagged as deceased
     * Deceased is indicated by the cis person type (populated through personInfo or accountPersonInfo)
     * 
     * @return
     */
    public Boolean isDeceased();

    /**
     * Designates if the Person can be edited
     * If a person is not edible, the phone numbers and email address
     * can be changed, all other fields are locked
     * 
     * @return
     */
    public Boolean isEditable();

    /**
     * Designates if the person can be removed from the service request
     * Some situations cause a person to be locked in - if we add a person that
     * owes us money
     * you can't just remove the person from the request, you have to handle the
     * debt
     * 
     * @return
     */
    public Boolean isRemovable();

    public void setLandlord(Boolean isLandlord);
    public Boolean isLandlord();

    /**
     * Designates if the person needs posId'd
     * 
     * @return
     *         {true} Person has been posId'd - does not need re-authenticated
     *         {false} Person has not been posId'd - needs to go through posId
     */
    public Boolean passPosId();
    public String getPosId();
    public void setPosId(String posId);

    public String getPosIdMessage();
    public void setPosIdMessage(String posIdMessage);

    public Integer getPosIdAttemptCount();
    public void setPosIdAttemptCount(Integer posIdAttemptCount);

    /**
     * Indicates if the Person has reached the maximum number of posid attempts
     */
    public Boolean isPosIdMaxAttempts();

    public void incrementPosIdAttemptCount();

    public BigDecimal getUtilityDebt();
    public void setUtilityDebt(BigDecimal totalArrears);

    public String getNamePrefix();
    public void setNamePrefix(String namePrefix);

    /**
     * Transforms text to upper case
     */
    public void setLastName(String lastName);
    public String getLastName();

    /**
     * Transforms text to upper case
     */
    public void setFirstName(String firstName);
    public String getFirstName();

    /**
     * Transforms text to upper case
     */
    public void setMiddleName(String middleName);
    public String getMiddleName();

    /*
    public String getNameSuffix() {
        return nameSuffix;
    }
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }
    */
    /**
     * Indicates if the business name field is populated, meaning this person represents a business record
     * @return
     */
    public Boolean isBusiness();

    /**
     * Transforms text to upper case
     */
    public void setBusinessName(String businessName);
    public String getBusinessName();

    public void setBusinessType(String businessType);
    public String getBusinessType();

    public Set<Long> getCisAccountNumbers();
    public void addCisAccountNumber(Long accountNumber);

    public String getPrimaryPhoneNumber();
    public void setPrimaryPhoneNumber(String primaryPhoneNumber);
    public String getPrimaryPhoneNumberUnformatted();
    public String getFormattedPrimaryPhoneNumber();
    /*public String getPrimaryPhoneType();
    public void setPrimaryPhoneType(String primaryPhoneType);*/

    public String getSecondaryPhoneNumber();
    public void setSecondaryPhoneNumber(String secondaryPhoneNumber);
    public String getSecondaryPhoneNumberUnformatted();
    public String getFormattedSecondaryPhoneNumber();
    /*
    public String getSecondaryPhoneType();
    public void setSecondaryPhoneType(String secondaryPhoneType);
    */

    public Date getBirthDate();
    public LocalDate getBirthDateAsLocalDate();
    public void setBirthDate(Date birthDate);
    public void setBirthDate(String date);
    /**
     * Looks at person id and current birth date to see if birthDate can be updated
     * We want customers to be able to add a brith date if we don't have one but
     * not change an existing value.
     * ** logic is also replicated in additionalperson.js on JsonPerson
     * @return
     */
    public Boolean isBirthDateEditable();

    public String getSsn();
    public String getSsnUnformatted();
    public String getMaskedSsn();
    public void setSsn(String ssn);

    public String getEin();
    public String getEinUnformatted();
    public void setEin(String ein);
    /**
     * Looks at person id and current ein to see if EIN can be updated
     * We want customers to be able to add a ein if we don't have one but
     * not change an existing value.
     * ** logic is also replicated in additionalperson.js on JsonPerson
     * @return
     */
    public Boolean isEinEditable();

    public Integer getSsnMatchCount();
    public void setSsnMatchCount(Integer ssnMatchCount);
    public Boolean isSsnMatchMaxAttempts();
    
    public String getSsnMatchError();
    public void setSsnMatchError(String errorMessage);

    public String getRelationshipType();
    public void setRelationshipType(String relationshipType);

    public String getEmail();
    public void setEmail(String email);

    public Address getPreviousAddress();
    public void setPreviousAddress(Address previousAddress);

    public DebtInfo getDebtInfo();
    public void setDebtInfo(DebtInfo debtInfo);

    public Long getSequenceNumber();
    public void setSequenceNumber(Long seqNbr);
    public void setSequenceNumber(String seqNbr);
    
    public List<String> getCcErrorCodes();
}
