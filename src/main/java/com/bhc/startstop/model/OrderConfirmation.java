package com.bhc.startstop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bhc.startstop.webservice.model.ClickTimeslot;

/**
 * Defines an existing service appointment
 * 
 * This class is sent to the client, all fields must be public
 *
 */
public class OrderConfirmation implements Serializable, Comparable<OrderConfirmation> {
	
	private static final long serialVersionUID = -6152338443291947639L;
	private Long accountId;
	private Long premiseId;
	private ClickTimeslot timeslot;
	private Long orderId;
	private Boolean insideAccessRequired; // redundant with timeslot.insideAccessRequired, but populating both
	private List<Long> servicePoints;
	
	public OrderConfirmation() {
		insideAccessRequired = false;
		servicePoints = new ArrayList<Long>();
	}

	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getPremiseId() {
		return premiseId;
	}
	public void setPremiseId(Long premiseId) {
		this.premiseId = premiseId;
	}
	public ClickTimeslot getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(ClickTimeslot timeslot) {
		this.timeslot = timeslot;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Boolean isInsideAccessRequired() {
		return insideAccessRequired;
	}
	public void setInsideAccessRequired(Boolean isInsideAccessRequired) {
		this.insideAccessRequired = isInsideAccessRequired;
	}
	public List<Long> getServicePoints() {
		return servicePoints;
	}
	
	/**
	 * OrderConfirmations are considered equal if the timeslots are the same
	 */
    @Override
    public int compareTo(OrderConfirmation another) {
        if (another != null && this.timeslot.isBefore(another.getTimeslot()))
            return -1;
        else if(!this.timeslot.isAfter(another.getTimeslot()))
        	return 0;
        else
            return 1;

    }
}