package com.bhc.startstop.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bhc.startstop.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is sent to the client
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true) // ignore json fields not defined here (insideAccessRequired is ignored)
public class ServicePointSchedule implements Serializable {

    private static final long serialVersionUID = -642863988113173256L;

    private Long serviceId;
    private String serviceDescription;
    private String scheduledDate;             // holds previously selected date
    private String scheduledTime;             // holds previously selected time
    private String scheduledTimeDisplay;
    private List<ScheduleAppointment> appointments;

    public ServicePointSchedule() {
        appointments = new ArrayList<ScheduleAppointment>();
    }

    public ServicePointSchedule(Long id, String description) {
        this.serviceId = id;
        this.serviceDescription = description;
        this.appointments = new ArrayList<ScheduleAppointment>();
    }

    public Long getServiceId() {
        return serviceId;
    }
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }
    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
    public void setScheduledDate(Date date) {
        if (date != null)
            this.scheduledDate = DateUtils.toDisplayFormat(date.toLocalDate());
    }

    public String getScheduledTime() {
        return scheduledTime;
    }
    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    
    public String getScheduledTimeDisplay() {
    	return scheduledTimeDisplay;
    }
    public void setScheduledTimeDisplay(String displayValue) {
    	this.scheduledTimeDisplay = displayValue;
    }

    public List<ScheduleAppointment> getAppointments() {
        return appointments;
    }

}