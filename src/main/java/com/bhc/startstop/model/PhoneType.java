package com.bhc.startstop.model;

/**
 * Defines values for Primary and Secondary Phone Types
 * Mappings to CIS are defined in PersonWebServiceImpl
 * 
 * @author bblom
 *
 */
public enum PhoneType {
    HOME("Home"), CELL("Cell"), WORK("Work");

    private String display;

    private PhoneType(String display) {
        this.display = display;
    }

    /**
     * Returns value to be displayed on the web
     * 
     * @return
     */
    public String getDisplay() {
        return this.display;
    }
}
