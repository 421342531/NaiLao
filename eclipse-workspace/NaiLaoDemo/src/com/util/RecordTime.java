package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordTime {

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		// TODO Auto-generated method stub
//		//updateStartTime();
//		//updateEndTime();
//		recordLog("1");
//		//getNowDate();
//		 //getNowTime();
//		
//	}
	// 获取当前日期 20201203
	public static String getNowDate() {
		Date nowTime = new Date();
		DateFormat df11= new SimpleDateFormat("yyyyMMdd");
		System.out.println(df11.format(nowTime));
		return df11.format(nowTime);
	}
	//获取当前时间 202010301600
	public static String getNowTime() {
		Date nowTime = new Date();
        DateFormat df11= new SimpleDateFormat("yyyyMMddHHmm");
        System.out.println(df11.format(nowTime));
        return df11.format(nowTime);
	}
	//操作更新启动时间 starttime
	public static String updateStartTime() throws ClassNotFoundException, SQLException {

		
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String nowTime = getNowTime();
		String sql=
				"UPDATE time_control set time_start = "+nowTime;//"202008290001";
		int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		System.out.println("update starttime result= "+rr);
		statement.close();
		return nowTime;
	}

	//操作更新结束时间 endtime
	public static String updateEndTime() throws ClassNotFoundException, SQLException, ParseException {

		
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String nowTime = getNowTime();
		String sql=
				"UPDATE time_control set time_end = "+nowTime;//"202008290002";
		System.out.println("end time = "+nowTime);
		int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		System.out.println("update date endtime result= "+rr);
		statement.close();
		
		
		
		//
		String sql_queryStartTime =
	 			"select time_start from time_control t where t.time_end = '"+nowTime+"'";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
	//	pstmt.setString(1, queryId);
		ResultSet rs ;
		rs = pstmt.executeQuery();
		String startTime ="";
		while(rs.next()) {
		
			 startTime = rs.getString(1);
			System.out.println("startTime="+startTime);
		}
		
		//开始时间：startTime
		//结束时间：nowTime
		//时间差：timeCount
		String timeCount = CountTimeCross.getTimeCross(startTime, nowTime);
	//	double timeCount = Double.valueOf(nowTime)-Double.valueOf(startTime);
		System.out.println("时间差是："+timeCount+"分");
		
		//
		return startTime+"|"+nowTime+"|"+timeCount+"";
	}
	//记录操作日志 type  0:开始吃奶 1:结束吃奶
	public static void recordLog(String type) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String sql=
				"insert into time_control_log(date,time,type)values("+"'"+getNowDate()+"','"+getNowTime()+
				"','"+type+"')";
		int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		System.out.println("insert result= "+rr);
		statement.close();
		//getNowDate();
		 //getNowTime();
	}
	
}
