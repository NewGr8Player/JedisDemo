package com.xavier;

public class StringUtil {

	public static boolean isBlank(String value) {
		if (value == null) {
			return true;
		}
		int len = value.length();
		if (len == 0) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			switch (value.charAt(i)) {
				case ' ':
				case '\t':
				case '\n':
				case '\r':
				case '\b':
				case '\f':
					break;
				default:
					return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String value){
		return !isBlank(value);
	}

	public static String toString(Object value){
		return value.toString();
	}
}
