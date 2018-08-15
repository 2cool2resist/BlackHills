package com.bhc.startstop.cis.constants;

import com.bhc.startstop.exceptions.InvalidTimeslotException;

public enum AppointmentTimeslots {
	// values are listed in order:  AM < DAY < PM, specific logic is in ClickTimeslot.java
	AM("morning"), 
	FD("all-day"),
	PM("afternoon"); 
	
    private String display;

    /**
     * Value displayed on the web
     * 
     * @param display
     */
    private AppointmentTimeslots(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return this.display;
    }
    
    static public String display(String code) {
    	return AppointmentTimeslots.valueOf(code).getDisplay();
    }
    
    public String emailDisplay() {
    	if(this.equals(AM))
    		return "8:00 a.m. - 12:00 p.m.";
    	else if(this.equals(PM))
    		return "12:30 p.m. - 5:00 p.m.";
    	else // full day or invalid
    		return "8:00 a.m. - 5:00 p.m.";
    				
    }

    public static AppointmentTimeslots convertFromCis(String start, String end) throws InvalidTimeslotException{ 
		if(start == null || end == null)
			throw new InvalidTimeslotException("Unable to convert times: " + start + " and " + end);
		else if(start.equals("0800") && end.equals("1200"))
			return AM;
		else if(start.equals("1230") && end.equals("1700"))
			return PM;
		else if(start.equals("0800") && end.equals("1700"))
			return FD;
		else
			throw new InvalidTimeslotException("Unable to convert times: " + start + " and " + end);
	}
}

	
