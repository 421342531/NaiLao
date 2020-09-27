package com.peeUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PeeUtil {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//记录小便次数 peetimes
		public static String updatePeeTime() throws ClassNotFoundException, SQLException {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		 	System.out.println("Success loading Mysql Driver!");
		 	Connection connect = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
			Statement statement =connect.createStatement();
			String nowDate= getNowDate();
			System.out.println(" 当前日期是:"+nowDate);
			//取目前次数
			String sql_queryStartTime =
		 			"select count from pee_count t where t.date = '"+nowDate+"'";
			PreparedStatement pstmt = null;
			pstmt = connect.prepareStatement(sql_queryStartTime);
			ResultSet rs ;
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				//insert
				String sql_insert=
						"insert into pee_count(date,count)values("+"'"+nowDate+"','"+"1')";
				int rr = statement.executeUpdate(sql_insert);// 更新成功条数 1为更新成功，0未更新
				statement.close();
			    System.out.println("现在pee次数="+"1");
				return "1";
			}else {
				//update
				String oldCount  = rs.getString("count");
				int oldTime = Integer.valueOf(oldCount);
				//次数+1
				
					String nowTime = getNowTime();
					String sql=
							"UPDATE pee_count t  set count = "+"'"+(++oldTime)+""+"' "
									+"where t.date ='"+nowDate+"' ";//"202008290001";
					int rr = statement.executeUpdate(sql);// 更新成功条数 1为更新成功，0未更新
					System.out.println("最新的pee次数是:"+oldTime);
					statement.close();
					return oldTime+"";
			}
		}
		public static String queryShitTimes() throws ClassNotFoundException, SQLException {
			String returnString ="0";
			Class.forName("com.mysql.cj.jdbc.Driver");
		 	System.out.println("Success loading Mysql Driver!");
		 	Connection connect = DriverManager.getConnection(
					 "jdbc:mysql://localhost:3306/mysql","root","nbcb,111");
			Statement statement =connect.createStatement();
			String nowDate= getNowDate();
			System.out.println(" 当前日期是:"+nowDate);
			//取目前次数
			String sql_queryStartTime =
		 			"select count from pee_count t where t.date = '"+nowDate+"'";
			PreparedStatement pstmt = null;
			pstmt = connect.prepareStatement(sql_queryStartTime);
			ResultSet rs ;
			rs = pstmt.executeQuery();
			if(rs.next()) {
				returnString = rs.getString("count");
			}
			return returnString;
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
