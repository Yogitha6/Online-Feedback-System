/**
 * 
 */
package com.ca.ofs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ca.ofs.beans.Employee;
import com.ca.ofs.beans.Feedback;
import com.ca.ofs.beans.Training;

/**
 * @author visna03
 * @version 1.0
 * 
 *          Database Utility class to interact with database
 * 
 */
public class DBHelper {

	/**
	 * Creates a connection with MySQL DB and returns the connection object
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(OFSCommons.DB_DRIVER);
			connection = DriverManager.getConnection(OFSCommons.DB_URL + "/"
					+ OFSCommons.DB_NAME, OFSCommons.DB_USER,
					OFSCommons.DB_PSSWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Closes the given connection with MySQL DB
	 * 
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert single employee data into employees table
	 * @param emp
	 */
	public static void addEmployee(Employee emp) {
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO employees VALUES(?,?,?,?)";
			try {
				PreparedStatement pStatement = connection.prepareStatement(query);
				
				pStatement.setString(1, emp.getPmfKey());
				pStatement.setString(2, emp.getName());
				pStatement.setString(3, emp.getDesignation());
				pStatement.setString(4, emp.getTeam());
				
				pStatement.execute();
				
				pStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Insert multiple employee data into employees table
	 * @param empList
	 */
	public static void addEmployees(ArrayList<Employee> empList) {
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO employees VALUES(?,?,?,?)";
			try {
				PreparedStatement pStatement = connection.prepareStatement(query);
				
				for (Employee emp : empList) {
					pStatement.setString(1, emp.getPmfKey());
					pStatement.setString(2, emp.getName());
					pStatement.setString(3, emp.getDesignation());
					pStatement.setString(4, emp.getTeam());
					pStatement.addBatch();
				}
				pStatement.executeBatch();
				
				pStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int addTraining(Training training) throws com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException{
		int id = -1;
		Connection connection = getConnection();
		if (connection != null) {
			String insertQuery = "INSERT INTO trainings (topic, trainer, start_date, end_date) VALUES(?,?,?,?)";
			try {
				PreparedStatement pStatement = connection.prepareStatement(insertQuery);
				
				pStatement.setString(1, training.getTrainingName());
				pStatement.setString(2, training.getTrainerName());
				pStatement.setString(3, training.getStartDate());
				pStatement.setString(4, training.getEndDate());
				
				pStatement.execute();
				
				pStatement.close();
				
				id = getTrainingID(training);
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public static int getTrainingID(Training training) {
		int id = -1;
		Connection connection = getConnection();
		if (connection != null) {
			String idQuery = "SELECT t_id FROM trainings WHERE topic='" + training.getTrainingName() + "' AND trainer='" + training.getTrainerName() + "' AND start_date='" + training.getStartDate() + "' AND end_date='" + training.getEndDate() + "'";
			try {				
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(idQuery);
				if(rs.next()) {
					id = rs.getInt("t_id");
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public static void addParticipants(int tID, List<String> list) {
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO training_user_map (t_id, pmf) VALUES(?,?)";
			try {
				PreparedStatement pStatement = connection.prepareStatement(query);
				
				for (String pmf : list) {
					pStatement.setInt(1, tID);
					pStatement.setString(2, pmf);
					pStatement.addBatch();
				}
				pStatement.executeBatch();
				
				pStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addCordinator(String pmf, String password, String role) {
	}
	
	public static void submitFeedback(Feedback feedback) {
		int tID = feedback.getTID();
		String pmf = feedback.getPmfKey();
		
		int feedbackID = getFeedbackID(pmf, tID);
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO feedbacks VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pStatement = connection.prepareStatement(query);
				
				pStatement.setInt(1, feedbackID);
				pStatement.setBoolean(2, feedback.isAnsGeneral());
				pStatement.setInt(3, feedback.getAnsTrainer1());
				pStatement.setInt(4, feedback.getAnsTrainer2());
				pStatement.setInt(5, feedback.getAnsMaterial1());
				pStatement.setInt(6, feedback.getAnsMaterial2());
				pStatement.setInt(7, feedback.getAnsFacilities());
				pStatement.setString(8, feedback.getAnsValueMost());
				pStatement.setString(9, feedback.getAnsValueLeast());
				pStatement.setString(10, feedback.getSuggestion());
				pStatement.setInt(11, feedback.getOverallScore());
				
				pStatement.execute();
				
				pStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getFeedbackID(String pmf, int tID) {
		int id = -1;
		Connection connection = getConnection();
		if (connection != null) {
			String idQuery = "SELECT fb_id FROM training_user_map WHERE t_id='" + tID + "' AND pmf='" + pmf + "'";
			try {				
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(idQuery);
				if(rs.next()) {
					id = rs.getInt("fb_id");
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public static List getTodayTrainings()
	{
		Connection connection=getConnection();
		if(connection !=null)
		{
			String trainingQuery="SELECT "
		}
	}
}
