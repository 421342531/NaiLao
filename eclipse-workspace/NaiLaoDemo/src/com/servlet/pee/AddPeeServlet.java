package com.servlet.pee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peeUtil.PeeUtil;
import com.shitUtil.RecordShitCounts;

/**
 * Servlet implementation class AddPeeServlet
 */
@WebServlet("/AddPeeServlet")
public class AddPeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPeeServlet() {
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

		String count = "1";
		try {
			//小便次数+1
			
			count=PeeUtil.updatePeeTime();
			//System.out.println("servlet return count = "+count);
			RecordShitCounts.recordShitLog("3");//记录日志-尿尿
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
