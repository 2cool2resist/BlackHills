package com.bhc.startstop.webservice.model;

import java.time.LocalDate;

import com.bhc.startstop.cis.constants.AppointmentTimeslots;
import com.bhc.startstop.exceptions.InvalidTimeslotException;
import com.bhc.startstop.model.AddressComponent;
import com.bhc.startstop.util.DateUtils;

/**
 * Holds an existing Start/Stop order from CIS
 * populated from pendingOrderInfo and orderHeaderInfo
 * 
 * @author bblom
 *
 */
public class Order {
	// populated from pendingOrderInfo
    private Integer companyId;
    private Long orderId;
    private String status;  // P = Pending
    private Long accountId;
    private Long premiseId;
    private OrderType orderType;
    private String subType;
    private LocalDate orderDate;
    private Long servicePointId;
    private AddressComponent address;
    private Long orderHeaderId;
    // populated from orderHeaderInfo
    private String appointmentStart;
    private String appointmentEnd;
    private AddressComponent forwardingAddress;
    private LocalDate forwardingAddressValidationDate;

    public Order() {
    }

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = Integer.valueOf(companyId);
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = Long.valueOf(orderId);
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = Long.valueOf(accountId);
    }

    public Long getPremiseId() {
        return premiseId;
    }
    public void setPremiseId(Long premiseId) {
        this.premiseId = premiseId;
    }
    public void setPremiseId(String premiseId) {
        this.premiseId = Long.valueOf(premiseId);
    }

    public OrderType getOrderType() {
        return orderType;
    }
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = OrderType.valueOf(orderType.toUpperCase());
    }

    public String getSubType() {
        return subType;
    }
    public void setSubType(String subType) {
        this.subType = subType;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public void setOrderDate(String cisFormattedDate) {
        this.orderDate = DateUtils.fromCisFormat(cisFormattedDate);
    }

    public Long getServicePointId() {
        return servicePointId;
    }
    public void setServicePointId(Long servicePointId) {
        this.servicePointId = servicePointId;
    }
    public void setServicePointId(String servicePointId) {
        this.servicePointId = Long.valueOf(servicePointId);
    }

    public Long getOrderHeaderId() {
        return orderHeaderId;
    }
    public void setOrderHeaderId(Long orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    };
    public void setOrderHeaderId(String orderHeaderId) {
        this.orderHeaderId = Long.valueOf(orderHeaderId);
    }

	public String getAppointmentStart() {
		return appointmentStart;
	}
	public void setAppointmentStart(String appointmentStart) {
		this.appointmentStart = appointmentStart;
	}

	public String getAppointmentEnd() {
		return appointmentEnd;
	}
	public void setAppointmentEnd(String appointmentEnd) {
		this.appointmentEnd = appointmentEnd;
	}
	
	public AppointmentTimeslots getAppointmentTimeslot() throws InvalidTimeslotException {
		if(appointmentStart != null && appointmentEnd != null)
			return AppointmentTimeslots.convertFromCis(appointmentStart, appointmentEnd);
		else
			return null;
	}

	public AddressComponent getAddress() {
		return address;
	}
	public void setAddress(AddressComponent address) {
		this.address = address;
	}
	
	public AddressComponent getForwardingAddress() {
		return forwardingAddress;
	}
	public void setForwardingAddress(AddressComponent forwardingAddress) {
		this.forwardingAddress = forwardingAddress;
	}

	public LocalDate getForwardingAddressValidationDate() {
		return forwardingAddressValidationDate;
	}
	public void setForwardingAddressValidationDate(LocalDate validationDate) {
		this.forwardingAddressValidationDate = validationDate;
	}
}