package com.bhc.startstop.util;

public class SecurityUtils {
	
	public static Long encrypt(Long value) {
		// quick and dirty encode to base 8
		if(value != null ) {
			String encrypted = Long.toOctalString(value);
			return Long.valueOf(encrypted);
		}
		else
			return null;
	}
	
	public static Long decrypt(Long encrypted) {
		if(encrypted != null)
			return Long.parseLong(encrypted.toString(), 8);  // convert octal back to decimal
		else
			return null;
	}
}