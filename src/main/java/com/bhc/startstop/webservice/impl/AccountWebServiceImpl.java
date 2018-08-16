package com.bhc.startstop.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bhc.customer.entirex.contract.CISUNIXPort;
import com.bhc.customer.entirex.messages.account.AccountAuthenticate.AUTHREQUEST;
import com.bhc.customer.entirex.messages.account.AccountAuthenticateResponse.AUTHRESPONSE;
import com.bhc.customer.entirex.messages.account.AccountInfo.ACCIREQUEST;
import com.bhc.customer.entirex.messages.account.AccountInfoResponse.ACCIRESPONSE;
import com.bhc.customer.entirex.messages.account.AccountSearch.ACTSREQUEST;
import com.bhc.customer.entirex.messages.account.AccountSearchResponse.ACTSRESPONSE;
import com.bhc.customer.entirex.messages.account.AccountServiceAgreements.ASAREQUEST;
import com.bhc.customer.entirex.messages.account.AccountServiceAgreementsResponse.ASARESPONSE;
import com.bhc.customer.entirex.messages.account.AccountServiceAgreementsResponse.ASARESPONSE.SAINFOs.SAINFO;
import com.bhc.startstop.exceptions.RestrictedAccountException;
import com.bhc.startstop.exceptions.WebServiceException;
import com.bhc.startstop.webservice.AccountWebService;

@Service
public class AccountWebServiceImpl implements AccountWebService {
    private static final Logger log = LoggerFactory.getLogger(AccountWebServiceImpl.class);

    private CISUNIXPort entirexService;

    public AccountWebServiceImpl(CISUNIXPort entirexService) {

        this.entirexService = entirexService;
    }

    @Override
    public AUTHRESPONSE accountAuthenticate(Integer coy, Long accountId) throws RestrictedAccountException, WebServiceException {

        AUTHREQUEST request = new AUTHREQUEST();
        request.setACCOUNTID(accountId.toString());
        request.setCOY(coy.toString());
        AUTHRESPONSE response = null;

        try {
            response = entirexService.accountAuthenticate(request);
        } catch (Exception e) {
            log.error("AccountWebServiceImpl: Exception thrown from accountInfo=" + accountId + ": " + e.getMessage());
            throw new WebServiceException(e);
        }

        if (response == null) {
            log.error("AccountWebServiceImpl:  No account authenticate response returned for account=" + accountId);
            throw new WebServiceException("Account Authenticate for account " + accountId + " returns a null object");
        }
        else if(response.getRETURNCODE().intValueExact() != 0 && response.getMESSAGECODE().intValueExact() == 25)
        	throw new RestrictedAccountException();
        // ignore general errors - allow the response to be blank
        /*else if(response.getRETURNCODE().intValueExact() != 0) { // error
        	log.error("AccountWebServiceImpl:  error {} returned from accountAuthenticate for account {}: {}", 
        			response.getMESSAGECODE(), accountId, response.getMESSAGE());
        	throw new WebServiceException("Error " + response.getMESSAGECODE() + " " + response.getMESSAGE());
        }*/
        else
            return response;
    }

    @Override
    public ACCIRESPONSE accountInfo(Integer coy, Long accountId) throws WebServiceException {

        ACCIREQUEST request = new ACCIREQUEST();
        request.setACCOUNTID(accountId.toString());
        request.setCOY(coy.toString());
        ACCIRESPONSE response = null;

        try {
            response = entirexService.accountInfo(request);
        } catch (Exception e) {
            log.error("AccountWebServiceImpl: Exception thrown from accountInfo=" + accountId + ": " + e.getMessage());
            throw new WebServiceException(e);
        }

        if (response == null) {
            log.error("AccountWebServiceImpl:  No account returned for accountInfo=" + accountId);
            throw new WebServiceException("Account Lookup for account " + accountId + " returns a null object");
        }
        else if(response.getRETURNCODE().intValueExact() == 1 && response.getMESSAGECODE().intValueExact() == 10) {
        	log.debug("AccountWebServiceImpl:  accountInfo unable to retrieve account {}, coy {}", accountId, coy);
        	return null;
        }
        else if(response.getRETURNCODE().intValueExact() != 0) {
            log.error("AccountWebServiceImpl: Error {} retrieving accountInfo for account {}: ", 
            		response.getMESSAGECODE(), accountId, response.getMESSAGE());
            throw new WebServiceException(response.getMESSAGE());
        }
        else
            return response;
    }

    @Override
    public List<SAINFO> accountServiceAgreements(Integer coy, Long accountId) throws WebServiceException {

        ASAREQUEST request = new ASAREQUEST();
        request.setACCOUNTID(accountId.toString());
        request.setCOY(coy.toString());
        ASARESPONSE agreements = null;
        
        log.info("searching for service agreements with account id " + accountId.toString() + "and coy " + coy.toString());

        try {
            agreements = entirexService.accountServiceAgreements(request);
        } catch (Exception e) {
            log.error("AccountWebServiceImpl: Exception thrown from accountInfo=" + accountId + ": " + e.getMessage());
            throw new WebServiceException(e);
        }

        if (agreements == null) {
            log.error("AccountWebServiceImpl:  No account returned for accountServiceAgreements=" + accountId);
            throw new WebServiceException(
                    "Account Service Agreements for account " + accountId + " returns a null object");
        }
        else if (agreements.getSAINFOs() == null || agreements.getSAINFOs().getSAINFO() == null
                || agreements.getSAINFOs().getSAINFO().size() == 0) {
            log.error("AccountWebServiceImpl:  No agreements returned for accountServiceAgreements=" + accountId);
            return new ArrayList<SAINFO>();
        }
        // not checking for return codes - if fields are empty, we are moving on - looking for presence of data
        else
            return agreements.getSAINFOs().getSAINFO();
        
    }

    @Override
    /**
     * Note:  accountSearch runs off a materialized view, so data gets refreshed when view is rebuilt
     * Don't use this for any real-time queries
     */
    public ACTSRESPONSE accountSearch(Integer coy, Long accountId, Long personId, String firstName, String lastName,
            String businessName, String phoneNumber) throws WebServiceException {
        ACTSRESPONSE accounts = null;
        ACTSREQUEST request = new ACTSREQUEST();
        request.setCOY(coy.toString());
        if (accountId != null)
            request.setACCOUNTID(accountId.toString());
        if (personId != null)
            request.setPERSONID(personId.toString());
        request.setFIRSTNAME(firstName);
        request.setLASTNAME(lastName);
        request.setBUSINESSNAME(businessName);
        request.setPHONENUMBER(phoneNumber);

        try {
            accounts = entirexService.accountSearch(request);
        } catch (Exception e) {
            log.error("AccountWebServiceImpl: Exception thrown from accountSearch for person {}: {}",
                    personId, e.getMessage());
            throw new WebServiceException(e);
        }

        if (accounts == null) {
            log.error("AccountWebServiceImpl:  No account returned in accountSearch for person {}", personId);
            throw new WebServiceException("Account Search for person " + personId + " returns a null object");
        }
        else if (accounts.getRETURNCODE() != null && accounts.getRETURNCODE().intValueExact() == 1 && 
        		accounts.getMESSAGECODE() != null && accounts.getMESSAGECODE().intValueExact() == 101) {
        	// handle Person ID not found to be the same as no accounts found
            log.trace("AccountWebServiceImpl.accountSearch: Error {} getting information for person {}: {} ",
                    accounts.getMESSAGECODE(), personId, accounts.getMESSAGE());
            return accounts;
        }
        else if (accounts.getRETURNCODE() != null && accounts.getRETURNCODE().intValueExact() != 0) {
            log.error("AccountWebServiceImpl.accountSearch: Error {} getting information for person {}: {} ",
                    accounts.getMESSAGECODE(), personId, accounts.getMESSAGE());
            throw new WebServiceException(
                    "AccountWebServiceImpl.accountSearch: Error " + accounts.getMESSAGECODE().toString() +
                            " getting information for person " + personId + ": " + accounts.getMESSAGE());
        }
        else {
            if (accounts.getACCOUNTINFOs() == null || accounts.getACCOUNTINFOs().getACCOUNTINFO() == null
                    || accounts.getACCOUNTINFOs().getACCOUNTINFO().size() == 0)
                log.debug("AccountWebServiceImpl:  No accounts found for person {}" + personId);
            return accounts;

        }
    }
}