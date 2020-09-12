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
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
public class RecordTime {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//HuangDanUtil.insertHuangDanInfo("15.42");
		tongjiEat();
		//	queryStartEndTime();
	}
	
	private static Logger logger = Logger.getLogger(RecordTime.class);
	// 获取当前日期 20201203
	public static String getNowDate() {
		Date nowTime = new Date();
		DateFormat df11= new SimpleDateFormat("yyyyMMdd");
		//System.out.println(df11.format(nowTime));
		return df11.format(nowTime);
	}
	//获取当前时间 202010301600
	public static String getNowTime() {
		Date nowTime = new Date();
        DateFormat df11= new SimpleDateFormat("yyyyMMddHHmm");
        //System.out.println(df11.format(nowTime));
        return df11.format(nowTime);
	}
	//操作更新启动时间 starttime
	public static String updateStartTime() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	//System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String nowTime = getNowTime();
		String sql=
				"UPDATE time_control set time_start = "+nowTime;//"202008290001";
		logger.info("updateStartTime() .sql="+sql);
		int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		//System.out.println("update starttime result= "+rr);
		statement.close();
		return nowTime;
	}

	//操作更新结束时间 endtime
	public static  Map<String,String> updateEndTime() throws ClassNotFoundException, SQLException, ParseException {
		logger.info("start to updateEndTime()");
		 Map<String,String> map = new HashMap<String,String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	//System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String nowTime = getNowTime();
		String sql=
				"UPDATE time_control set time_end = "+nowTime;//"202008290002";
		//System.out.println("end time = "+nowTime);
		logger.info("updateEndTime().sql = "+sql);
		int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		//System.out.println("update date endtime result= "+rr);
		logger.info("updateEndTime().sql = result="+rr);
		statement.close();
		
		String sql_queryStartTime =
	 			"select time_start from time_control t where t.time_end = '"+nowTime+"'";
		logger.info("sql_queryStartTime().sql = "+sql_queryStartTime);
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery();
		String startTime ="";
		while(rs.next()) {
		logger.info("rs set's startTime="+startTime);
			 startTime = rs.getString("time_start");
			//System.out.println("startTime="+startTime);
		}
		//开始时间：startTime
		//结束时间：nowTime
		//时间差：timeCount
		String timeCount ="0";
		timeCount = CountTimeCross.getTimeCross(startTime, nowTime);
		//System.out.println("时间差是："+timeCount+"分");
		logger.info("startTime="+startTime);
		logger.info("nowTime="+nowTime);
		logger.info("timeCount="+timeCount);
		map.put("startTime", 
				startTime.substring(8, 10)+":"+startTime.substring(10,startTime.length()));
		map.put("endTime", 
				nowTime.substring(8,10)+":"+nowTime.substring(10,nowTime.length()));
		map.put("timeCount", timeCount);
		logger.info("return map="+map);
		return map;
	}
	//记录操作日志 type  0:开始吃奶 1:结束吃奶
	public static void recordLog(String type) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	//System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String sql=
				"insert into time_control_log(date,time,type)values("+"'"+getNowDate()+"','"+getNowTime()+
				"','"+type+"')";
		logger.info("recordLog().sql= "+sql);
		int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		//System.out.println("insert result= "+rr);
		statement.close();
	}
	
	public static Map<String ,String > queryStartEndTime() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Map<String ,String >  map =new HashMap<>();
		String sql_queryStartTime =
	 			"select time_start,time_end from time_control";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		while(rs.next()) {
			String time_start = rs.getString("time_start");
			 time_start = time_start.substring(8,time_start.length());
			 String start_fen= time_start.substring(0,2);
			 String start_miao =time_start.substring(2,time_start.length());
			String time_end = rs.getString("time_end");
			logger.info("time="+start_fen+":"+start_miao+"|time_start="+time_start+"｜time_end="+time_end);
			map.put("time_start", start_fen+":"+start_miao);
			map.put("time_end", time_end);
		}
		return map;
	}
	
	
	
	public static Map<String ,String >  tongjiEat() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Map<String ,String >  map =new HashMap<>();
		String sql_queryStartTime =
	 			"select time ,type from time_control_log  t where t.date = '"
	 					+getNowDate()+"' and (t.type= '0' or t.type = '1') order by time";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		while(rs.next()) {
			String time = rs.getString("time");//ex：202009090934
			//	time = time.substring(8, time.length());//ex：0934
			String type = rs.getString("type"); //0: 吃 1:结束吃
			if(type.equals("0")) {
				logger.info("time="+time+"|type="+type);
				type = "1";// 表示开启
				map.put(time, type);
			}
			
		}
		return map;
	}
}
