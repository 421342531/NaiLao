package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.util.ShowLogUtli;
import com.util.caltime.CalEatTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class GetEatCrossInfo
 */
@WebServlet("/GetEatCrossInfoServlet")
public class GetEatCrossInfoServlet extends HttpServlet {
	 private static Logger logger = Logger.getLogger(GetEatCrossInfoServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEatCrossInfoServlet() {
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

		try {
		//	String result = CalEatTime.RecordEndEatInfo();
		//	logger.info("result:"+result);
		//	if("0".equals(result)) {
				JSONObject obj = CalEatTime.queryListCross();
				
				 String param="";
						param = JSON.toJSONString(obj);
						 response.getWriter().write(param);
						 logger.info("param:"+param);
		//	}else {
	//			request.setAttribute("data", "-1");
	//		}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//记录信息
	}
}
