package com.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Test {
	 private static Logger logger = Logger.getLogger(HuangDanUtil.class);
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
			JSONObject jsonObject =ShowLogUtli.showLogFunc();
			System.out.println("jsonObject="+jsonObject);
		Iterator iter = jsonObject.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey().toString());
            System.out.println(entry.getValue().toString());
        }
	}
}
