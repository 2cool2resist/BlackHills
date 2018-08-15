package com.bhc.startstop.cis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Defines a Premise Search Result object
 * 
 *  When we search for a premise, we are going to show a full address value.  We want to convert this
 *  to a premise id.
 *  
 *  Premise is stored in our backend by premise id where multiple premise ids can have the same address.
 *  For web display, we want to pivot this data so that one address has multiple premise ids.  This objects
 *  allows us to store that pivot.
 *  
 *  When this object is sent to a client (ajax call), it will be formatted as a JSON object.  The
 *  JSON ultimately gets consumed with a jQuery Autocomplete object which handles a structure of label/value 
 *  items.  Our autocomplete implementation uses the address as both the label and value (hence is mapped to 
 *  value) and will store premise id list to an array of hidden input elements (hence is mapped to id) 
 * @author bblom
 *
 */
public class PremiseSearchResult implements Serializable {

	private static final long serialVersionUID = -8936064556394933987L;
	private List<Long> premiseList;
	private String address;
	
	public PremiseSearchResult() {
		premiseList = new ArrayList<Long>();
	}
	
	public PremiseSearchResult(String address, Long premiseId) {
		this.address = address;
		premiseList = new ArrayList<Long>();
		premiseList.add(premiseId);
	}
	
	@JsonProperty(value="id")
	public List<Long> getPremiseList() {
		return premiseList;
	}
	public void addPremiseId(Long premiseId) {
		if(premiseList == null)
			premiseList = new ArrayList<Long>();
		premiseList.add(premiseId);
	}
	
	@JsonProperty(value="value")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}