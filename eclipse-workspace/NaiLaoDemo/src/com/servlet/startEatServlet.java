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

import com.util.RecordTime;
import com.util.caltime.CalEatTime;

/**
 * Servlet implementation class startEatServlet
 */
@WebServlet("/startEatServlet")
public class startEatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(startEatServlet.class); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public startEatServlet() {
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
		// TODO Auto-generated method stub
		 

		 String id = request.getParameter("id");
		 //System.out.println("start to eating..."+id);
		 String startTime = "";
		 try {
		    startTime = RecordTime.updateStartTime();
			RecordTime.recordLog("0");
		
			logger.info("startEatservlet:CalEatTime.RecordStartEatInfoCross();//用于计算吃奶间隔时间");
			CalEatTime.RecordStartEatInfoCross();//用于计算吃奶间隔时间
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// response.sendRedirect("/index.jsp");
	
		 response.getWriter().write(
				 startTime.substring(8, 10)+":"+startTime.substring(10, 12));
		 
	}

}
