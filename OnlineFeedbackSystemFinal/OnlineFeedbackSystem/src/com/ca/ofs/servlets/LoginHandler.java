/**
 * 
 */
package com.ca.ofs.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ca.ofs.beans.Employee;
import com.ca.ofs.beans.Training;
import com.ca.ofs.utils.DBHelper;

/**
 * @author visna03
 *
 */
public class LoginHandler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Login Handler");
		
		String userName = req.getParameter("username");	
		String password = req.getParameter("pwd");
		String isParticipant = req.getParameter("participantvalidation");
		
		HttpSession session = req.getSession();
		
		if(isParticipant != null && isParticipant.equals("true")) {
			Date currDate = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String currDateString = df.format(currDate);
			ArrayList<Training> trainings = (ArrayList<Training>) DBHelper.getTrainingByDate(userName, currDateString);
			if (trainings != null) {
				ArrayList<String> tStrings = getTrainingList(trainings);
				session.setAttribute("username", userName);
				session.setAttribute("trainings", tStrings);
				session.setAttribute("trainingsObj", trainings);
				
				Employee emp = DBHelper.getEmployee(userName);
				session.setAttribute("name", emp.getName());
				session.setAttribute("desg", emp.getDesignation());
				
				RequestDispatcher rd = req.getRequestDispatcher("pLanding.jsp");
				rd.forward(req, resp);
			}
		} else {
			String role = DBHelper.validateUser(userName, password);
			if (role != null) {
				session.setAttribute("username", userName);
				
				Employee emp = DBHelper.getEmployee(userName);
				session.setAttribute("name", emp.getName());
				session.setAttribute("desg", emp.getDesignation());
				
				if(role.equals("a")) {
					RequestDispatcher rd = req.getRequestDispatcher("aLanding.jsp");
					rd.forward(req, resp);
				} else if(role.equals("c")) {
					RequestDispatcher rd = req.getRequestDispatcher("cLanding.jsp");
					rd.forward(req, resp);
				}
			}
			
		}
	}
	
	public ArrayList<String> getTrainingList(ArrayList<Training> trainings) {
		ArrayList<String> tList = new ArrayList<String>();
		for (Training t : trainings) {
			String tString = "";
			tString += t.getTrainingName() + " by " + t.getTrainerName() + " (" + t.getStartDate() + " to " + t.getEndDate() + ")";
			tList.add(tString);
		}
		return tList;
	}
}
