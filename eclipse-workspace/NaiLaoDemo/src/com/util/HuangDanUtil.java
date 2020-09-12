package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;



public class HuangDanUtil {
	
	 private static Logger logger = Logger.getLogger(HuangDanUtil.class);
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//HuangDanUtil.insertHuangDanInfo("15.42");
		 queryWeekHuangDanInfo();
		
	}
	
	public static String insertHuangDanInfo(String info) throws ClassNotFoundException, SQLException {
		if(info.equals("")) {
			return 2+"";
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
	 	String sql_queryStartTime =
	 			"select * from huangdan_data t where t.date = '"+getNowDate()+"'";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery();
		String flag="";
		if(rs.next()) {
			flag = rs.getString(1);
			System.out.println("是否已经存在信息="+flag);
			//进行更新
			String sql_update=
					"UPDATE huangdan_data set info = '"
							+info+"' where date = '"+getNowDate()+"'";
			//System.out.println("end time = "+nowTime);\
			Statement statement =connect.createStatement();
			int rr = statement.executeUpdate(sql_update);// 更新成功条数 1为更新成功，0未更新
			System.out.println("update huangdan_data info result= "+rr);
			statement.close();
			return  1+"";//已经有当日数据,且更新了数据
		}
	 	//如果没有当日数据则进行插入
		Statement statement =connect.createStatement();
		String sql=
				"insert into huangdan_data(date,info,time)values("+"'"+getNowDate()+"','"+info+
				"','"+getNowTime()+"')";
		int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
		System.out.println("insert result= "+rr);
		statement.close();
		return 0+"";//没有当日数据，新增当日数据
	}
	
	
	public static Map<String ,String > queryHuangDanInfo() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Map<String ,String >  map =new HashMap<>();
		String sql_queryStartTime =
	 			"select date,info from huangdan_data t order by date ";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		while(rs.next()) {
			String date = rs.getString("date");
			String info = rs.getString("info");
			System.out.println("日期:"+date+"黄疸值："+info);
			map.put(date, info);
		}
		return map;
	}
	
	
	//查询近一周的黄疸值
	public static Map<String ,String > queryWeekHuangDanInfo() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Map<String ,String >  map =new HashMap<>();
		String sql_queryStartTime =
	 			"select date,info from huangdan_data t order by date ";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		while(rs.next()) {
			String date = rs.getString("date");
			String info = rs.getString("info");
			date =date.substring(4,date.length());//20200304 => 0304
			
			System.out.println("日期:"+date+"黄疸值："+info);
			map.put(date, info);
		}
		return map;
	}
	
	
	
	
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
}
