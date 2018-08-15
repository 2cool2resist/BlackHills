package com.bhc.startstop.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {

    public static final String CIS_DATE_FORMAT = "yyyyMMdd";
    public static final String DISPLAY_DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIEMSTAMP_FORMAT = "MMddyyyyHHmmssn";
    public static final String EMAIL_FORMAT = "EEEE, MMMM dd, yyyy";

    /**
     * Changes a date from the format mm/dd/yyyy to yyyyddmm
     * If no / is present in the string, it will return the unaltered string
     * 
     * @param mmddyyyySeperated
     * @return
     */
    public static String formatDate(String mmddyyyySeperated) {

        if (StringUtils.isNotEmpty(mmddyyyySeperated)) {
            StringBuilder startDateBuilder = new StringBuilder();
            String[] startDateArray = mmddyyyySeperated.split("/");
            if (startDateArray[0].length() == 4) { // yyyy mm dd
                for (String piece : startDateArray) {
                    startDateBuilder.append(StringUtils.leftPad(piece, 2, '0'));
                }
            }
            else if (startDateArray.length == 3) { // mm dd yyyy
                startDateBuilder.append(startDateArray[2])
                        .append(StringUtils.leftPad(startDateArray[0], 2, '0'))
                        .append(StringUtils.leftPad(startDateArray[1], 2, '0'));
            }
            else { // assume yyyymmdd
                for (String piece : startDateArray) {
                    startDateBuilder.append(piece);
                }
            }
            return startDateBuilder.toString();
        }
        else
            return null;
    }

    public static String getCurrentDate(String format) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * Formats the given date to mm/dd/yyyy
     * 
     * @param date
     * @return
     */
    public static String toDisplayFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);
        return date.format(formatter);
    }

    /**
     * Converts a String in DISPLAY_DATE_FORMAT to a LocalDate
     * 
     * @param displayDate
     *        MM/dd/yyyy
     * @return
     */
    public static LocalDate fromDisplayFormat(String displayDate) {
        return LocalDate.parse(displayDate, DateTimeFormatter.ofPattern(DateUtils.DISPLAY_DATE_FORMAT));
    }

    /**
     * Formats the given date to yyyyMMdd
     * 
     * @param date
     * @return
     */
    public static String toCisFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CIS_DATE_FORMAT);
        return date.format(formatter);
    }

    /**
     * Converts a String in CIS_DATE_FORMAT to a LocalDate
     * 
     * @param cisDate
     *        yyyyMMdd
     * @return
     */
    public static LocalDate fromCisFormat(String cisDate) {
    	if(StringUtils.isEmpty(cisDate))
    		return null;
    	else if(cisDate.equals("00000000"))
    		return null;
        return LocalDate.parse(cisDate, DateTimeFormatter.ofPattern(DateUtils.CIS_DATE_FORMAT));
    }
    
    /**
     * Converts a given date to DayOfWeek, Month dd, yyyy
     * ex:  Monday, July 4, 2020
     */
    public static String toEmailDisplay(LocalDate date) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EMAIL_FORMAT);
    	return date.format(formatter);
    }

}