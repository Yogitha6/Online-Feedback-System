package com.ca.ofs.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.ofs.beans.Feedback;
import com.ca.ofs.utils.DBHelper;

/**
 * Servlet implementation class submitfeedbackServlet
 */
@WebServlet("/submitfeedbackServlet")
public class SubmitFeedbackHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitFeedbackHandler() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		Feedback feedback = new Feedback();
		// get all the parameters from the ui and updating in the db using the
		// dbhelper function

		feedback.setPmfKey(session.getAttribute("username").toString());
		feedback.setTID(Integer.parseInt(session.getAttribute("selectedTid").toString()));
		feedback.setAnsFacilities(Integer.parseInt(request.getParameter("facilityrating")));
		feedback.setAnsGeneral(request.getParameter("usefulrating"));
		feedback.setAnsMaterial1(Integer.parseInt(request.getParameter("materialrating")));
		feedback.setAnsMaterial2(Integer.parseInt(request.getParameter("activityrating")));
		feedback.setAnsTrainer1(Integer.parseInt(request.getParameter("knowledgerating")));
		feedback.setAnsTrainer2(Integer.parseInt(request.getParameter("examplesrating")));
		feedback.setAnsValueLeast(request.getParameter("leastvalue"));
		feedback.setAnsValueMost(request.getParameter("mostvalue"));
		feedback.setOverallScore(Integer.parseInt(request.getParameter("overallrating")));
		feedback.setSuggestion(request.getParameter("suggestion"));

		DBHelper.submitFeedback(feedback);
		
		response.sendRedirect("pLanding.jsp");
	}

}
