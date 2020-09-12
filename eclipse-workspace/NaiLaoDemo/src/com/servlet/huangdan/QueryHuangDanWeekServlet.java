package com.servlet.huangdan;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.servlet.StopEatServlet;
import com.util.HuangDanUtil;

/**
 * Servlet implementation class QueryHuangDanWeekServlet
 */
@WebServlet("/QueryHuangDanWeekServlet")
public class QueryHuangDanWeekServlet extends HttpServlet {
	 private static Logger logger = Logger.getLogger(StopEatServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryHuangDanWeekServlet() {
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

		ServletContext sc = getServletContext();  
		RequestDispatcher rd = null;
		String str="";
		try {
			 str = JSON.toJSONString(HuangDanUtil.queryWeekHuangDanInfo());
			 logger.info(str);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	//	request.setAttribute("data", str);
		response.getWriter().write(str);
		return;
	}

}
