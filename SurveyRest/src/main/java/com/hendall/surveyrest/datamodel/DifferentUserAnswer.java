package com.hendall.surveyrest.datamodel;

import java.util.List;

public class DifferentUserAnswer {
	
	private String user;
	private List<AnswerReadOnly> readOnlyAnswerList;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<AnswerReadOnly> getReadOnlyAnswerList() {
		return readOnlyAnswerList;
	}
	public void setReadOnlyAnswerList(List<AnswerReadOnly> readOnlyAnswerList) {
		this.readOnlyAnswerList = readOnlyAnswerList;
	}

}
