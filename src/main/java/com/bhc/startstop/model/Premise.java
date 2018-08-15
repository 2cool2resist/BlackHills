package com.bhc.startstop.model;

import java.io.Serializable;
import java.util.List;

public class Premise implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long premiseId;
	private String address;

	public Long getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(Long premiseId) {
		this.premiseId = premiseId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
