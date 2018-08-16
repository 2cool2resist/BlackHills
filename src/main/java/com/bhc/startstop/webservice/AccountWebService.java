package com.bhc.startstop.webservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bhc.customer.entirex.messages.account.AccountAuthenticateResponse.AUTHRESPONSE;
import com.bhc.customer.entirex.messages.account.AccountInfoResponse.ACCIRESPONSE;
import com.bhc.customer.entirex.messages.account.AccountSearchResponse.ACTSRESPONSE;
import com.bhc.customer.entirex.messages.account.AccountServiceAgreementsResponse.ASARESPONSE.SAINFOs.SAINFO;
import com.bhc.startstop.exceptions.RestrictedAccountException;
import com.bhc.startstop.exceptions.WebServiceException;

@Service
public interface AccountWebService {
    public AUTHRESPONSE accountAuthenticate(Integer coy, Long accountId) throws RestrictedAccountException, WebServiceException;
    public ACCIRESPONSE accountInfo(Integer coy, Long accountId) throws WebServiceException;
    public List<SAINFO> accountServiceAgreements(Integer coy, Long accountId) throws WebServiceException;
    public ACTSRESPONSE accountSearch(Integer coy, Long accountId, Long personId, String firstName, String lastName,
            String businessName, String phoneNumber) throws WebServiceException;
}