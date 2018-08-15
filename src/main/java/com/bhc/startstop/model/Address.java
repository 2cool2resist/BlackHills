package com.bhc.startstop.model;

import java.io.Serializable;
import java.util.List;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	private String address;
	private boolean isRestricted;
	private List<Long> premiseIds;
	
	public boolean isRestricted() {
		return isRestricted;
	}

	public List<Long> getPremiseIds() {
		return premiseIds;
	}

	public void setPremiseIds(List<Long> premiseIds) {
		this.premiseIds = premiseIds;
	}

	public void setRestricted(boolean isRestricted) {
		this.isRestricted = isRestricted;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}