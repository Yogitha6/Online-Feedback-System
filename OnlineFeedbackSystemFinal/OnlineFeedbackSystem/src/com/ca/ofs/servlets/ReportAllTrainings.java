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
import com.ca.ofs.logic.ExcelHelper;
import com.ca.ofs.utils.DBHelper;

public class ReportAllTrainings extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Generating report for all training");
		
		HttpSession session = req.getSession();
		
		ArrayList<Feedback> feedbacks = DBHelper.getConsolidateReportForAllTrainings();
		ConsolidatedFeedback cfReport = new ConsolidatedFeedback();
		cfReport.generateReport(feedbacks);
		
		System.out.println("Overall : " + cfReport.getAnsValueLeastList().get(0));
		
		Integer id = null;
		int attendees = DBHelper.getTraineeCount(id);
		cfReport.setTotalParticipants(attendees);
		
		Training selectedTraining = new Training("All", "N/A", "N/A", "N/A", null);
		session.setAttribute("selectedTraining", selectedTraining);
		session.setAttribute("report", cfReport);
		
		ExcelHelper.getReportInExcel(cfReport, "All");		
		
		RequestDispatcher rd = req.getRequestDispatcher("feedbackReport.jsp");
		rd.forward(req, resp);
	}

}
