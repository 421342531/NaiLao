package com.shitUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QueryShitWeekDataUtil {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		queryWeekShitData();
		
	}
	
	public static  Map<String ,String > queryWeekShitData() throws ClassNotFoundException, SQLException  {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Map<String ,String >  map =new HashMap<>();
		String sql_queryStartTime =
	 			"select date,count from shit_count t order by date ";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		while(rs.next()) {
			String date = rs.getString("date");
			String info = rs.getString("count");
			date =date.substring(4,date.length());//20200304 => 0304
			
			System.out.println("日期:"+date+"臭臭次数："+info);
			map.put(date, info);
		}
		return map;
	}
}
