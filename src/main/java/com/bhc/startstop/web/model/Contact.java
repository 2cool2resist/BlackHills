package com.bhc.startstop.web.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "START_STOP_CONTACT_PERSON")
// @DiscriminatorValue(PersonEntity.CONTACT_PERSON_TYPE)
public class Contact extends PersonEntity {
    private static final long serialVersionUID = -2762341888999785797L;

    public Contact() {
        super();
        this.setPersonType(PersonEntity.CONTACT_PERSON_TYPE);
    }

    public Contact(PersonEntity person) {
        super(person);
        this.setPersonType(PersonEntity.CONTACT_PERSON_TYPE);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof Contact))
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