package com.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class CountBirthday {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(showDate()[0]+showDate()[1]+showDate()[2]);
		
	}
    public static String[] showDate() {
    	String[] showDate = {"","",""};
    	LocalDate today = LocalDate.now();
    	
    	showDate[0] = "Today : " + today;
        //System.out.println("Today : " + today);
        LocalDate birthDate = LocalDate.of(2020, Month.AUGUST, 26);
       // System.out.println("BirthDate : " + birthDate);
        showDate[1] = "BirthDate : " + birthDate;
        Period p = Period.between(birthDate, today);
       // System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        int int3= p.getDays()+1;
        showDate[2] =
        		"年龄 :"+ p.getYears()+"年"+
        					p.getMonths()+"月"+
        					int3+"日";
    	return showDate;
    }

}
