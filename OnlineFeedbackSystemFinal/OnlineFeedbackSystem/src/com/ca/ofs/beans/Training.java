/**
 * 
 */
package com.ca.ofs.beans;

import java.util.ArrayList;

/**
 * @author visna03
 * 
 */
public class Training {
	private int id;
	private String trainingName;
	private String trainerName;
	private String startDate;
	private String endDate;
	private ArrayList<String> participants;

	public Training() {
		super();
	}

	public Training(String trainingName, String trainerName, String startDate,
			String endDate, ArrayList<String> participants) {
		super();
		this.trainingName = trainingName;
		this.trainerName = trainerName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.participants = participants;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getStartDate() {
		return startDate;
	}

	/*public String getStartDateString() {
		DateFormat df = new SimpleDateFormat(OFSCommons.DATE_FORMAT);
		String dateString = df.format(startDate);
		return dateString;
	}*/

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	/*public String getEndDateString() {
		DateFormat df = new SimpleDateFormat(OFSCommons.DATE_FORMAT);
		String dateString = df.format(endDate);
		return dateString;
	}*/

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ArrayList<String> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<String> participants) {
		this.participants = participants;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
