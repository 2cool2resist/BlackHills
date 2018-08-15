package com.bhc.startstop.web.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.bhc.startstop.cis.constants.AppointmentTimeslots;
import com.bhc.startstop.cis.model.ServicePoint;
import com.bhc.startstop.webservice.model.ClickTimeslot;

@Entity
@Table(name = "START_STOP_SERVICE")
public class UtilityService implements Serializable{

    private static final long serialVersionUID = -1092140684656803711L;

    @Id
    @Column(name = "SERVICE_ID", updatable = false, nullable = false)
    @GenericGenerator(name = "service_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "START_STOP_SERVICE_ID_SEQ"),
            @Parameter(name = "increment_size", value = "1") })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_sequence")
    private Long id;

    // foreign key: ACCOUNT_ID

    @Column(name = "CIS_PREMISE_ID", nullable = false)
    private Long premiseId;   // used in scheduling

    @Column(name = "CIS_SP_ID", nullable = false)
    private Long servicePointId;

    @Column(name = "MTR_NBR")
    private String meterId;

    @Column(name = "MTR_LOC")
    private String meterLocationCode;

    @Column(name = "METER_DESCRIPTION")
    private String meterLocationDescription;   // using the short description value from CIS

    @Column(name = "METER_INSIDE")
    @Type(type = "yes_no")
    private Boolean meterInside;

    @Column(name = "GRANDFATHERED_RATE")
    @Type(type = "yes_no")
    private Boolean grandfatheredRate;

    @Column(name = "CUST_CLASS")
    // @Type(type = "yes_no")
    private String customerClass; // uses values from CIS
    
    @Column(name = "VALID_WEB")
    @Type(type = "yes_no")
    private Boolean validForWeb;

    @Column(name = "SCHEDULED_DATE")
    private Date scheduledDate;

    @Column(name = "SCHEDULED_TIMESLOT")
    private String scheduledTimeslot;
    
    @Column(name="ORDER_SUBTYPE")
    private String orderSubtype;
    
    @Column(name="INSIDE_ACCESS")
    @Type(type="yes_no")
    private Boolean insideAccessRequired;
    
    @Column(name = "START_ORDER_ID")
    private Long startOrderId;
    
    @Column(name="STOP_ORDER_ID")
    private Long stopOrderId;
    
    @Transient
    private String nonUtilityName;

    public String getNonUtilityName() {
		return nonUtilityName;
	}

	public void setNonUtilityName(String nonUtilityName) {
		this.nonUtilityName = nonUtilityName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ServiceAppointment.class, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "SERVICE_ID", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ServiceAppointment> appointmentOptions;

    public UtilityService() {
    	this.validForWeb = true;   // initialize to true
        appointmentOptions = new ArrayList<ServiceAppointment>();
    }

    public UtilityService(ServicePoint servicePoint) {
        // this.id
        this.servicePointId = servicePoint.getServicePointId();
        this.premiseId = servicePoint.getPremiseId();
        this.meterId = servicePoint.getMeterId();
        this.meterLocationCode = servicePoint.getMeterLocationCode();
        this.meterLocationDescription = servicePoint.getMeterLocationShortDescription();
        this.customerClass = servicePoint.getCustomerClass();
        if(servicePoint.isMasterDeduct() != null && servicePoint.isMasterDeduct() == true)
        	this.validForWeb = false; // master deduct meters are not valid for web turnon/turnoff
        else
        	this.validForWeb = true;
        if(servicePoint.isMeterInside())
        	this.meterInside = true;
        else
        	this.meterInside = null; // we will use premSPAuth to set meter inside
        this.grandfatheredRate = null;
        this.scheduledDate = null;
        this.scheduledTimeslot = null;
        this.orderSubtype = null;
        this.startOrderId = null;
        this.stopOrderId = null;

        appointmentOptions = new ArrayList<ServiceAppointment>();
    }

    public String getDisplayDescription() {
        return getDisplayDescription(true);
    }

    public String getDisplayDescription(boolean includeMeterLabelPrefix) {
        String description = includeMeterLabelPrefix ? "Meter Number " + meterId : meterId;
        if (StringUtils.isNotEmpty(meterLocationDescription))
            description = description + " " + meterLocationDescription;
        if(StringUtils.isNotEmpty(nonUtilityName))
        	return nonUtilityName;
        else
        	return description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getServicePointId() {
        return servicePointId;
    }
    public void setServicePointId(Long servicePointId) {
        this.servicePointId = servicePointId;
    }

    public Long getPremiseId() {
        return premiseId;
    }
    public void setPremiseId(Long premiseId) {
        this.premiseId = premiseId;
    }

    public String getMeterId() {
        return meterId;
    }
    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getMeterLocationCode() {
        return meterLocationCode;
    }

    public void setMeterLocationCode(String meterLocationCode) {
        this.meterLocationCode = meterLocationCode;
    }

    public String getMeterLocationDescription() {
        return meterLocationDescription;
    }

    public void setMeterLocationDescription(String meterLocationDescription) {
        this.meterLocationDescription = meterLocationDescription;
    }

    public Boolean isMeterInside() {
        return meterInside;
    }

    public void setMeterInside(Boolean meterInside) {
        this.meterInside = meterInside;
    }

    public Boolean isGrandfatheredRate() {
        return grandfatheredRate;
    }

    public void setGrandfatheredRate(Boolean grandfatheredRate) {
        this.grandfatheredRate = grandfatheredRate;
    }

    public String getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }
    
    public Boolean isValidForWeb() {
		return validForWeb;
	}

	public void setValidForWeb(Boolean validForWeb) {
		this.validForWeb = validForWeb;
	}

	public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getScheduledTimeslot() {
        return scheduledTimeslot;
    }

    public void setScheduledTimeslot(String scheduledTimeslot) {
        this.scheduledTimeslot = scheduledTimeslot;
    }

    public void setScheduledDateTime(ClickTimeslot timeslot) {
        this.scheduledDate = Date.valueOf(timeslot.getDate());
        this.scheduledTimeslot = timeslot.getTimeslot().name();
    }
    
    public String getScheduledTimeslotDisplay() {
    	if(StringUtils.isNotBlank(this.scheduledTimeslot)) {
    		AppointmentTimeslots code = AppointmentTimeslots.valueOf(this.scheduledTimeslot);
    		return code.getDisplay();
    	}
    	else
    		return null;
    }
    
    public String getOrderSubtype() {
		return orderSubtype;
	}

	public void setOrderSubtype(String orderSubtype) {
		this.orderSubtype = orderSubtype;
	}
	
	public Boolean isInsideAccessRequired() {
		return insideAccessRequired;
	}

	public void setInsideAccessRequired(Boolean insideAccessRequired) {
		this.insideAccessRequired = insideAccessRequired;
	}

	public Long getStartOrderId() {
		return startOrderId;
	}

	public void setStartOrderId(Long startOrderId) {
		this.startOrderId = startOrderId;
	}
	
	public Long getStopOrderId() {
		return stopOrderId;
	}

	public void setStopOrderId(Long stopOrderId) {
		this.stopOrderId = stopOrderId;
	}
	
	public List<ServiceAppointment> getAppointmentOptions() {
		return this.appointmentOptions;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof UtilityService))
            return false;
        if (((UtilityService) obj).getId() == this.id)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}