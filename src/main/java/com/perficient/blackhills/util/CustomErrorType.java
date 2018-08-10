package com.perficient.blackhills.util;

/**
 * Custom error type for passing back errors to client 
 * @author ltburch
 *
 */
public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
