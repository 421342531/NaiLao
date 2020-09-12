package com.servlet;

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
import com.util.ShowLogUtli;

/**
 * Servlet implementation class showLogServlet
 */
@WebServlet("/showLogServlet")
public class showLogServlet extends HttpServlet {
	 private static Logger logger = Logger.getLogger(showLogServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showLogServlet() {
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
		try {
			
			logger.info(JSON.toJSONString(ShowLogUtli.showLogFunc()));
			request.setAttribute("data", 
					ShowLogUtli.showLogFunc()); 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		rd = sc.getRequestDispatcher("/showLog.jsp"); //定向的页面   
		rd.forward(request, response);
		
		//Iterator it
		return;
	}
}
