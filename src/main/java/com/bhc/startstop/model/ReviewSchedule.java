package com.bhc.startstop.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.bhc.startstop.cis.constants.UtilityTypeCode;
import com.bhc.startstop.web.model.UtilityService;

public class ReviewSchedule {

	private Date scheduleDate;
	private Map<UtilityTypeCode, List<UtilityService>> services;
	
	public ReviewSchedule() {}

	public ReviewSchedule(Date scheduleDate, Map<UtilityTypeCode, List<UtilityService>> services) {
		this.scheduleDate = scheduleDate;
		this.services = services;
	}
	
	public Date getScheduleDate() {
		return scheduleDate;
	}
	
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	public Map<UtilityTypeCode, List<UtilityService>> getServices() {
		return services;
	}
	
	public void setServices(Map<UtilityTypeCode, List<UtilityService>> services) {
		
		this.services = services;
	}
}
