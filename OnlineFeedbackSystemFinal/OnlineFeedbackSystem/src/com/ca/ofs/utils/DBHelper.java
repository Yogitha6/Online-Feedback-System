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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private static Connection getConnection() {
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

	public static String validateUser(String username, String password) {

		Connection connection = getConnection();
		if (connection != null) {

			String query = "SELECT password, role FROM login WHERE user='"
					+ username + "'";
			try {
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				if (rs.next()) {
					String psswd = rs.getString("password");
					if (password.equals(psswd)) {
						String role = rs.getString("role");
						return role;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * Insert single employee data into employees table
	 * 
	 * @param emp
	 */
	public static void addEmployee(Employee emp) {
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO employees VALUES(?,?,?,?)";
			try {
				PreparedStatement pStatement = connection
						.prepareStatement(query);

				pStatement.setString(1, emp.getPmfKey());
				pStatement.setString(2, emp.getName());
				pStatement.setString(3, emp.getDesignation());
				pStatement.setString(4, emp.getTeam());

				pStatement.execute();

				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Insert multiple employee data into employees table
	 * 
	 * @param empList
	 */
	public static void addEmployees(ArrayList<Employee> empList) {
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO employees VALUES(?,?,?,?)";
			try {
				PreparedStatement pStatement = connection
						.prepareStatement(query);

				for (Employee emp : empList) {
					pStatement.setString(1, emp.getPmfKey());
					pStatement.setString(2, emp.getName());
					pStatement.setString(3, emp.getDesignation());
					pStatement.setString(4, emp.getTeam());
					pStatement.addBatch();
				}
				pStatement.executeBatch();

				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Employee getEmployee(String pmf) {
		Connection connection = getConnection();
		if (connection != null) {
			String query = "Select * FROM employees WHERE pmf='" + pmf + "'";
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				if (rs.next()) {
					Employee emp = new Employee(pmf, rs.getString("name"), rs.getString("designation"), rs.getString("team"));
					return emp;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static int addTraining(Training training)
			throws com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException {
		int id = -1;
		Connection connection = getConnection();
		if (connection != null) {
			String insertQuery = "INSERT INTO trainings (topic, trainer, start_date, end_date) VALUES(?,?,?,?)";
			try {
				PreparedStatement pStatement = connection
						.prepareStatement(insertQuery);

				pStatement.setString(1, training.getTrainingName());
				pStatement.setString(2, training.getTrainerName());
				pStatement.setString(3, training.getStartDate());
				pStatement.setString(4, training.getEndDate());

				pStatement.execute();

				pStatement.close();

				id = getTrainingID(training);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id;
	}

	public static int getTrainingID(Training training) {
		int id = -1;
		Connection connection = getConnection();
		if (connection != null) {
			String idQuery = "SELECT t_id FROM trainings WHERE topic='"
					+ training.getTrainingName() + "' AND trainer='"
					+ training.getTrainerName() + "' AND start_date='"
					+ training.getStartDate() + "' AND end_date='"
					+ training.getEndDate() + "'";
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(idQuery);
				if (rs.next()) {
					id = rs.getInt("t_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id;
	}

	public static void addParticipants(int tID, List<String> list) {
		Connection connection = getConnection();
		if (connection != null) {
			try {
				String query = "INSERT INTO training_user_map (t_id, pmf) VALUES(?,?)";
				PreparedStatement pStatement = connection
						.prepareStatement(query);

				for (String pmf : list) {
					pStatement.setInt(1, tID);
					pStatement.setString(2, pmf);
					pStatement.addBatch();
				}
				pStatement.executeBatch();

				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void addCoordinator(String pmf, String password, String role) {
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO login VALUES(?,?,?)";
			try {
				PreparedStatement pStatement = connection.prepareStatement(query);
				
				pStatement.setString(1, pmf);
				pStatement.setString(2, password);
				pStatement.setString(3, role);
				
				pStatement.execute();
				
				pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void submitFeedback(Feedback feedback) {
		int tID = feedback.getTID();
		String pmf = feedback.getPmfKey();

		int feedbackID = getFeedbackID(pmf, tID);
		Connection connection = getConnection();
		if (connection != null) {
			String query = "INSERT INTO feedbacks VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pStatement = connection
						.prepareStatement(query);

				pStatement.setInt(1, feedbackID);
				pStatement.setString(2, feedback.getAnsGeneral());
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
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static int getFeedbackID(String pmf, int tID) {
		int id = -1;
		Connection connection = getConnection();
		if (connection != null) {
			String idQuery = "SELECT fb_id FROM training_user_map WHERE t_id='"
					+ tID + "' AND pmf='" + pmf + "'";
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(idQuery);
				if (rs.next()) {
					id = rs.getInt("fb_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id;
	}

	/**
	 * 
	 * @param pmf
	 * @param date
	 *            (format : yyyy-MM-dd)**
	 * @return
	 */
	public static List<Training> getTrainingByDate(String pmf, String date) {
		List<Training> trainings = new ArrayList<Training>();
		Connection connection = getConnection();
		if (connection != null) {
			String query = "SELECT * FROM trainings NATURAL JOIN training_user_map WHERE pmf='"
					+ pmf + "' AND end_date='" + date + "'";
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Training training = new Training(rs.getString("topic"),
							rs.getString("trainer"),
							rs.getString("start_date"),
							rs.getString("end_date"), null);
					training.setId(rs.getInt("t_id"));
					trainings.add(training);
				}
				System.out.println("length " + trainings.size());
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return trainings;
	}
	
	public static ArrayList<String> getAllTrainers() {
		ArrayList<String> trainers = new ArrayList<String>();
		Connection connection = getConnection();
		if (connection != null) {
			String query = "SELECT DISTINCT trainer FROM trainings";
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					String trainer = rs.getString("trainer");
					trainers.add(trainer);
				}
				System.out.println("length " + trainers.size());
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return trainers;
	}
	
	public static ArrayList<Training> getAllTrainings() {
		ArrayList<Training> trainings = new ArrayList<Training>();
		Connection connection = getConnection();
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(OFSCommons.DATE_FORMAT);
		String currDate = df.format(date);
		if (connection != null) {
			String query = "SELECT * FROM trainings WHERE end_date<='" + currDate + "'";
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Training training = new Training(rs.getString("topic"),
							rs.getString("trainer"),
							rs.getString("start_date"),
							rs.getString("end_date"), null);
					training.setId(rs.getInt("t_id"));
					trainings.add(training);
				}
				System.out.println("length " + trainings.size());
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return trainings;
	}
	
	public static int getTraineeCount(Integer tId) {
		int count = -1;
		
		Connection connection = getConnection();
		if (connection != null) {
			String query = "SELECT COUNT(fb_id) FROM  training_user_map WHERE t_id='" + tId + "'";
			if(tId == null) {
				query = "SELECT COUNT(fb_id) FROM  training_user_map";
			}
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	
	public static int getTraineeCount(String trainer) {
		int count = -1;
		
		Connection connection = getConnection();
		if (connection != null) {
			String query = "SELECT COUNT(t_id) FROM trainings NATURAL JOIN training_user_map WHERE trainer='" + trainer + "'";
			if(trainer == null) {
				query = "SELECT COUNT(t_id) FROM trainings NATURAL JOIN training_user_map";
			}
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	//select * from trainings natural join training_user_map where trainer='Deepak Mishra'

	public static ArrayList<Feedback> getConsolidateReportForTraining(int tId) {
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		Connection connection = getConnection();
		if (connection != null) {
			String query = "SELECT * FROM feedbacks NATURAL JOIN training_user_map WHERE t_id='"
					+ tId + "'";
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Feedback fb = new Feedback(rs.getString("pmf"),
							rs.getInt("t_id"), rs.getString("ans_general"),
							rs.getInt("ans_trainer1"),
							rs.getInt("ans_trainer2"),
							rs.getInt("ans_material1"),
							rs.getInt("ans_material2"),
							rs.getInt("ans_facilities"),
							rs.getString("ans_value_most"),
							rs.getString("ans_value_least"),
							rs.getString("suggestion"),
							rs.getInt("overal_score"));

					feedbacks.add(fb);
				}
				System.out.println("length " + feedbacks.size());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return feedbacks;
	}
	
	public static ArrayList<Feedback> getConsolidateReportForTrainer(String trainer, String date1, String date2) {
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		Connection connection = getConnection();
		if (connection != null) {
			String query = "SELECT * FROM trainings NATURAL JOIN training_user_map NATURAL JOIN feedbacks WHERE trainer='" + trainer + "' AND end_date BETWEEN '" + date1 + "' AND '" + date2 + "'";
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Feedback fb = new Feedback(rs.getString("pmf"),
							rs.getInt("t_id"), rs.getString("ans_general"),
							rs.getInt("ans_trainer1"),
							rs.getInt("ans_trainer2"),
							rs.getInt("ans_material1"),
							rs.getInt("ans_material2"),
							rs.getInt("ans_facilities"),
							rs.getString("ans_value_most"),
							rs.getString("ans_value_least"),
							rs.getString("suggestion"),
							rs.getInt("overal_score"));

					feedbacks.add(fb);
				}
				System.out.println("length " + feedbacks.size());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return feedbacks;
	}
	
	public static ArrayList<Feedback> getConsolidateReportForAllTrainings() {
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		Connection connection = getConnection();
		if (connection != null) {
			String query = "SELECT * FROM feedbacks NATURAL JOIN training_user_map";
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Feedback fb = new Feedback(rs.getString("pmf"),
							rs.getInt("t_id"), rs.getString("ans_general"),
							rs.getInt("ans_trainer1"),
							rs.getInt("ans_trainer2"),
							rs.getInt("ans_material1"),
							rs.getInt("ans_material2"),
							rs.getInt("ans_facilities"),
							rs.getString("ans_value_most"),
							rs.getString("ans_value_least"),
							rs.getString("suggestion"),
							rs.getInt("overal_score"));

					feedbacks.add(fb);
				}
				System.out.println("length " + feedbacks.size());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return feedbacks;
	}
	
	public static Training getTraining(int tID) {
		Connection connection = getConnection();
		if (connection != null) {
			String idQuery = "SELECT * FROM trainings WHERE t_id='" + tID + "'";
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(idQuery);
				if (rs.next()) {
					Training training = new Training(rs.getString("topic"), rs.getString("trainer"), rs.getString("start_date"), rs.getString("end_date"), null);
					return training;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}