/**
 * 
 */
package com.ca.ofs.beans;

/**
 * @author visna03
 * 
 */
public class Employee {

	private String pmfKey;
	private String name;
	private String designation;
	private String team;

	public Employee() {
		super();
	}

	public Employee(String pmfKey, String name, String designation, String team) {
		super();
		this.pmfKey = pmfKey;
		this.name = name;
		this.designation = designation;
		this.team = team;
	}

	public String getPmfKey() {
		return pmfKey;
	}

	public void setPmfKey(String pmfKey) {
		this.pmfKey = pmfKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
