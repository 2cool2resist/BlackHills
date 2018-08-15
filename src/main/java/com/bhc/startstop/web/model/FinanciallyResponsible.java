package com.bhc.startstop.web.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "START_STOP_FINANCIAL_PERSON")
// @DiscriminatorValue(PersonEntity.FINANCIAL_PERSON_TYPE)
public class FinanciallyResponsible extends PersonEntity {

    private static final long serialVersionUID = 8960201054915376756L;

    public FinanciallyResponsible() {
        super();
        this.setPersonType(PersonEntity.FINANCIAL_PERSON_TYPE);
    }

    public FinanciallyResponsible(PersonEntity person) {
        super(person);
        this.setPersonType(PersonEntity.FINANCIAL_PERSON_TYPE);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof FinanciallyResponsible))
            return false;
        if (((PersonEntity) obj).getId() == this.getId())
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}