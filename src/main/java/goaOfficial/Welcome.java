package goaOfficial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class Welcome
 */
public class Welcome extends HttpServlet {

	private static Connection connection = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Welcome() {
		super();
	}

	public void init() {
		connection = ConnectionManager.getConnection();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("u_connection", connection);
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("AddRecord")){
			RequestDispatcher rd = request.getRequestDispatcher("basic.html"); 
			rd.forward(request,response);
		}
		else if(action.equalsIgnoreCase("FetchRecords")){
			RequestDispatcher rd = request.getRequestDispatcher("/FetchRecords"); 
			rd.forward(request,response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
