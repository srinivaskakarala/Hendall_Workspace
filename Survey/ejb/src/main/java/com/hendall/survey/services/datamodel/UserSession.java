package com.hendall.survey.services.datamodel;

public class UserSession {

	private Integer userKey;
	private String userName;
	private String firstName;
	private String lastName;
	private Integer role;
	private String currentSurveyStatus;
	private String email;
	private String state;

	public Integer getUserKey() {
		return userKey;
	}

	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getCurrentSurveyStatus() {
		return currentSurveyStatus;
	}

	public void setCurrentSurveyStatus(String currentSurveyStatus) {
		this.currentSurveyStatus = currentSurveyStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	

}
