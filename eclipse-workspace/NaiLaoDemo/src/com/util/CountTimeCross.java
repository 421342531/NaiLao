package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountTimeCross {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		
		getTimeCross("202008301030","202008301140");
	}
	
	
	public static String getTimeCross(String startTime,String endTime) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");

		Date start = simpleDateFormat .parse(startTime);

		Date end = simpleDateFormat .parse(endTime);

		long between = (end.getTime() - start .getTime())/1000;

		long min = between/60;
		System.out.println("时间差是:"+min);
		return min+"";
	}

}
