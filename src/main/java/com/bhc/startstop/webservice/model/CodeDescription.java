package com.bhc.startstop.webservice.model;

import java.io.Serializable;

public class CodeDescription implements Serializable {
    private static final long serialVersionUID = -6703295827712806656L;
    private String code;
    private String description;

    public CodeDescription() {
    }

    public CodeDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}