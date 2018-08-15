package com.bhc.startstop.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.StringUtils;

@Embeddable
public class Address implements Serializable {
    // Address is included in published objects, so all fields can be seen by the public
	
	// per Dan Brewer:  for former address on person, everything just goes in street name:
	//   street name = "PO BOX 333" etc

    private static final long serialVersionUID = 8778006725792042725L;

    // all fields are set to uppercase in the setter

    @Column(name = "ADDRESS1")
    private String address1;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;
    
    @Column(name = "PROVINCE")
    private String province;
    
    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PHONE")
    private String phone;

    public Address() {
    }

    public Address(Address copy) {
        this.address1 = copy.getAddress1();
        this.address2 = copy.getAddress2();
        this.city = copy.getCity();
        this.state = copy.getState();
        this.zip = copy.getZip();
        this.province = copy.getProvince();
        this.country = copy.getCountry();
        this.phone = copy.getPhone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ")
                .append("address1:\"").append(address1).append("\", ")
                .append("address2:\"").append(address2).append("\", ")
                .append("city:\"").append(city).append("\", ")
                .append("state:\"").append(state).append("\", ")
                .append("zip:\"").append(zip).append("\", ")
                .append("province:\"").append(province).append("\", ")
                .append("country:\"").append(country).append("\", ")
                .append("phone:\"").append(phone).append("\"}");
        return sb.toString();
    }
    
    public String formatAddress() {
    	StringBuilder fa = new StringBuilder();
    	if(StringUtils.isNotEmpty(address1))
    		fa.append(address1).append(" ");
    	if(StringUtils.isNotEmpty(address2))
    		fa.append(address2).append(" ");
    	if(StringUtils.isNotEmpty(city))
    		fa.append(city).append(", ");
    	if(StringUtils.isNotEmpty(state))
    		fa.append(state).append(" ");
    	if(StringUtils.isNotEmpty(zip))
    		fa.append(zip);
    	if(StringUtils.isNotEmpty(province))
    		fa.append(province).append(" ");
    	if(StringUtils.isNotEmpty(country))
    		fa.append(country);
    	
    	if(fa.length() == 0)
    		return null;
    	else
    		return fa.toString();
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = StringUtils.upperCase(address1);
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = StringUtils.upperCase(address2);
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = StringUtils.upperCase(city);
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = StringUtils.upperCase(state);
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = StringUtils.upperCase(zip);
    }
    public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = StringUtils.upperCase(province);
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = StringUtils.upperCase(country);
	}
	public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = StringUtils.upperCase(phone);
    }

}