package com.servlet.shit;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.shitUtil.RecordShitCounts;
import com.util.RecordTime;

/**
 * Servlet implementation class addShitServlet
 */
@WebServlet("/QueryShitServlet")
public class QueryShitServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(RecordTime.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryShitServlet() {
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
		
		String count = "0";
		try {
			//拉屎次数+1
			
			count=RecordShitCounts.queryShitTimes();
			logger.info("query count = "+count);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//updateShitTime();
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 response.getWriter().write(count);
	}

}
