package com.hendall.survey.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ServiceUtils {

	public static String DATE_FORMAT_MM_DD_YYYY_HH_MM_SS = "MM/dd/yyyy HH:mm:ss";
	public static String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
	public static Properties props = new Properties();
	static
	  {
	    
	    try {
	props.load(ServiceUtils.class.getResourceAsStream("/survey.properties"));
} catch (Exception e) {
	
	e.printStackTrace();
}
	  }

	public static String converDateToString(Date date, String format) {
DateFormat df = new SimpleDateFormat(format);
return df.format(date);
	}

	public static Date converStringToDate(String dateString, String format) {
DateFormat df = new SimpleDateFormat(format);
Date date;
try {
	date = df.parse(dateString);
	return date;
} catch (Exception e) {
	e.printStackTrace();
}
return null;
	}
	public static String getPropety(String key){
return props.getProperty(key);
	}
}
