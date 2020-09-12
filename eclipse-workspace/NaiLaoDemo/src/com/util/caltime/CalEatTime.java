package com.util.caltime;

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

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.po.TimeCross;
import com.util.CountTimeCross;

public class CalEatTime {
	private static Logger logger = Logger.getLogger(CalEatTime.class);
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		logger.info(queryListCross());
	}
	
	public static String   RecordEndEatInfo() throws SQLException, ClassNotFoundException, ParseException {
	
		//取最近一条记录，如果end-time为空 则记录 如果有end——time 不记录 直接return
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String nowTime = getNowTime();
		String nowDate = getNowDate();
		String index_startTime="";
		String index_date="";

		String sql_queryStartTime =
				//按照开始时间降序取第一条
	 	"select date,time_start ,time_end from time_control_cross t "
	 	+ "where t.date = '"+nowDate + "'"
	 	+ "order by time_start desc LIMIT 1 ";
		logger.info("sql_queryStartTime().sql = "+sql_queryStartTime);
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery();
		String startTime ="";
		
		while(rs.next()) {
			String time_start =rs.getString("time_start");
			String date 	  =rs.getString("date");
			String time_end ="";
			 	   time_end = rs.getString("time_end");
			logger.info("cross 表中 time_start= "+time_start);
			logger.info("cross  表中  date="       +date);
			logger.info("cross  表中  time_end="       +time_end);
			index_startTime =time_start;
			index_date = date;
			//插入结束时间
			if(time_end.equals("")) {
				//没有结束时间就update
				Class.forName("com.mysql.cj.jdbc.Driver");
			 	Connection connect2 = DriverManager.getConnection(
						 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
				Statement statement2 =connect.createStatement();
				String nowTime2 = getNowTime();
				//计算间隔时间 并插入
				//CountTimeCross.getTimeCross("202008301030","202008301031");
			    String cross = 	CountTimeCross.getTimeCross(index_startTime,nowTime2);
				String sql2=
						"UPDATE time_control_cross set  "
						+ "time_end =  '"+nowTime2+"' ,timeCross = '"+cross+"' where time_start = '"
								+index_startTime+"' and date ='"+index_date+"'"
										;//"202008290001";
				logger.info("updateStartTime() .sql="+sql2);
				int rr2 = statement.executeUpdate(sql2);// 更新成功条数 1为更新成功，0未更新
				statement.close();
			}else {
			//有结束时间了，不插入 直接return	
				return "-1";
			}
			}
			//再返回今天所有的记录信息
			return "0";//需要再查询
		}
	public static JSONObject queryListCross() throws ClassNotFoundException, SQLException {
		JSONObject jsonObject = new JSONObject(true);
		//再返回今天所有的记录信息
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect3 = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		String sql_queryStartTime3 =
	 			"select * "
	 			+ " from time_control_cross "
	 			+ " where"
	 			+ " date ='" + getNowDate()+"' order by time_start" ;
		PreparedStatement pstmt3 = null;
		logger.info("sql_queryStartTime3:"+sql_queryStartTime3);
		pstmt3 = connect3.prepareStatement(sql_queryStartTime3);
		ResultSet rs3 ;
		rs3 = pstmt3.executeQuery(); 
		int index = 1;
		while(rs3.next()) {
			TimeCross tc =new TimeCross();
			
			String time_start = rs3.getString("time_start");
			String time_end = "";
			String timeCross="0";
			if(null!=rs3.getString("time_end")&&
					""!=rs3.getString("time_end")&&
						0!=rs3.getString("time_end").length())
			{
			  time_end = rs3.getString("time_end");
			}
			if(null!=rs3.getString("timeCross")&&
					""!=rs3.getString("timeCross")&&
						0!=rs3.getString("timeCross").length())
			{
				timeCross = rs3.getString("timeCross");
			}
			time_start =time_start.substring(8, time_start.length());
			
			tc.setTime_start(time_start);
			tc.setTime_end(time_end);
			tc.setTimeCross(timeCross);
			jsonObject.put(""+index++,tc);
			logger.info("jsonObject"+jsonObject);
		}
		return jsonObject;
	}
	
	
	//开始记录开始时间
	public static void RecordStartEatInfoCross() throws SQLException, ClassNotFoundException {
		//20200909 202009101023 202009101134 30分钟
		/*
		//step1 查看是否已经有启动时间 如果有直接return 如果没有insert
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect.createStatement();
		String nowTime = getNowTime();
		String nowDate = getNowDate();

		String sql_queryStartTime =
	 	"select date,time_start from time_control_cross t"
	 	+ " where t.time_start = '"+nowTime+"' and date = '"+nowDate +"'";
		logger.info("sql_queryStartTime().sql = "+sql_queryStartTime);
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery();
		String startTime ="";
		
		while(rs.next()) {
			logger.info("  time_control_cross表已经存在 开始时间为："+nowTime+" 的记录");
			 return;
		}
		*/
		
		//没有开始时间就insert
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	Connection connect1 = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Statement statement =connect1.createStatement();
		String sql=
				"insert into time_control_cross"
				+ "(date,time_start,time_end,timeCross)values"
				+ "("+"'"+getNowDate()+"','"+
				getNowTime()+"','"+"',"+"'"+"')";//主要插入日期+开始时间
		logger.info("insert time_control_cross.sql= "+sql);
		int rr1 = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		statement.close();
	}

	
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
	
}
