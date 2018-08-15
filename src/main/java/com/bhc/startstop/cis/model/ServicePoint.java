package com.bhc.startstop.cis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Where;

import com.bhc.startstop.cis.constants.UtilityTypeCode;

/**
 * Defines a Service Point object
 * Holds service point data coming out of CIS+
 */
@Entity
@Table(name = "CIS_PREMSP_MV")
public class ServicePoint implements Serializable {

    private static final long serialVersionUID = 334324925386776666L;

    public static final String SERVICE_TYPE_PRIVATE_AREA_LIGHT = "L";
    public static final String CUSTOMER_CLASS_RESIDENTIAL = "R";
    public static final String CUSTOMER_CLASS_UNKNOWN = "Z";
    /*public static final String MASTER_DEDUCT_METER_MASTER = "MAST";
    public static final String MASTER_DEDUCT_METER_SUBMETER = "SUBM";
    public static final String MASTER_DEDUCT_METER_MASC = "MASC";
    public static final String MASTER_DEDUCT_METER_SUBC = "SUBC";*/
    public static final String METER_LOC_CODE_INSIDE = "INS";
    
    @Id
    @Column(name = "SP_ID")
    private Long servicePointId;

    @Column(name = "PREMISE_ID")
    private Long premiseId;

    @Column(name = "COY", insertable = false, updatable = false)
    private Integer coy;

    @Column(name = "LOE")
    private String loe;

    /*@Column(name="AREA") 
    private Integer area;*/  // maps to CIS_AREA.AREA which contains LEGAL_OPER_ENTITY

    @Column(name = "UTIL_TYPE")
    @Where(clause = "utilityType = 'E' or utilityType = 'G' or utilityType = 'A'")
    private String utilityType;    // E = Electric, G = Gas, A = Appliance Repair, N = NonUtility, W = Water

    @Column(name = "RATE_CLASS")
    private String rateClass;
    /*@Column(name="RATE_AREA")
    private String rateArea;*/
    /*@Column(name="USE_CODE")
    private String useCode;*/
    @Column(name = "TYPE_OF_SERVICE")
    private String typeOfService;

    @Column(name = "MTR_IND")
    private String meterIndicated; // ??

    @Column(name = "MTR_NBR")
    private String meterId;
    /*@Column(name="SOURCE_STATUS")
    private String sourceStatus;*/  // where service can be cut (pole, main)
    /*@Column(name="MTR_AT_SP_ST")
    private String meterAt; // ?? */
    @Column(name = "MTR_LOC")
    private String meterLocationCode;  // maps to CIS_MTR_LOC_T and grabs DSCR, SHORT_DESC

    @Column(name = "MTR_LOC_DSCR")
    private String meterLocationDescription;
    	/*
    	 * 00
    	 * ALY - alley
    	 * BCK
    	 * BSM
    	 * EAS - outside east
    	 * ECH
    	 * EMT - easement
    	 * INS - inside
    	 * MNL
    	 * NOR - outside north
    	 * O5
    	 * OBD - outbuilding
    	 * OUT
    	 * POL - pole
    	 * SOU - outside south
    	 * TEL - telemetry
    	 * WES - outside west
    	 * 
    	 * 
    	 * 
    	 * 
    	 */

    @Column(name = "MTR_LOC_SHORT_DESC")
    private String meterLocationShortDescription;

    /*@Column(name="THEFT_INVEST_IND")
    private String theftInvestmentIndicator; //?? */
    /*@Column(name="LOCATION_DESC")
    private String locationDescription;*/
    /*@Column(name="LOCATION_DETAIL")
    private String locationDetail;*/
    /*@Column(name="RANK")
    private Integer rank; // RANK() OVER(PARTITION BY premise_id ORDER BY util_type, sp_id) */

    @Column(name = "CUST_CLASS")
    private String customerClass; // Z=can't determine, R=residential, C=commercial, X, M, D, J, U, G, I, A, W
    
    @Column(name = "MAST_SUBM_IND")
    private String masterSubmeterIndicator; // MAST, SUBM, MASC, SUBC

    public ServicePoint() {
    }

    /*public ServicePoint (Long servicePointId, Long premiseId, Long coy, 
    					 String utilityType, String rateClass, String typeOfService,
    					 String meterIndicated, String meterId, String meterLocation) {
    	this.servicePointId = servicePointId;
    	this.premiseId = premiseId;
    	this.coy = coy;
    	this.utilityType = utilityType;
    	this.rateClass = rateClass;
    	this.typeOfService = typeOfService;
    	this.meterIndicated = meterIndicated;
    	this.meterId = meterId;
    	this.meterLocation = meterLocation;
    }*/

    /**
     * Determines if the service point is a Private Area Light (PAL)
     * 
     * @return true or false
     */
    public Boolean isPal() {
        // PAL can be determined by RateClass = "LT" or "RL" or TypeOfService = "L"
        // we will check typeOfService
        if (typeOfService != null && typeOfService.equalsIgnoreCase(SERVICE_TYPE_PRIVATE_AREA_LIGHT))
            return true;
        else
            return false;
    }
    
    /**
     * Determines if the service point is a master/deduct meter
     * @return
     */
    public Boolean isMasterDeduct() {
    	if(StringUtils.isBlank(this.masterSubmeterIndicator))
    		return false;
    	else
    		return true; // non-null value means master/deduct
    }
    
    public Boolean isMeterInside() {
    	if(this.meterLocationCode != null && this.meterLocationCode.equals(METER_LOC_CODE_INSIDE))
    		return true;
    	else
    		return false;
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

    public Integer getCoy() {
        return coy;
    }
    public void setCoy(Integer coy) {
        this.coy = coy;
    }

    public String getLoe() {
        return loe;
    }
    public void setLoe(String loe) {
        this.loe = loe;
    }

    public String getUtilityType() {
        return utilityType;
    }
    public void setUtilityType(String utilityType) {
        this.utilityType = utilityType;
    }
    public String getUtilityTypeDescription() {
        UtilityTypeCode code = UtilityTypeCode.convertCode(utilityType);
        if (code != null)
            return code.getDescription();
        else
            return null;
    }
    public UtilityTypeCode getUtilityTypeCode() {
        UtilityTypeCode code = UtilityTypeCode.convertCode(utilityType);
        return code;
    }

    public String getRateClass() {
        return rateClass;
    }
    public void setRateClass(String rateClass) {
        this.rateClass = rateClass;
    }

    public String getTypeOfService() {
        return typeOfService;
    }
    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public String getMeterIndicated() {
        return meterIndicated;
    }
    public void setMeterIndicated(String meterIndicated) {
        this.meterIndicated = meterIndicated;
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

    public String getMeterLocationShortDescription() {
        return meterLocationShortDescription;
    }
    public void setMeterLocationShortDescription(String meterLocationShortDescription) {
        this.meterLocationShortDescription = meterLocationShortDescription;
    }

    public String getCustomerClass() {
        return customerClass;
    }
    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

	public String getMasterSubmeterIndicator() {
		return masterSubmeterIndicator;
	}
	public void setMasterSubmeterIndicator(String masterSubmeterIndicator) {
		this.masterSubmeterIndicator = masterSubmeterIndicator;
	}

}