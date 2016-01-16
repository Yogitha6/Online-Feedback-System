package com.ca.ofs.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.ofs.beans.Training;
import com.ca.ofs.utils.DBHelper;

/**
 * Servlet implementation class participantformServlet
 */
public class GetFeedbackHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFeedbackHandler() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("In GetFeedbackHandler");
		HttpSession session = request.getSession();
		ArrayList<Training> trainings = (ArrayList<Training>) session.getAttribute("trainingsObj");
		String index = request.getParameter("trainingname");
		System.out.println("Index: " + index);
		
		Training selectedTraining = trainings.get(Integer.parseInt(index));

		request.setAttribute("selectedTraining", selectedTraining);
		
		int tId = DBHelper.getTrainingID(selectedTraining);
		session.setAttribute("selectedTid", tId);
		
		RequestDispatcher rd = request.getRequestDispatcher("feedback_form.jsp");
		rd.forward(request, response);

	}
}