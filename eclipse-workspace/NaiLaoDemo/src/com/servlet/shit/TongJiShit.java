package com.servlet.shit;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.shitUtil.QueryDayShitTimeStampUtil;
import com.util.RecordTime;

/**
 * Servlet implementation class TongJiEat
 */
@WebServlet("/TongJiShit")
public class TongJiShit extends HttpServlet {
	private static Logger logger = Logger.getLogger(TongJiShit.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TongJiShit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    logger.info("开始处理TongJiShit");
	    String param="";
		try {
			param = JSON.toJSONString(QueryDayShitTimeStampUtil.tongjiDayShitTimeStamp());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("StopEatServlet 返回信息:"+param);
		 response.getWriter().write(param);
		return;
	
	}

}
