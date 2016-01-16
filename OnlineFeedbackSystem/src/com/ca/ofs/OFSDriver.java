/**
 * 
 */
package com.ca.ofs;

import java.util.ArrayList;

import com.ca.ofs.beans.Training;
import com.ca.ofs.utils.DBHelper;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * @author visna03
 *
 */
public class OFSDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ArrayList<Employee> list = new ArrayList<Employee>();
		Employee emp1 = new Employee("visna04", "Navneet", "ASE", "SaaS Platform");
		list.add(emp1);
		Employee emp2 = new Employee("mahyo01", "Yogitha", "ASE", "APM");
		list.add(emp2);*/
		ArrayList<String> list = new ArrayList<String>();
		list.add("visna03");
		list.add("mahyo01");
		list.add("sinba02");
		Training training = new Training("Java", "Suresh", "2013-08-21", "2013-08-27", list);
		
		/*try {
			System.out.println(DBHelper.addTraining(training));
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Duplicate");
		}*/
		
		int id = DBHelper.getTrainingID(training);
		System.out.println(id);
		
	}

}
