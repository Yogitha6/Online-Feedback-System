package com.ca.ofs.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import com.ca.ofs.beans.Training;
import com.ca.ofs.utils.DBHelper;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class AddTrainingSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public AddTrainingSchedule() {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String topic = request.getParameter("sub");
		String trainer = request.getParameter("name");
		String startDate = request.getParameter("sdate");
		String endDate = request.getParameter("edate");

		String pListString = request.getParameter("add");
		String filepath = request.getParameter("addfile");

		System.out.println("listString: '" + pListString + "'");
		System.out.println("filepath : " + filepath);

		ArrayList<String> list = new ArrayList<String>();

		if (pListString != null && !pListString.equals("")) {

			System.out.println("in manual method");
			String[] splitted = pListString.split(",");
			for (String p : splitted) {
				list.add(p);
			}
		} else if (filepath != null) {
			System.out.println("in file method");
			try {
				ServletContext context = getServletContext();
				String fullPath = context.getRealPath(filepath);
				System.out.println(filepath + " full: " + fullPath);
				

				Workbook wb = Workbook.getWorkbook(new File(filepath));
				System.out.println(wb.getNumberOfSheets());
				for (int sheetNo = 0; sheetNo < wb.getNumberOfSheets(); sheetNo++) {
					Sheet sheet = wb.getSheet(sheetNo);
					int columns = sheet.getColumns();
					int rows = sheet.getRows();
					String data;
					for (int row = 0; row < rows; row++) {
						for (int col = 0; col < columns; col++) {
							data = sheet.getCell(col, row).getContents();
							System.out.println(data);
							list.add(data);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Training t = new Training(topic, trainer, startDate, endDate, list);
		try {
			int id = DBHelper.addTraining(t);
			System.out.println(id);
			DBHelper.addParticipants(id, list);
			response.sendRedirect("cLanding.jsp");
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}

	}

}
