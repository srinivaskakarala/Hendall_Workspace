package com.hendall.surveyrest.datamodel;

import java.io.Serializable;

public class UserData implements Serializable{
	private Integer userKey;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int role;
	private String state;
	private Boolean hasSurveys;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserKey() {
		return userKey;
	}
	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}
	public String getNameAndEmail(){
		return ((firstName!=null)?firstName:"")+"  "+((lastName!=null)?lastName:"")+ " "+((email!=null)?(" ("+email+")"):"");
	}
	public Boolean getHasSurveys() {
		return hasSurveys;
	}
	public void setHasSurveys(Boolean hasSurveys) {
		this.hasSurveys = hasSurveys;
	}
	

}
