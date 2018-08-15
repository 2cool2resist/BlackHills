package com.bhc.startstop.cis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Defines a Premise object
 * Holds premise data coming out of CIS+
 */
@Entity
@Table(name = "CIS_PREMSP_MV")
public class Premise implements Serializable {

    private static final long serialVersionUID = 8608394921362232190L;

    @Id
    @Column(name = "PREMISE_ID")
    private Long premiseId;

    @Column(name = "COY")
    private Integer coy;

    @Column(name = "STREET_ADDR")
    private String address;

    @Column(name = "BUILDING_NAME")
    private String buildingName;

    @Column(name = "MAIL_CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    // used when copying record over to mailing address
    @Column(name = "ST_NBR")
    private String streetNumber;

    @Column(name = "ST_PRE_DIR_CODE")
    private String streetPredirection;

    @Column(name = "ST_NAME")
    private String streetName;

    @Column(name = "ST_POST_DIR_CD")
    private String streetPostdirection;

    @Column(name = "ST_SUFFIX")
    private String streetSuffix;

    @Column(name = "APT_NBR")
    private String apartmentNumber;

    @Column(name = "ZIP_CODE")
    private String zipCode;
    
    @Column(name="REPORTING_GROUP")
    private String reportingGroup;

    // CITY, PREMISE_LOCATION, APT_BLD_ADDR

    public Premise() {
    }

    public Premise(Long premiseId, Integer coy, String address, String buildingName, String city, String state) {
        this.premiseId = premiseId;
        this.coy = coy;
        this.address = address;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
    }

    public Long getPremiseId() {
        return premiseId;
    }
    public void setPremiseId(Long premiseId) {
        this.premiseId = premiseId;
    }

    public Integer getCoy() {
        return coy;
    }
    public void setCoy(Integer coy) {
        this.coy = coy;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public String getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetPredirection() {
        return streetPredirection;
    }
    public void setStreetPredirection(String streetPredirection) {
        this.streetPredirection = streetPredirection;
    }

    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetPostdirection() {
        return streetPostdirection;
    }
    public void setStreetPostdirection(String streetPostdirection) {
        this.streetPostdirection = streetPostdirection;
    }

    public String getStreetSuffix() {
        return streetSuffix;
    }
    public void setStreetSuffix(String streetSuffix) {
        this.streetSuffix = streetSuffix;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }
    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

	public String getReportingGroup() {
		return reportingGroup;
	}
	public void setReportingGroup(String reportingGroup) {
		this.reportingGroup = reportingGroup;
	}
    
    

}