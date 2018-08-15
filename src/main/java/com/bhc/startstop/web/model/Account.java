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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "START_STOP_ACCOUNT")
public class Account implements Serializable {

    private static final long serialVersionUID = -8532274476592775684L;

    public static final String ALL_LOE_VALUE = "ALL";

    @Id
    @Column(name = "ACCOUNT_ID", updatable = false, nullable = false)
    @GenericGenerator(name = "account_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "START_STOP_ACCOUNT_ID_SEQ"),
            @Parameter(name = "increment_size", value = "1") })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long id;

    @Column(name = "LEGAL_OPERATING_ENTITY")
    private String legalOperatingEntity;

    @Column(name = "CIS_ACCOUNT_ID")
    private Long accountId;
    
    @Column(name = "IS_NEW")
    @Type(type = "yes_no")
    private Boolean newAccount;

    @Column(name = "CNP")
    @Type(type = "yes_no")
    private Boolean cutNonPay;

    @Column(name = "RESIDENTIAL")
    @Type(type = "yes_no")
    private Boolean residential;
    
    @Column(name="REQUIRED_PAYMENT")
    private BigDecimal requiredPayment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = PrimaryPerson.class, orphanRemoval = true)
    @JoinColumn(name = "PRIMARY_PERSON_ID", referencedColumnName = "PERSON_ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private PrimaryPerson primaryPerson;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = FinanciallyResponsible.class, orphanRemoval = true)
    @JoinColumn(name = "ACCOUNT_ID")
    @Fetch(value = FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<FinanciallyResponsible> authorized;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Contact.class, orphanRemoval = true)
    @JoinColumn(name = "ACCOUNT_ID")
    @Fetch(value = FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Contact> contacts;
    
    @Column(name = "SEASONAL_CHARGE")
    @Type(type = "yes_no")
    private Boolean seasonalCharge; // customer is reconnecting within 12 months, recoup customer charge for the disconnected months
    
    // Deposit columns - we want to retain the new deposit amount and send it on to the turn on
    @Column (name="DEPOSIT_TOTAL")
    private BigDecimal newDeposit;  // maps to AdditionalDepositAmount
    @Column (name="DEPOSIT_REQUIRED")
    private BigDecimal previousDepositDue; // maps to DepositCurrentAmountDue
    @Column (name="DEPOSIT_ARR_MONTHS")
    private Integer newDepositArrangementMonths; // maps to NumberArrangementMonths

    public Account() {
        authorized = new ArrayList<FinanciallyResponsible>();
        contacts = new ArrayList<Contact>();
    }

    public Account(String utilityTypeCd) {
        // this.id
        authorized = new ArrayList<FinanciallyResponsible>();
        contacts = new ArrayList<Contact>();
    }

    /**
     * Copy constructor is used when two accounts are created from a single template with ALL_LOE_VALUE
     * After calling, be sure to set these columns
     * * accountId
     * * legalOperatingEntity
     * * seasonal charge
     * * deposit info
     * 
     * @param copy
     */
    public Account(Account copy) {
        authorized = new ArrayList<FinanciallyResponsible>();
        contacts = new ArrayList<Contact>();
        // this.id = copy.getId(); // don't copy the database id
        // this.accountId = copy.getAccountId(); // don't copy account id
        // this.legalOperatingEntity = copy.getLegalOperatingEntity(); // should be overwritten
        this.newAccount = true;
        // this.cutNonPay = copy.isCutNonPay(); // should be re-queried from CIS
        // this.residential = copy.isResidential(); // should be re-queried from CIS
        this.primaryPerson = copy.getPrimaryPerson();
        this.authorized.addAll(copy.getAuthorized());
        this.contacts.addAll(copy.getContacts());
        //this.seasonalCharge = 
        // this.depositAmountDue = copy.getDepositAmountDue(); // should be re-queried from CIS
        // this.depositAmountAdditional = copy.getDepositAmountAdditional(); // should be re-queried from CIS
        // this.depositArrangementMonths = copy.getDepositArrangementMonths(); // should be re-queried from CIS
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getLegalOperatingEntity() {
        return legalOperatingEntity;
    }
    public void setLegalOperatingEntity(String legalOperatingEntity) {
        this.legalOperatingEntity = legalOperatingEntity;
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
    
    /**
     * Indicates if teh account is a new account or was existing prior to start/stop
     */
    public Boolean isNewAccount() {
    	return this.newAccount;
    }
    public void setNewAccount(Boolean isNew) {
    	this.newAccount = isNew;
    }

    /**
     * Indicates if the account has been flagged as CNP
     * set through accountService.runStartChecks
     * 
     * @return
     */
    public Boolean isCutNonPay() {
        return this.cutNonPay;
    }
    public void setCutNonPay(Boolean cnp) {
        this.cutNonPay = cnp;
    }

    /**
     * Indicates if the account is marked as a residential account
     * set through accountService.runStartChecks
     * 
     * @return
     */
    public Boolean isResidential() {
        return this.residential;
    }
    public void setResidential(Boolean isResidential) {
        this.residential = isResidential;
    }
    
    public BigDecimal getRequiredPayment() {
		return requiredPayment;
	}
	public void setRequiredPayment(BigDecimal requiredPayment) {
		this.requiredPayment = requiredPayment;
	}

	public void setPrimaryPerson(PrimaryPerson person) {
        primaryPerson = person;
        if(primaryPerson != null)
        	primaryPerson.setPersonType(PersonEntity.PRIMARY_PERSON_TYPE);
    }
    public PrimaryPerson getPrimaryPerson() {
        return primaryPerson;
    }
    public void setPrimaryPerson(PersonEntity primary) {
        this.primaryPerson = new PrimaryPerson(primary);
        primaryPerson.setPersonType(PersonEntity.PRIMARY_PERSON_TYPE);
    }

    public List<FinanciallyResponsible> getAuthorized() {
        return this.authorized;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public Boolean isSeasonalCharge() {
    	if(this.seasonalCharge == null)
    		this.seasonalCharge = false;
		return this.seasonalCharge;
	}
	public void setSeasonalCharge(Boolean seasonalCharge) {
		this.seasonalCharge = seasonalCharge;
	}

    public BigDecimal getNewDeposit() {
		return newDeposit;
	}

	public void setNewDeposit(BigDecimal newDeposit) {
		this.newDeposit = newDeposit;
	}

	public BigDecimal getPreviousDepositDue() {
		return previousDepositDue;
	}

	public void setPreviousDepositDue(BigDecimal previousDepositDue) {
		this.previousDepositDue = previousDepositDue;
	}

	public Integer getNewDepositArrangementMonths() {
		return newDepositArrangementMonths;
	}

	public void setNewDepositArrangementMonths(Integer newDepositArrangementMonths) {
		this.newDepositArrangementMonths = newDepositArrangementMonths;
	}
	

	@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof Account))
            return false;
        if (((Account) obj).getId() == this.id)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}