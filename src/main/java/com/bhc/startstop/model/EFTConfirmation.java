package com.bhc.startstop.model;

import java.io.Serializable;
import java.time.LocalDate;

public class EFTConfirmation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long confirmationNumber;
    private LocalDate postTransDate;

    public Long getConfirmationNumber() {

        return confirmationNumber;
    }

    public void setConfirmationNumber(Long confirmationNumber) {

        this.confirmationNumber = confirmationNumber;
    }

    public LocalDate getPostTransDate() {

        return postTransDate;
    }

    public void setPostTransDate(LocalDate postTransDate) {

        this.postTransDate = postTransDate;
    }

}
