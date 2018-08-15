package com.bhc.startstop.model;

import java.io.Serializable;

public class ServiceAgreement implements Serializable {

	private static final long serialVersionUID = -4949636323622207815L;

	public ServiceAgreement(String pREMISEID, String sAENDDT, String sPID, String rATEDSCR) {
		super();
		PREMISEID = Long.parseLong(pREMISEID);
		SAENDDT = sAENDDT;
		SPID = Long.parseLong(sPID);
		RATEDSCR = rATEDSCR;
	}
	private Long PREMISEID;
    private String SAENDDT;
    private Long SPID;
    private String RATEDSCR;

    public String getRATEDSCR() {
		return RATEDSCR;
	}
	public void setRATEDSCR(String rATEDSCR) {
		RATEDSCR = rATEDSCR;
	}
	public Long getPREMISEID() {
		return PREMISEID;
	}
	public void setPREMISEID(String pREMISEID) {
		PREMISEID = Long.parseLong(pREMISEID);
	}
	public String getSAENDDT() {
		return SAENDDT;
	}
	public void setSAENDDT(String sAENDDT) {
		SAENDDT = sAENDDT;
	}
	public Long getSPID() {
		return SPID;
	}
	public void setSPID(String sPID) {
		SPID = Long.parseLong(sPID);
	}

	
}
