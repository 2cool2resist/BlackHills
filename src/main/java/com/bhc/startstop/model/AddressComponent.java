package com.bhc.startstop.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class AddressComponent implements Serializable {

    private static final long serialVersionUID = -7808249449437851197L;
    private static final String STREET_TYPE_S = "S";
    private static final String STREET_TYPE_B = "B";
    private static final String STREET_TYPE_P = "P";
    private static final String STREET_TYPE_P_STRING = " PO BOX ";
    private static final String STREET_TYPE_B_STRING = " BOX ";
    private static final String STREET_TYPE_S_APT_STRING = " APT ";

    private String streetNumber;
    private String streetNumberType;
    private String streetPreDirectionCode;
    private String streetName;
    private String streetPostDirectionCode;
    private String streetSuffix;
    private String streetSuffixDescription;
    private String apartmentNumber;
    private String specialAddressCode;
    private String specialAddressNumber;
    private String zipCode;
    private String zipCodeExt;
    private String city;
    private String state;
    private String country;
    private String county;
    private String barCode;
    private String buildingName;
    private Boolean activeFlag;
    // address update fields
    private String addressDescription;
    private String action;
    private String actionCode;
    private String addressType;
    private boolean seasonalAddress;
    private boolean addAddress;
    private String labelCaps;
    private String labelSmall;
    private boolean allowByPass;
    private String removeAddress;
    private String rrNumber;
    private String rrBoxNumber;
    private String poBoxNumber;
    private String houseNumber;
    private boolean saveAsOriginallyEntered;
    private int verifyLevel;
    private String validationStatus;
    private boolean useModifiedAddress;
    private String submitButton;
    private long accountId;
    
    /**
     * ADDED SPECIFICALLY FOR START/STOP
     * returns the special address as a formatted string
     * @return
     */
    public String getFormattedSpecialAddress() {
    	if(!StringUtils.isBlank(specialAddressCode)) {
    		if(specialAddressCode.equals("P"))
    			return "PO BOX " + poBoxNumber;
    		else if(specialAddressCode.equals("F"))
    			return "FIRE BOX " + rrBoxNumber;
    		else if(specialAddressCode.equals("RR"))
    			return "RR " + rrNumber;
    		else // specialAddressCode.equals("B")
    			return "BOX " + rrBoxNumber;
    	}
    	return null;
    }

    public String getFormattedAddressLineOne() {

        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isBlank(specialAddressCode)) {
            sb.append(specialAddressCode);
            sb.append(' ');
            sb.append(specialAddressNumber);

            if (streetNumberType != null && streetNumberType.equals(STREET_TYPE_B)
                    && !StringUtils.isBlank(streetNumber)) {
                sb.append(STREET_TYPE_B_STRING);
                sb.append(streetNumber);
            }
        }
        else if (streetNumberType != null && streetNumberType.equals(STREET_TYPE_P)) {
            sb.append(STREET_TYPE_P_STRING);
            sb.append(streetNumber);
        }
        else {
            sb.append(streetNumber);

            if (!StringUtils.isBlank(streetPreDirectionCode)) {
                sb.append(' ');
                sb.append(streetPreDirectionCode);
            }

            sb.append(' ');
            sb.append(streetName);

            if (!StringUtils.isBlank(streetSuffix)) {
                sb.append(' ');
                sb.append(streetSuffix);
            }

            if (!StringUtils.isBlank(streetPostDirectionCode)) {
                sb.append(' ');
                sb.append(streetPostDirectionCode);
            }

            if (!StringUtils.isBlank(apartmentNumber)) {
                char c = apartmentNumber.charAt(0);
                if (Character.isDigit(c)) {
                    sb.append(STREET_TYPE_S_APT_STRING);
                }
                else {
                    sb.append(' ');
                }
                sb.append(apartmentNumber);
            }
        }
        return sb.toString();
    }

    public String getFormattedAddressLineTwo() {

        StringBuffer sb = new StringBuffer();
        sb.append(city);
        sb.append(", ");
        sb.append(state);
        sb.append("  ");

        if (zipCode == null) {
            // don't append the zipCode
        }
        else if (zipCode != null && zipCode.length() == 9) {
            sb.append(zipCode.substring(0, 5));
            sb.append('-');
            sb.append(zipCode.substring(5, 9));
        }
        else {
            sb.append(zipCode);
            if (!StringUtils.isBlank(zipCodeExt)) {
                sb.append('-');
                sb.append(zipCodeExt);
            }
        }
        return sb.toString();
    }

    public String getStreetNumber() {

        if (StringUtils.isNotBlank(houseNumber)) {
            streetNumber = houseNumber;
        }
        else if (StringUtils.isNotBlank(poBoxNumber)) {
            streetNumber = poBoxNumber;
        }
        else if (StringUtils.isNotBlank(rrNumber)) {
            specialAddressCode = "RR";
            specialAddressNumber = rrNumber;
            if (StringUtils.isNotBlank(rrBoxNumber)) {
                streetNumber = rrBoxNumber;
            }
        }
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {

        this.streetNumber = streetNumber;
    }

    public String getStreetNumberType() {

        if (StringUtils.isNotBlank(houseNumber)) {
            streetNumberType = STREET_TYPE_S;
        }
        else if (StringUtils.isNotBlank(poBoxNumber)) {
            streetNumberType = STREET_TYPE_P;
        }
        else if (StringUtils.isNotBlank(rrNumber)) {
            specialAddressCode = "RR";
            specialAddressNumber = rrNumber;
            if (StringUtils.isNotBlank(rrBoxNumber)) {
                streetNumberType = STREET_TYPE_B;
            }
        }
        return streetNumberType;
    }

    public void setStreetNumberType(String streetNumberType) {

        this.streetNumberType = streetNumberType;
    }

    public String getStreetPreDirectionCode() {

        return streetPreDirectionCode;
    }

    public void setStreetPreDirectionCode(String streetPreDirectionCode) {

        this.streetPreDirectionCode = streetPreDirectionCode;
    }

    public String getStreetName() {

        return streetName;
    }

    public void setStreetName(String streetName) {

        this.streetName = streetName;
    }

    public String getStreetPostDirectionCode() {

        return streetPostDirectionCode;
    }

    public void setStreetPostDirectionCode(String streetPostDirectionCode) {

        this.streetPostDirectionCode = streetPostDirectionCode;
    }

    public String getStreetSuffix() {

        return streetSuffix;
    }

    public void setStreetSuffix(String streetSuffix) {

        this.streetSuffix = streetSuffix;
    }

    public String getStreetSuffixDescription() {

        return streetSuffixDescription;
    }

    public void setStreetSuffixDescription(String streetSuffixDescription) {

        this.streetSuffixDescription = streetSuffixDescription;
    }

    public String getApartmentNumber() {

        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {

        this.apartmentNumber = apartmentNumber;
    }

    public String getSpecialAddressCode() {

        return specialAddressCode;
    }

    public void setSpecialAddressCode(String specialAddressCode) {

        this.specialAddressCode = specialAddressCode;
    }

    public String getSpecialAddressNumber() {

        return specialAddressNumber;
    }

    public void setSpecialAddressNumber(String specialAddressNumber) {

        this.specialAddressNumber = specialAddressNumber;
    }

    public String getZipCode() {

        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
        if (zipCode != null && (zipCode.length() == 9 || zipCode.length() == 10))
            zipCodeExt = zipCode.substring(zipCode.length() - 4, zipCode.length());
    }

    public String getZipCodeExt() {

        return zipCodeExt;
    }

    public void setZipCodeExt(String zipCodeExt) {

        this.zipCodeExt = zipCodeExt;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getCounty() {

        return county;
    }

    public void setCounty(String county) {

        this.county = county;
    }

    public String getBarCode() {

        return barCode;
    }

    public void setBarCode(String barCode) {

        this.barCode = barCode;
    }

    public String getBuildingName() {

        return buildingName;
    }

    public void setBuildingName(String buildingName) {

        this.buildingName = buildingName;
    }

    public Boolean getActiveFlag() {

        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {

        this.activeFlag = activeFlag;
    }

    public String getAddressDescription() {

        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {

        this.addressDescription = addressDescription;
    }

    public String getActionCode() {

        return actionCode;
    }

    public void setActionCode(String actionCode) {

        this.actionCode = actionCode;
    }

    public String getAddressType() {

        return addressType;
    }

    public void setAddressType(String addressType) {

        this.addressType = addressType;
    }

    public boolean isSeasonalAddress() {

        return seasonalAddress;
    }

    public void setSeasonalAddress(boolean seasonalAddress) {

        this.seasonalAddress = seasonalAddress;
    }

    public boolean isAddAddress() {

        return addAddress;
    }

    public void setAddAddress(boolean addAddress) {

        this.addAddress = addAddress;
    }

    public String getLabelCaps() {

        if (addressType.equals("S")) {
            labelCaps = "Seasonal Address";
        }
        else {
            labelCaps = "Mailing Address";
        }
        return labelCaps;
    }

    public void setLabelCaps(String labelCaps) {

        this.labelCaps = labelCaps;
    }

    public String getLabelSmall() {

        if (addressType.equals("S")) {
            labelSmall = "seasonal address";
        }
        else {
            labelSmall = "mailing address";
        }
        return labelSmall;
    }

    public void setLabelSmall(String labelSmall) {

        this.labelSmall = labelSmall;
    }

    public boolean isAllowByPass() {

        return allowByPass;
    }

    public void setAllowByPass(boolean allowByPass) {

        this.allowByPass = allowByPass;
    }

    public String getRemoveAddress() {

        if (StringUtils.isBlank(removeAddress)) {
            removeAddress = "N";
        }
        return removeAddress;
    }

    public void setRemoveAddress(String removeAddress) {

        this.removeAddress = removeAddress;
    }

    public String getRrNumber() {

        return rrNumber;
    }

    public void setRrNumber(String rrNumber) {

        this.rrNumber = rrNumber;
    }

    public String getRrBoxNumber() {

        return rrBoxNumber;
    }

    public void setRrBoxNumber(String rrBoxNumber) {

        this.rrBoxNumber = rrBoxNumber;
    }

    public String getPoBoxNumber() {

        return poBoxNumber;
    }

    public void setPoBoxNumber(String poBoxNumber) {

        this.poBoxNumber = poBoxNumber;
    }

    public String getHouseNumber() {

        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {

        this.houseNumber = houseNumber;
    }

    public boolean isSaveAsOriginallyEntered() {

        return saveAsOriginallyEntered;
    }

    public void setSaveAsOriginallyEntered(boolean saveAsOriginallyEntered) {

        this.saveAsOriginallyEntered = saveAsOriginallyEntered;
    }

    public int getVerifyLevel() {

        return verifyLevel;
    }

    public void setVerifyLevel(int verifyLevel) {

        this.verifyLevel = verifyLevel;
    }

    public String getValidationStatus() {

        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {

        this.validationStatus = validationStatus;
    }

    public boolean isUseModifiedAddress() {

        return useModifiedAddress;
    }

    public void setUseModifiedAddress(boolean useModifiedAddress) {

        this.useModifiedAddress = useModifiedAddress;
    }

    public String getSubmitButton() {

        return submitButton;
    }

    public void setSubmitButton(String submitButton) {

        this.submitButton = submitButton;
    }

    public long getAccountId() {

        return accountId;
    }

    public void setAccountId(long accountId) {

        this.accountId = accountId;
    }

    public String getAction() {

        if (actionCode.equals("A")) {
            action = "added";
        }
        else {
            action = "updated";
        }
        return action;
    }

    public void setAction(String action) {

        this.action = action;
    }

}
