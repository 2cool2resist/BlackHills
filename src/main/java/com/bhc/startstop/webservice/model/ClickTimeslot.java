package com.bhc.startstop.webservice.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.bhc.startstop.cis.constants.AppointmentTimeslots;
import com.bhc.startstop.exceptions.InvalidTimeslotException;
import com.bhc.startstop.util.DateUtils;

public class ClickTimeslot implements Serializable, Comparable<ClickTimeslot> {
    private static final long serialVersionUID = -5152291158337460756L;
    private LocalDate date;
    private AppointmentTimeslots timeslot;

    // order types for TON:  N, R, A, C, I, J
	// order types for TOFF:  J, O
    private String orderSubtype;

    // indicates if the appointment is from click or a spoofed appointment that bypasses click
	//  AMI and ERT meters are manually scheduled outside of click
    private Boolean clickAppt; 

    public ClickTimeslot() {
    }

    public ClickTimeslot(LocalDate date, AppointmentTimeslots timeslot) {
        this.date = date;
        this.timeslot = timeslot;
    }
    public ClickTimeslot(LocalDate date, String start, String end) throws InvalidTimeslotException {
        this.date = date;
        setTimeslot(start, end);
    }

    public Boolean isBefore(ClickTimeslot another) {
        if (this.date.isBefore(another.getDate()))
            return true;
        // same date, AM < DAY < PM
        else if (this.date.equals(another.getDate()) && this.timeslot.ordinal() < another.getTimeslot().ordinal())
            return true;
        else
            return false;
    }

    public Boolean isAfter(ClickTimeslot another) {
        if (this.date.isAfter(another.getDate()))
            return true;
        // same date, DAY is not after AM and PM is not after DAY but PM is after AM : PM > AM, DAY !> PM, DAY !> AM
        else if (this.date.equals(another.getDate()) &&
                this.timeslot.ordinal() <= AppointmentTimeslots.FD.ordinal()
                && another.getTimeslot() == AppointmentTimeslots.PM)
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(ClickTimeslot another) {
        if (this.isBefore(another))
            return -1;
        else
            return 1;

    }

    public boolean equals(Object obj) {
    	// don't worry about clickAppt or orderSubtype for purposes of equality
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ClickTimeslot))
            return false;
        if (((ClickTimeslot) obj).getDate() == null && this.date == null
                && ((ClickTimeslot) obj).getTimeslot() == null && this.timeslot == null)
            return true;
        if (((ClickTimeslot) obj).getDate().isEqual(this.date)
                && ((ClickTimeslot) obj).getTimeslot().equals(this.timeslot))
            return true;
        else
            return false;

    }
    
    
    public Boolean isClickAppt() {
		return clickAppt;
	}

	public void setClickAppt(Boolean clickAppt) {
		this.clickAppt = clickAppt;
	}
	
	public void setClickAppt(String clickAppt) {
		if(clickAppt.equalsIgnoreCase("Y"))
			this.clickAppt = true;
		else
			this.clickAppt = false;
	}

	public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getDateDisplay() {
    	if(date != null)
    		return DateUtils.toDisplayFormat(date);
    	else
    		return "";
    }

    public AppointmentTimeslots getTimeslot() {
        return timeslot;
    }
    public void setTimeslot(AppointmentTimeslots timeslot) {
        this.timeslot = timeslot;
    }
    public void setTimeslot(String start, String end) throws InvalidTimeslotException {
        this.timeslot = AppointmentTimeslots.convertFromCis(start, end);
    }

	public String getOrderSubtype() {
		return orderSubtype;
	}
	public void setOrderSubtype(String orderSubtype) {
		this.orderSubtype = orderSubtype;
	}
    
}