package com.bhc.startstop.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.bhc.startstop.cis.constants.AppointmentTimeslots;
import com.bhc.startstop.util.DateUtils;
import com.bhc.startstop.webservice.model.ClickTimeslot;

/**
 * This class is sent to the client, all fields must be viewable
 * 
 */
public class ScheduleAppointment implements Serializable, Comparable<ScheduleAppointment> {

    private static final long serialVersionUID = 565040350856667820L;

    private String requestedDate;
    private String requestedTime;
    private String requestedTimeDisplay;
    private Boolean insideAccessRequired;
    
    private ClickTimeslot getClickTimeslot() {
    	if(!StringUtils.isEmpty(this.requestedDate) && !StringUtils.isEmpty(this.requestedTime)) {
    		ClickTimeslot timeslot = new ClickTimeslot(); // note, this isn't fully populated, just date/time so keeping private
    		timeslot.setDate(DateUtils.fromDisplayFormat(this.requestedDate));
    		timeslot.setTimeslot(AppointmentTimeslots.valueOf(requestedTime));
    		return timeslot;
    	}
    	else
    		return null;
    }

    public ScheduleAppointment() {}

    public ScheduleAppointment(ClickTimeslot timeslot, Boolean isInsideAccessRequired) {
        this.requestedDate = timeslot.getDateDisplay();
        this.requestedTime = timeslot.getTimeslot().name();
        this.requestedTimeDisplay = timeslot.getTimeslot().getDisplay();
        this.insideAccessRequired = isInsideAccessRequired;
    }

    /**
     * returns true if the date and timeslot from the specified timeslot matches the
     * requestedDate and requestedTime of this date and time
     * 
     * @return
     */
    public Boolean isTimeslotEqual(ClickTimeslot timeslot) {
    	ClickTimeslot thisTimeslot = getClickTimeslot();
    	return thisTimeslot.equals(timeslot);
    }
    
    /**
     * Compares the click timeslots
     * A ScheduleApointment having the same date/time but different inside access is still equal
     */
    @Override
    public int compareTo(ScheduleAppointment another) {
    	ClickTimeslot thisTimeslot = getClickTimeslot();
    	return thisTimeslot.compareTo(another.getClickTimeslot()); 
    }

    public String getRequestedDate() {
        return requestedDate;
    }
    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getRequestedTime() {
        return requestedTime;
    }
    public void setRequestedTime(String requestedTime) {
        this.requestedTime = requestedTime;
    }
    public String getRequestedTimeDisplay() {
    	return requestedTimeDisplay;
    }

	public Boolean isInsideAccessRequired() {
		return insideAccessRequired;
	}
	public void setInsideAccessRequired(Boolean insideAccessRequired) {
		this.insideAccessRequired = insideAccessRequired;
	}
    
}