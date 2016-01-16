/**
 * 
 */
package com.ca.ofs.logic;

import java.util.ArrayList;

import com.ca.ofs.beans.Feedback;

/**
 * @author visna03
 * 
 */
public class ConsolidatedFeedback {

	private ArrayList<String> ansValueMostList;

	public ArrayList<String> getAnsValueMostList() {
		return ansValueMostList;
	}

	public void setAnsValueMostList(ArrayList<String> ansValueMostList) {
		this.ansValueMostList = ansValueMostList;
	}

	public ArrayList<String> getAnsValueLeastList() {
		return ansValueLeastList;
	}

	public void setAnsValueLeastList(ArrayList<String> ansValueLeastList) {
		this.ansValueLeastList = ansValueLeastList;
	}

	public ArrayList<String> getSuggestionList() {
		return suggestionList;
	}

	public void setSuggestionList(ArrayList<String> suggestionList) {
		this.suggestionList = suggestionList;
	}

	private ArrayList<String> ansValueLeastList;
	private ArrayList<String> suggestionList;
	private int totalParticipants;
	private int totalFeedbacks;
	private float overallScore;
	private float ansFacilities;
	private float ansMaterial1;
	private float ansMaterial2;
	private float ansTrainer1;
	private float ansTrainer2;

	public ConsolidatedFeedback() {
		this.setAnsFacilities(0.0f);
		this.setAnsMaterial1(0.0f);
		this.setAnsMaterial2(0.0f);
		this.setAnsTrainer1(0.0f);
		this.setAnsTrainer2(0.0f);
		this.ansValueLeastList = new ArrayList<String>();
		this.ansValueMostList = new ArrayList<String>();
		this.suggestionList = new ArrayList<String>();
		this.setOverallScore(0.0f);
		this.totalParticipants = 0;
		this.totalFeedbacks = 0;
	}

	public void generateReport(ArrayList<Feedback> feedbacks) {
		for (Feedback fb : feedbacks) {
			this.ansFacilities += fb.getAnsFacilities();
			this.ansMaterial1 += fb.getAnsMaterial1();
			this.ansMaterial2 += fb.getAnsMaterial2();
			this.ansTrainer1 += fb.getAnsTrainer1();
			this.ansTrainer2 += fb.getAnsTrainer2();
			this.overallScore += fb.getOverallScore();
			if (!isEmptyString(fb.getAnsValueLeast())) {
				this.ansValueLeastList.add(fb.getAnsValueLeast());
			}
			if (!isEmptyString(fb.getAnsValueMost())) {
				this.ansValueMostList.add(fb.getAnsValueMost());
			}
			if (!isEmptyString(fb.getSuggestion())) {
				this.suggestionList.add(fb.getSuggestion());
			}
		}

		this.setTotalFeedbacks(feedbacks.size());
		

		this.ansFacilities /= this.totalFeedbacks;
		this.ansMaterial1 /= this.totalFeedbacks;
		this.ansMaterial2 /= this.totalFeedbacks;
		this.ansTrainer1 /= this.totalFeedbacks;
		this.ansTrainer2 /= this.totalFeedbacks;
		this.overallScore /= this.totalFeedbacks;
	}

	private boolean isEmptyString(String str) {
		str = str.trim();
		if (str.length() == 0 || str.equals("")) {
			return true;
		}
		return false;
	}

	public int getTotalParticipants() {
		return totalParticipants;
	}

	public void setTotalParticipants(int totalParticipants) {
		this.totalParticipants = totalParticipants;
	}

	public int getTotalFeedbacks() {
		return totalFeedbacks;
	}

	public void setTotalFeedbacks(int totalFeedbacks) {
		this.totalFeedbacks = totalFeedbacks;
	}

	public float getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(float overallScore) {
		this.overallScore = overallScore;
	}

	public float getAnsFacilities() {
		return ansFacilities;
	}

	public void setAnsFacilities(float ansFacilities) {
		this.ansFacilities = ansFacilities;
	}

	public float getAnsMaterial1() {
		return ansMaterial1;
	}

	public void setAnsMaterial1(float ansMaterial1) {
		this.ansMaterial1 = ansMaterial1;
	}

	public float getAnsMaterial2() {
		return ansMaterial2;
	}

	public void setAnsMaterial2(float ansMaterial2) {
		this.ansMaterial2 = ansMaterial2;
	}

	public float getAnsTrainer1() {
		return ansTrainer1;
	}

	public void setAnsTrainer1(float ansTrainer1) {
		this.ansTrainer1 = ansTrainer1;
	}

	public float getAnsTrainer2() {
		return ansTrainer2;
	}

	public void setAnsTrainer2(float ansTrainer2) {
		this.ansTrainer2 = ansTrainer2;
	}

}
