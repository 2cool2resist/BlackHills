package com.bhc.startstop.web.model;





import java.time.LocalDate;

import com.bhc.startstop.model.AddressComponent;

public class PendingOrder {
    private Long orderId;
	private LocalDate orderDate;
    private String utilityType;
    private AddressComponent address;
    
   
	public PendingOrder(Long orderId, LocalDate orderDate2, String utilityType, AddressComponent address) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate2;
		this.utilityType = utilityType;
		this.address = address;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getUtilityType() {
		return utilityType;
	}
	public void setUtilityType(String utilityType) {
		this.utilityType = utilityType;
	}
	public AddressComponent getAddress() {
		return address;
	}
	 public void setAddress(AddressComponent address) {
			this.address = address;
		}

    
}
