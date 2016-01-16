/**
 * 
 */
package com.ca.ofs.beans;

/**
 * @author visna03
 * 
 */
public class Feedback {
	private String pmfKey;
	private int tID;

	private boolean ansGeneral;
	private int ansTrainer1;
	private int ansTrainer2;
	private int ansMaterial1;
	private int ansMaterial2;
	private int ansFacilities;

	private String ansValueMost;
	private String ansValueLeast;

	private String suggestion;
	private int overallScore;

	public Feedback() {
		super();
	}

	public Feedback(String pmfKey, int tID, boolean ansGeneral,
			int ansTrainer1, int ansTrainer2, int ansMaterial1,
			int ansMaterial2, int ansFacilities, String ansValueMost,
			String ansValueLeast, String suggestion, int overallScore) {
		super();
		this.pmfKey = pmfKey;
		this.tID = tID;
		this.ansGeneral = ansGeneral;
		this.ansTrainer1 = ansTrainer1;
		this.ansTrainer2 = ansTrainer2;
		this.ansMaterial1 = ansMaterial1;
		this.ansMaterial2 = ansMaterial2;
		this.ansFacilities = ansFacilities;
		this.ansValueMost = ansValueMost;
		this.ansValueLeast = ansValueLeast;
		this.suggestion = suggestion;
		this.overallScore = overallScore;
	}

	public String getPmfKey() {
		return pmfKey;
	}

	public void setPmfKey(String pmfKey) {
		this.pmfKey = pmfKey;
	}

	public int getTID() {
		return tID;
	}

	public void setTID(int tID) {
		this.tID = tID;
	}

	public boolean isAnsGeneral() {
		return ansGeneral;
	}

	public void setAnsGeneral(boolean ansGeneral) {
		this.ansGeneral = ansGeneral;
	}

	public int getAnsTrainer1() {
		return ansTrainer1;
	}

	public void setAnsTrainer1(int ansTrainer1) {
		this.ansTrainer1 = ansTrainer1;
	}

	public int getAnsTrainer2() {
		return ansTrainer2;
	}

	public void setAnsTrainer2(int ansTrainer2) {
		this.ansTrainer2 = ansTrainer2;
	}

	public int getAnsMaterial1() {
		return ansMaterial1;
	}

	public void setAnsMaterial1(int ansMaterial1) {
		this.ansMaterial1 = ansMaterial1;
	}

	public int getAnsMaterial2() {
		return ansMaterial2;
	}

	public void setAnsMaterial2(int ansMaterial2) {
		this.ansMaterial2 = ansMaterial2;
	}

	public int getAnsFacilities() {
		return ansFacilities;
	}

	public void setAnsFacilities(int ansFacilities) {
		this.ansFacilities = ansFacilities;
	}

	public String getAnsValueMost() {
		return ansValueMost;
	}

	public void setAnsValueMost(String ansValueMost) {
		this.ansValueMost = ansValueMost;
	}

	public String getAnsValueLeast() {
		return ansValueLeast;
	}

	public void setAnsValueLeast(String ansValueLeast) {
		this.ansValueLeast = ansValueLeast;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public int getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(int overallScore) {
		this.overallScore = overallScore;
	}

}
