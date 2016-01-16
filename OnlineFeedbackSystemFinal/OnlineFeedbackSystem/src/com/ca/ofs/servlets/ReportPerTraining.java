package com.ca.ofs.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.ofs.beans.Feedback;
import com.ca.ofs.beans.Training;
import com.ca.ofs.logic.ConsolidatedFeedback;
import com.ca.ofs.utils.DBHelper;

public class ReportPerTraining extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Generating report per training");
		
		HttpSession session = req.getSession();
		
		String tidString = req.getParameter("f1TrainingId");
		int tID = Integer.parseInt(tidString);
		
		ArrayList<Feedback> feedbacks = DBHelper.getConsolidateReportForTraining(tID);
		ConsolidatedFeedback cfReport = new ConsolidatedFeedback();
		cfReport.generateReport(feedbacks);
		
		System.out.println("Overall : " + cfReport.getAnsValueLeastList().get(0));
		
		int attendees = DBHelper.getTraineeCount(tID);
		cfReport.setTotalParticipants(attendees);
		
		Training selectedTraining = DBHelper.getTraining(tID);
		session.setAttribute("selectedTraining", selectedTraining);
		session.setAttribute("report", cfReport);
		
		
		RequestDispatcher rd = req.getRequestDispatcher("feedbackReport.jsp");
		rd.forward(req, resp);
	}

}
