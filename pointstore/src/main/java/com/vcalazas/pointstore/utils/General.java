package com.vcalazas.pointstore.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class General {

	public static String getdataIsoString(boolean hasT) {
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
        // Conversion
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(tz);
        if(hasT)
        	return sdf.format((Date) GregorianCalendar.getInstance(tz).getTime());
        else 
        	return sdf.format((Date) GregorianCalendar.getInstance(tz).getTime()).replace("T", " ");
	}
	
}
