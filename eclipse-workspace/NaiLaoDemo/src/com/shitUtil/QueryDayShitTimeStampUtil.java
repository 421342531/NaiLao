package com.shitUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.servlet.shit.TongJiShit;

public class QueryDayShitTimeStampUtil {
	private static Logger logger = Logger.getLogger(QueryDayShitTimeStampUtil.class);
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		tongjiDayShitTimeStamp();
	}
	
	
	public static Map<String ,String >  tongjiDayShitTimeStamp() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	 	System.out.println("Success loading Mysql Driver!");
		Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
		Map<String ,String >  map =new HashMap<>();
		String sql_queryStartTime =
	 			"select time ,type from time_control_log  t where t.date = '"
	 					+getNowDate()+"' and t.type= '2' order by time";
		PreparedStatement pstmt = null;
		pstmt = connect.prepareStatement(sql_queryStartTime);
		ResultSet rs ;
		rs = pstmt.executeQuery(); 
		while(rs.next()) {
			String time = rs.getString("time");//ex：202009090934
			//	time = time.substring(8, time.length());//ex：0934
			String type = rs.getString("type"); //0: 吃 1:结束吃
			type = "1";// 表示开启
			logger.info("time="+time+"|type="+type);
			map.put(time, type);
		}
		return map;
	}
	
	// 获取当前日期 20201203
		public static String getNowDate() {
			Date nowTime = new Date();
			DateFormat df11= new SimpleDateFormat("yyyyMMdd");
			//System.out.println(df11.format(nowTime));
			return df11.format(nowTime);
		}

}
