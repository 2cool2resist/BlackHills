package com.bhc.startstop.util;

public class EmailUtils {
	
	public static String getFirstEmail(String emailList) {
		if(emailList != null && emailList.contains(";")) {
			String[] emails = emailList.split(";");
			return emails[0];
		}
		else if(emailList != null && emailList.contains(",")) {
			String[] emails = emailList.split(",");
			return emails[0];
		}
		// TODO:  check if we have only one @ sign left
		return emailList;
	}
	
	public static String filterInternalEmail(String email) {
		String single = getFirstEmail(email);
		if(single != null) {
			if(single.endsWith("@blackhillscorp.com") || single.endsWith("@perficient.com"))
				return single;
			else
				return null;
		}
		else 
			return null;
	}
}