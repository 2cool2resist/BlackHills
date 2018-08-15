package com.bhc.startstop.web.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.bhc.startstop.webservice.model.ClickTimeslot;

@Entity
@Table(name = "START_STOP_SERVICE_APPT")
public class ServiceAppointment implements Serializable {

	private static final long serialVersionUID = 6220456919634345344L;

    @Id
    @Column(name = "APPT_ID", updatable = false, nullable = false)
    @GenericGenerator(name = "appointment_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "START_STOP_SVCAPT_ID_SEQ"),
            @Parameter(name = "increment_size", value = "1") })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Long id;

    // foreign key: SERVICE_ID
	
	// mirrors ClickTimeslot - we are saving the timeslot data so we can 
	// retrieve orderSubtype and clickApointment

	@Column(name = "APPOINTMENT_DATE")
    private Date appointmentDate;
    
    @Column(name = "APPOINTMENT_TIMESLOT")
    private String appointmentTimeslot;
    
    @Column(name = "ORDER_SUBTYPE")
    private String orderSubtype;
    
    @Column(name = "FROM_CLICK")
    @Type(type = "yes_no")
    private Boolean clickAppointment;



    public ServiceAppointment() {}

    public ServiceAppointment(ClickTimeslot timeslot) {
        appointmentDate = Date.valueOf(timeslot.getDate());
        appointmentTimeslot = timeslot.getTimeslot().name();
        this.orderSubtype = timeslot.getOrderSubtype();
        this.clickAppointment = timeslot.isClickAppt();
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTimeslot() {
		return appointmentTimeslot;
	}

	public void setAppointmentTimeslot(String appointmentTimeslot) {
		this.appointmentTimeslot = appointmentTimeslot;
	}

	public String getOrderSubtype() {
		return orderSubtype;
	}

	public void setOrderSubtype(String orderSubtype) {
		this.orderSubtype = orderSubtype;
	}
	
	public Boolean isClickAppointment() {
		return clickAppointment;
	}
	
	public void setClickAppointment(Boolean isClickAppointment) {
		this.clickAppointment = isClickAppointment;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof ServiceAppointment))
            return false;
        if (((ServiceAppointment) obj).getAppointmentDate().equals(this.appointmentDate)
        	 && ((ServiceAppointment) obj).getAppointmentTimeslot().equals(this.appointmentTimeslot)
        	 && ((ServiceAppointment) obj).getOrderSubtype().equals(this.orderSubtype)
        	 && ((ServiceAppointment) obj).isClickAppointment() == this.clickAppointment )
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.appointmentDate);
        builder.append(this.appointmentTimeslot);
        builder.append(this.orderSubtype);
        return builder.toHashCode();
    }
}