package com.bhc.startstop.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class reads all environment variables from application.property files.  We
 * are centralizing this so we can track where these variables are used throughout our app.
 *
 */
@Component
@ConfigurationProperties("application")
public class ApplicationEnvironment {

	private String applicationId;
	private Integer coy; 
	
	private String externalFromEmail;
	private String internalFromEmail;
	private String clickEmail;
	private String webFailEmail;
	
	public static final String CHEYENNE_LOE = "CLFP";
	

	public ApplicationEnvironment() {}
	
    public Integer getCompanyId() {
    	if(coy == null)
    		return 1;
    	return coy;
    }
    
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getCoy() {
		return coy;
	}

	public void setCoy(Integer coy) {
		this.coy = coy;
	}
	
	public String getExternalFromEmail() {
		return externalFromEmail;
	}

	public void setExternalFromEmail(String externalFromEmail) {
		this.externalFromEmail = externalFromEmail;
	}

	public String getInternalFromEmail() {
		return internalFromEmail;
	}

	public void setInternalFromEmail(String internalEmail) {
		this.internalFromEmail = internalEmail;
	}

	public String getClickEmail() {
		return clickEmail;
	}

	public void setClickEmail(String clickEmail) {
		this.clickEmail = clickEmail;
	}

	public String getWebFailEmail() {
		return webFailEmail;
	}

	public void setWebFailEmail(String webFailEmail) {
		this.webFailEmail = webFailEmail;
	}
    
    

}
