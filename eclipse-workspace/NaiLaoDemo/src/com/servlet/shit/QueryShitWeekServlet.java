package com.servlet.shit;

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
import com.shitUtil.QueryShitWeekDataUtil;
import com.util.HuangDanUtil;

/**
 * Servlet implementation class QueryShitWeekServlet
 */
@WebServlet("/QueryShitWeekServlet")
public class QueryShitWeekServlet extends HttpServlet {
	 private static Logger logger = Logger.getLogger(StopEatServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryShitWeekServlet() {
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
			 str = JSON.toJSONString(QueryShitWeekDataUtil.queryWeekShitData());
			 logger.info(str);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.getWriter().write(str);
		return;
	}

}
