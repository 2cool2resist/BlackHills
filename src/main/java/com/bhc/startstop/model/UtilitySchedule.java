package com.bhc.startstop.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bhc.startstop.cis.constants.AppointmentTimeslots;
import com.bhc.startstop.cis.constants.UtilityTypeCode;
import com.bhc.startstop.util.DateUtils;

public class UtilitySchedule implements Serializable {

    private static final long serialVersionUID = -6141266426544831924L;

    private String utility;
    private Integer serviceCount;
    private String firstDate;
    private String firstTime;
    private List<ScheduleAppointment> combined;
    private List<ServicePointSchedule> individual;

    public UtilitySchedule() {
        combined = new ArrayList<ScheduleAppointment>();
        individual = new ArrayList<ServicePointSchedule>();
    }
    
    public Boolean hasInsideAccess() {
    	Boolean insideAccess = false;
    	for(ScheduleAppointment appt : combined)
    		if(appt.isInsideAccessRequired())
    			insideAccess = true;
    	for(ServicePointSchedule schedule : individual)
    		for(ScheduleAppointment appt: schedule.getAppointments())
    			if(appt.isInsideAccessRequired())
    				insideAccess = true;
    	return insideAccess;
    }

    public String getUtility() {
        return utility;
    }
    public void setUtility(String utility) {
        this.utility = utility;
    }
    public void setUtility(UtilityTypeCode utilityTypeCode) {
        this.utility = utilityTypeCode.name();
    }

    public Integer getServiceCount() {
        return serviceCount;
    }
    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public String getFirstDate() {
        return firstDate;
    }
    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }
    public void setFirstDate(LocalDate date) {
        this.firstDate = DateUtils.toDisplayFormat(date);
    }

    public String getFirstTime() {
        return firstTime;
    }
    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }
    public void setFirstTime(AppointmentTimeslots timeslot) {
        this.firstTime = timeslot.name();
    }

    public List<ScheduleAppointment> getCombined() {
        return combined;
    }

    public List<ServicePointSchedule> getIndividual() {
        return individual;
    }
    public void addIndividual(ServicePointSchedule service) {
        individual.add(service);
    }

}