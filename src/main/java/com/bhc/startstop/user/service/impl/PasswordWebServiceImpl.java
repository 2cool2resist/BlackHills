package com.bhc.startstop.user.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bhc.startstop.user.exceptions.InvalidPasswordException;
import com.bhc.startstop.user.exceptions.InvalidTokenException;
import com.bhc.startstop.user.exceptions.UsernameNotFoundException;
import com.bhc.startstop.user.service.PasswordWebService;
import com.bhc.startstop.user.service.WebServiceHelper;
import com.bhc.soa.web.contract.entity.password.v1.InvalidPassword;
import com.bhc.soa.web.contract.entity.password.v1.InvalidToken;
import com.bhc.soa.web.contract.entity.password.v1.PasswordServicesV1;
import com.bhc.soa.web.contract.entity.password.v1.ProcessFailed;
import com.bhc.soa.web.contract.entity.password.v1.UsernameNotFound;
import com.bhc.soa.web.types.messages.password.v1.ChangePasswordRequestType;
import com.bhc.soa.web.types.messages.password.v1.GenerateTokenRequestType;
import com.bhc.soa.web.types.messages.password.v1.GenerateTokenResponseType;
import com.bhc.soa.web.types.messages.password.v1.PasswordResetRequestType;

@Service
public class PasswordWebServiceImpl implements PasswordWebService {

    @Value("${user.service.dbad.data.type}")
    private String dbadDataType;

    @Value("${user.security.migration.status}")
    private String migrationStatus;

    @Value("${auth.security.user.type}")
    private String userType;
    
    private PasswordServicesV1 passwordServicesV1;

    public PasswordWebServiceImpl(PasswordServicesV1 passwordServicesV1) {
    	this.passwordServicesV1 = passwordServicesV1;
    }

    @Override
    public void resetPassword(String userName, String newPassword, String token) {

        PasswordResetRequestType request = new PasswordResetRequestType();
        request.setUsername(userName);
        request.setPassword(newPassword);
        request.setToken(token);
        request.setUserType(userType);
        request.setDataType(dbadDataType);
        request.setRequestContext(WebServiceHelper.createRequestContext());
        try {
            passwordServicesV1.resetPassword(request);
        } catch (UsernameNotFound e) {
            throw new UsernameNotFoundException();
        } catch (ProcessFailed e) {
            throw new RuntimeException();
        } catch (InvalidToken e) {
            throw new InvalidTokenException();
        }
    }

    @Override
    public String generateResetToken(String username) {

        GenerateTokenRequestType request = new GenerateTokenRequestType();
        request.setUsername(username);
        request.setDataType(dbadDataType);
        request.setUserType(userType);
        request.setRequestContext(WebServiceHelper.createRequestContext());
        GenerateTokenResponseType response;
        try {
            response = passwordServicesV1.generateToken(request);
            return response.getToken();
            // } catch (UsernameNotFound e) {
            // throw new UsernameNotFoundException();
        } catch (ProcessFailed e) {
            throw new RuntimeException();
        }

    }

    @Override
    public boolean changePassword(String userName, String oldPassword, String newPassword, String ipAddress,
            String userAgent) {

        ChangePasswordRequestType request = new ChangePasswordRequestType();
        request.setUserName(userName);
        request.setOldPassword(oldPassword);
        request.setNewPassword(newPassword);
        request.setUserType(userType);
        request.setDataType(dbadDataType);
        request.setIpAddress(ipAddress);
        request.setMigrationStatus(migrationStatus);
        request.setUserAgent(userAgent);
        request.setRequestContext(WebServiceHelper.createRequestContext());

        try {
            passwordServicesV1.changePassword(request);
        } catch (UsernameNotFound e) {
            throw new UsernameNotFoundException();
        } catch (InvalidPassword e) {
            throw new InvalidPasswordException();
        } catch (ProcessFailed e) {
            throw new RuntimeException();
        }

        return true;
    }

}
