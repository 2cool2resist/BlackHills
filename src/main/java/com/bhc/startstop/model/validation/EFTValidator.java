package com.bhc.startstop.model.validation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bhc.startstop.model.EFT;

@Component
@Qualifier("eftValidator")
public class EFTValidator implements Validator {

    public boolean supports(Class<?> clazz) {

        return EFT.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        EFT form = (EFT) obj;

        // Account Number
        if (isStringEmpty(form.getAccountNumber())) {
            e.reject("errors.accountNumber.empty");

        } else if (!form.getAccountNumber().matches("[0-9]*")) {
            e.reject("errors.accountNumber.not.numeric");

        }

        // Account Type
        if (isStringEmpty(form.getAccountType())) {
            e.reject("errors.accountType.empty");

        }

        // Agreement
        if (isStringEmpty(form.getAgreement())) {
            e.reject("errors.agreement.empty");

        }

        // Bank Name
        if (isStringEmpty(form.getBankName())) {
            e.reject("errors.bankName.empty");

        }

        // Payment Amount
        if (isStringEmpty(form.getPaymentAmount())) {
            e.reject("errors.paymentAmount.empty");

        } else if (Double.parseDouble(form.getPaymentAmount()) == 0.0
                || Double.parseDouble(form.getPaymentAmount()) < 0.0) {
            e.reject("errors.paymentAmount.invalid");

            // } else if
            // (!form.getPaymentAmount().matches("^[0-9]{1,2}([,.][0-9]{1,2})?$"))
            // {
        } else if (!form.getPaymentAmount().matches("^[0-9]*([,.][0-9]{1,2})?$")) {
            e.reject("errors.paymentAmount.not.numeric");

        }

        // Routing Number
        if (isStringEmpty(form.getRoutingNumber())) {
            e.reject("errors.routingNumber.empty");
        } else if (form.getRoutingNumber().length() != 9) {
            e.reject("errors.routingNumber.length");
        } else if (form.getRoutingNumber().charAt(0) == '5') {
            e.reject("errors.routingNumber.automatic");
        } else if (!form.getRoutingNumber().matches("[0-9]*")) {
            e.reject("errors.routingNumber.not.numeric");

        } else {
            // check for valid routing number, 9th digit is checksum
            // checksum: (3(d_1 + d_4 + d_7) + 7(d_2 + d_5 + d_8) + (d_3 + d_6 +
            // d_9)) mod 10 = 0

            int[] digits = new int[9];
            for (int i = 0; i < form.getRoutingNumber().length(); i++) {
                digits[i] = Character.getNumericValue(form.getRoutingNumber().charAt(i));
            }
            int sum = 3 * (digits[0] + digits[3] + digits[6]) + 7 * (digits[1] + digits[4] + digits[7])
                    + (digits[2] + digits[5] + digits[8]);

            if ((sum % 10) != 0) {
                e.reject("errors.routingNumber.invaild");
            }
        }

        // Email Address
        if (!isStringEmpty(form.getEmailAddress())) {

            if (!form.getEmailAddress().matches(
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                e.reject("errors.emailAddress.invalid");
            }
        }
    }

    public boolean isStringEmpty(String s) {

        boolean isEmpty = s == null ? true : s.isEmpty();

        return isEmpty;
    }

}
