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

public class WeightUtil {

	private static Logger logger = Logger.getLogger(WeightUtil.class);
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		recordWeightInfo("10");
		
		
	}
	
	//查询体重map
	public static Map<String ,String > queryWeightInfo() 
			throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Map<String ,String >  map =new HashMap<>();
		String sql_queryStartTime =
	 			"select date,count from weight_data t order by date ";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		while(rs.next()) {
			String date = rs.getString("date");
			String count = rs.getString("count");
			date =date.substring(4,date.length());//20200304 => 0304
			logger.info(date+"|"+count);

			map.put(date, count);
		//	map.put("count", count);
		}
		return map;
	}
	
	
	
	public static void recordWeightInfo(String data) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	//System.out.println("Success loading Mysql Driver!");
	 	Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");

	 	
		String sql_query =
	 			"select count from weight_data t where t.date = '"+getNowDate()+"'";
		logger.info("sql.sql_query = "+sql_query);
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_query);
		ResultSet rs ;
		rs = pstmt.executeQuery();
		String count ="";
		while(rs.next()) {
			logger.info("rs set's count="+count);
			count = rs.getString("count");
			//System.out.println("startTime="+startTime);
		}
		if(count.equals("")) {
			logger.info("无数据，需要插入数据");
			//需要插入
			Class.forName("com.mysql.cj.jdbc.Driver");
		 	//System.out.println("Success loading Mysql Driver!");
		 	Connection connect2 = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
			Statement statement =connect2.createStatement();
			String sql=
					"insert into weight_data(date,count)values"
					+ "("+"'"+getNowDate()+"','"+data+
					"')";
			logger.info("insert.sql= "+sql);
			int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
			//System.out.println("insert result= "+rr);
			statement.close();
			
			
			
		}else {
			// 更新
			Class.forName("com.mysql.cj.jdbc.Driver");
		 	//System.out.println("Success loading Mysql Driver!");
		 	Connection connect1 = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
			Statement statement =connect1.createStatement();
			String sql=
					"UPDATE weight_data set count = "+"'"+data+
					"' where date = '"+getNowDate()+"'";//"202008290001";
			
			logger.info("update sql = "+sql);
			int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
			statement.close();
			
		}
		
		
		
	}
	
		// 获取当前日期 20201203
		public static String getNowDate() {
			Date nowTime = new Date();
			DateFormat df11= new SimpleDateFormat("yyyyMMdd");
			//System.out.println(df11.format(nowTime));
			return df11.format(nowTime);
		}

}
