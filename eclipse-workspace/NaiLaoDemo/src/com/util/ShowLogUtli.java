package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class ShowLogUtli {
	 private static Logger logger = Logger.getLogger(ShowLogUtli.class);
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		showLogFunc();
	}
	
	public static JSONObject showLogFunc() throws ClassNotFoundException, SQLException {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		String sql_queryStartTime =
	 			"select time,type from time_control_log t order by time  desc";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		JSONObject jsonObject = new JSONObject(true);
		while(rs.next()) {
			String time = rs.getString("time");
			String type = rs.getString("type");
		//	logger.info(time+"|"+type);
			jsonObject.put(time, type);
			//map.put(time, type);
		}
		return jsonObject;
		
	}

}
