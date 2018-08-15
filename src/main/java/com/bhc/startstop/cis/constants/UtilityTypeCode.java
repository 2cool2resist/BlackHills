package com.bhc.startstop.cis.constants;

public enum UtilityTypeCode {
    E("Electric"), G("Natural Gas"), A("Appliance Repair Plan"), N("Non-Utility Service Plan"), W("Water");

    private String description;

    /**
     * Value displayed on the web
     * 
     * @param description
     */
    private UtilityTypeCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns true if this UtiltyTypeCode is a utility service
     * Codes E and G are utility codes
     * Codes A N and W are non-utility codes
     * 
     * @return
     */
    public Boolean isUtility() {
        if (this == E || this == G)
            return true;
        else
            return false;
    }

    public static UtilityTypeCode convertCode(String utilityCode) {
        if (utilityCode != null) {
            for (UtilityTypeCode type : UtilityTypeCode.values())
                if (utilityCode.trim().equals(type.name()))
                    return type;
        }
        return null;
    }

}
