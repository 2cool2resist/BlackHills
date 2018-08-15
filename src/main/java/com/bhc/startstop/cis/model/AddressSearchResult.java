package com.bhc.startstop.cis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressSearchResult implements Serializable {

	private static final long serialVersionUID = -8936064556394933987L;
	private List<Long> premiseList;
	private String address;
	private String addressLine1;
	private String city;
	private String state;
	private String zip;
	
	public AddressSearchResult() {
		premiseList = new ArrayList<Long>();
	}
	
	public AddressSearchResult(String address, Long premiseId, String addressLine1, String city, String state, String zip) {
		this.address = address;
		premiseList = new ArrayList<Long>();
		premiseList.add(premiseId);
		this.addressLine1 = addressLine1;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	@JsonProperty(value="id")
	public List<Long> getPremiseList() {
		return premiseList;
	}
	public void addPremiseId(Long premiseId) {
		if(premiseList == null)
			premiseList = new ArrayList<Long>();
		premiseList.add(premiseId);
	}
	

	@JsonProperty(value="value")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty(value="value1")
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
}