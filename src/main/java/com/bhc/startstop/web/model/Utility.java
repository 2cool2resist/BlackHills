package com.bhc.startstop.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bhc.startstop.cis.constants.UtilityTypeCode;

@Entity
@Table(name = "START_STOP_UTILITY")
public class Utility implements Serializable {

    private static final long serialVersionUID = -8532274476592775684L;

    @Id
    @Column(name = "UTILITY_ID", updatable = false, nullable = false)
    @GenericGenerator(name = "utility_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "START_STOP_UTILITY_ID_SEQ"),
            @Parameter(name = "increment_size", value = "1") })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utility_sequence")
    private Long id;
    @Column(name = "UTILITY_TYPE_CD", nullable = false)
    private String typeCode;
    // foreign key: REQUEST_ID

    @Column(name = "LEGAL_OPERATING_ENTITY")
    private String legalOperatingEntity;
    
    @Column(name = "CONNECT_CHARGE")
    private BigDecimal connectCharge;
    
    @Column(name = "CONNECT_CHARGE_TYPE")
    private String connectChargeType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = UtilityService.class, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "UTILITY_ID", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<UtilityService> services;

    public Utility() {
        services = new ArrayList<UtilityService>();
    }

    public Utility(String utilityTypeCd) {
        // this.id
        typeCode = utilityTypeCd;
        services = new ArrayList<UtilityService>();
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String utilityTypeCode) {
        this.typeCode = utilityTypeCode;
    }
    public UtilityTypeCode getUtilityTypeCode() {
        return UtilityTypeCode.convertCode(typeCode);
    }

    public String getLegalOperatingEntity() {
        return legalOperatingEntity;
    }
    public void setLegalOperatingEntity(String legalOperatingEntity) {
        this.legalOperatingEntity = legalOperatingEntity;
    }
    
    public BigDecimal getConnectCharge() {
		return connectCharge;
	}
	public void setConnectCharge(BigDecimal connectCharge) {
		this.connectCharge = connectCharge;
	}

	public String getConnectChargeType() {
		return connectChargeType;
	}
	public void setConnectChargeType(String connectChargeType) {
		this.connectChargeType = connectChargeType;
	}

	public List<UtilityService> getServices() {
        if (this.services == null)
            services = new ArrayList<UtilityService>();
        return services;
    }
    public void addService(UtilityService service) {
        if (this.services == null)
            services = new ArrayList<UtilityService>();
        services.add(service);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof Utility))
            return false;
        if (((Utility) obj).getId() == this.id)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public void setServices(List<UtilityService> list) {
		this.services = list;
		
	}
}