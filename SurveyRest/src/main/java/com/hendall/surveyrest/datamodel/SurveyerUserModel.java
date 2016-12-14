package com.hendall.surveyrest.datamodel;

public class SurveyerUserModel {
	
	private Integer userKey;
	private Boolean Delete;
	
	public Integer getUserKey() {
		return userKey;
	}
	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}
	
	public Boolean getDelete() {
		return Delete;
	}
	public void setDelete(Boolean delete) {
		Delete = delete;
	}

}
