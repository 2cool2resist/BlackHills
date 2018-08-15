package com.bhc.startstop.web.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "START_STOP_PRIMARY_PERSON")
// @DiscriminatorValue(PersonEntity.PRIMARY_PERSON_TYPE)
public class PrimaryPerson extends PersonEntity {

    private static final long serialVersionUID = -8183340357658842362L;

    public PrimaryPerson() {
        super();
        this.setPersonType(PersonEntity.PRIMARY_PERSON_TYPE);
    }

    public PrimaryPerson(PersonEntity person) {
        super(person);
        this.setPersonType(PersonEntity.PRIMARY_PERSON_TYPE);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof PrimaryPerson))
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