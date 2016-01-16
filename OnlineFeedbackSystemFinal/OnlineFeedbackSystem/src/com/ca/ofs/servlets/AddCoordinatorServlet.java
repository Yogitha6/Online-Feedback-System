package com.ca.ofs.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ca.ofs.utils.DBHelper;
import com.ca.ofs.utils.OFSCommons;

/**
 * Servlet implementation class AddCordinatorServlet
 */
public class AddCoordinatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCoordinatorServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in Add Coordinatorservlet");

		String pmf = request.getParameter("pmf_key");
		String defaultPwd = OFSCommons.DEFAULT_PSSWD;
		String role = "c";

		DBHelper.addCoordinator(pmf, defaultPwd, role);

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/aLanding.jsp");
		dispatcher.forward(request, response);
	}

}
