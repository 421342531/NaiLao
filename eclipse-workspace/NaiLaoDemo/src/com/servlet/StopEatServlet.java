package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.RecordTime;

/**
 * Servlet implementation class endEatServlet
 */
@WebServlet("/StopEatServlet")
public class StopEatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StopEatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get end eating");
		//response.sendRedirect("/index.jsp");
		 String startTimeQuery = "";
		 try {
			 startTimeQuery = RecordTime.updateEndTime();
				RecordTime.recordLog("1");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 response.getWriter().write(""+
			startTimeQuery.substring(8, 10)+":"+startTimeQuery.substring(10, 12)+"|"+
				startTimeQuery.substring(21,23)+":"+
				startTimeQuery.substring(23,25)+
				startTimeQuery.substring(25,startTimeQuery.length()) );
						//startTimeQuery.length())); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post end eating");
		//response.sendRedirect("/index.jsp");
		 String startTimeQuery = "";
		 try {
			 startTimeQuery = RecordTime.updateEndTime();
				RecordTime.recordLog("1");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 response.getWriter().write(""+
			startTimeQuery.substring(8, 10)+":"+startTimeQuery.substring(10, 12)+"|"+
				startTimeQuery.substring(21,23)+":"+
				startTimeQuery.substring(23,25)+
				startTimeQuery.substring(25,startTimeQuery.length()) );
						//startTimeQuery.length())); 
	}

}
