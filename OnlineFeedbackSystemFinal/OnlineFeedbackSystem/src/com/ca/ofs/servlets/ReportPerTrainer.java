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

public class ReportPerTrainer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Generating report per trainer");
		
		HttpSession session = req.getSession();

		String trainer = req.getParameter("f2Trainer");
		System.out.println("trainer: " + trainer);
		String date1 = req.getParameter("date1");
		String date2 = req.getParameter("date2");

		ArrayList<Feedback> feedbacks = DBHelper
				.getConsolidateReportForTrainer(trainer, date1, date2);
		ConsolidatedFeedback cfReport = new ConsolidatedFeedback();
		cfReport.generateReport(feedbacks);

		int attendees = DBHelper.getTraineeCount(trainer);
		cfReport.setTotalParticipants(attendees);

		Training selectedTraining = new Training("All", trainer, "N/A", "N/A", null);
		session.setAttribute("selectedTraining", selectedTraining);
		session.setAttribute("report", cfReport);

		RequestDispatcher rd = req.getRequestDispatcher("feedbackReport.jsp");
		rd.forward(req, resp);
	}

}
