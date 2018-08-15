package com.bhc.startstop.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.bhc.soa.web.types.common.v1.RequestContextType;
import com.bhc.soa.web.types.common.v1.RequestorInfoType;

public class SoapHeaderInfo {

	private static final String host = "TBD";
	private static final String appName="start-stop";
	
    /**
     * Creates the XMLGregorianCalendar for the current date.
     * 
     * @return XMLGregorianCalendar
     */
    private static XMLGregorianCalendar createRequestDate() {

        try {
            Date date = new Date(System.currentTimeMillis());
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());

            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Creates & populates the request context used in the requests
     * 
     * @return RequestContextType
     */
    public static RequestContextType createRequestContext() {

        RequestContextType out = new RequestContextType();
        String mdcUuid = MDC.get("uuid");
        if (StringUtils.isNotBlank(mdcUuid)) {
            out.setRequestUuid(mdcUuid);
        } else {
            out.setRequestUuid(UUID.randomUUID().toString());
        }
        out.setRequestDt(createRequestDate());

        RequestorInfoType requestor = new RequestorInfoType();
        requestor.setRequestorAppHost(host);
        requestor.setRequestorAppName(appName);
        out.setRequestor(requestor);

        return out;
    }


}
