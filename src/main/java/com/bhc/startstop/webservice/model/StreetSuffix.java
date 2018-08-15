package com.bhc.startstop.webservice.model;

import java.io.Serializable;

public class StreetSuffix implements Serializable {

	private static final long serialVersionUID = -8130669881212438341L;
	private String suffixCode;
    private String longDescription;
    private String shortDescription;

    public StreetSuffix() {
    }

    public StreetSuffix(String code, String shortDescr, String longDescr) {
        this.suffixCode = code;
        this.shortDescription = shortDescr;
        this.longDescription = longDescr;
    }

	public String getSuffixCode() {
		return suffixCode;
	}

	public void setSuffixCode(String suffixCode) {
		this.suffixCode = suffixCode;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

}