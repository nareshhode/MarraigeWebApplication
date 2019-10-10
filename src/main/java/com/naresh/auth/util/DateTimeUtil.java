package com.naresh.auth.util;

import java.sql.Timestamp;

public class DateTimeUtil {

	public DateTimeUtil() {
		// TODO Auto-generated constructor stub
	}

	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	
}
