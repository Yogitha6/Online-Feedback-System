package com.ca.ofs.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Logout Handler!");
		HttpSession session = req.getSession();
		session.invalidate();
		try {
			resp.sendRedirect("index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
