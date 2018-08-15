package com.bhc.startstop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bhc.startstop.web.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Sanitized representation of the Person object
 * A JsonPerson is sent to the client - all data variables should be permissible to be examined by the user
 * Structure is exposed to end user in additional-person.html
 * 
 * A Person (BH Corp data structure) is converted to a JsonPerson (public data structure)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPerson implements Serializable {

    private static final long serialVersionUID = 709562487411309278L;
    private Long id;          // gets mapped to Person.id
    private String token;        // uses octal representation of personId (quasi-obfuscation)
    private Boolean selected;
    private Boolean canEdit;
    // private Boolean canDelete // is removable
    private Boolean posid;
    private String lastName;
    private String firstName;
    private String middleName;
    private String businessName;
    private String businessType;
    //private String primaryPhoneType;
    private String primaryPhoneNumber;
    // private String secondaryPhoneType;
    private String secondaryPhoneNumber;
    private String relationshipType;
    private String dateOfBirth;
    private String ssn;
    private String ein;
    private String email;
    private Address previousAddress;

    private List<String> errors; // set in person service - contains user messages for any errors on this person

    public JsonPerson() {
        canEdit = true;
        selected = false;
        errors = new ArrayList<String>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ")
                .append("id:" + id + ", ")
                .append("token:" + token + ", ")
                .append("selected: " + selected + ", ")
                .append("canEdit:" + canEdit + ", ")
                .append("posid:" + posid + ", ")
                .append("lastName:" + lastName + ", ")
                .append("firstName:" + firstName + ", ")
                .append("middleName:" + middleName + ", ")
                .append("businessName:" + businessName + ", ")
                .append("businessType:" + businessType + ", ")
                //.append("primaryPhoneType:" + primaryPhoneType + ",")
                .append("primaryPhoneNumber:" + primaryPhoneNumber + ", ")
                //.append("secondaryPhoneType:" + secondaryPhoneType + ",")
                .append("secondaryPhoneNumber:" + secondaryPhoneNumber + ", ")
                .append("relationshipType: " + relationshipType + ", ")
                .append("ssn:" + ssn + ", ")
                .append("ein:" + ein + ", ")
                .append("dateOfBirth: " + dateOfBirth + ", ")
                .append("email:" + email + ", ");
        if (previousAddress != null)
            sb.append("previousAddress:" + previousAddress.toString() + ", ");
        else
            sb.append("previousAddress: null, ");
        if (errors == null)
            sb.append("errors: null");
        else {
            sb.append("errors: [");
            for (int index = 0; index < errors.size(); index++) {
                sb.append(errors.get(index));
                if (index < errors.size() - 1)
                    sb.append(", ");
            }
            sb.append("]");
        }
        sb.append(" }");
        return sb.toString();
    }
    
    public Boolean isSelected() {
    	return selected;
    }
    public void setSelected(Boolean isSelected) {
    	this.selected = isSelected;
    }

    /**
     * Designates if the Person can be edited
     * If a person is not edible, the phone numbers and email address
     * can be changed, all other fields are locked
     * 
     * @return
     */
    public Boolean getCanEdit() {
        return canEdit;
    }
    public void setCanEdit(Boolean isEditable) {
        this.canEdit = isEditable;
    }

    public Boolean isPosid() {
        return posid;
    }
    public void setPosid(Boolean posid) {
        this.posid = posid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getToken() {
    	return token;
    }
    
    public void setToken(String token) {
    	this.token = token;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getMiddleName() {
        return middleName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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
	
	public String getFormattedName() {
		if(!StringUtils.isEmpty(businessName))
			return businessName;
		else {
			StringBuilder name = new StringBuilder();
			name.append(firstName).append(" ").append(lastName);
			return name.toString();
		}
	}

    /*public String getPrimaryPhoneType() {
        return primaryPhoneType;
    }
    public void setPrimaryPhoneType(String primaryPhoneType) {
        this.primaryPhoneType = primaryPhoneType;
    }*/

	public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }
    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    /*public String getSecondaryPhoneType() {
        return secondaryPhoneType;
    }
    public void setSecondaryPhoneType(String secondaryPhoneType) {
        this.secondaryPhoneType = secondaryPhoneType;
    }*/
    
    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }
    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }
    
    public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

    public String getSsn() {
        return ssn;
    }
	public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEin() {
        return ein;
    }
    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public List<String> getErrors() {
        if (errors == null)
            errors = new ArrayList<String>();
        return errors;
    }
    public void addError(String error) {
        if (errors == null)
            errors = new ArrayList<String>();
        errors.add(error);
    }

}
