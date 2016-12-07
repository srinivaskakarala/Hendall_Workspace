package com.hendall.surveyrest.datamodel;

import java.util.Date;
import java.util.List;

public class DifferentUserAnswer {
	
	private Integer user;
	private String answer;
	private String answerDate;
	//private List<AnswerReadOnly> readOnlyAnswerList;
	
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
//	public List<AnswerReadOnly> getReadOnlyAnswerList() {
//		return readOnlyAnswerList;
//	}
//	public void setReadOnlyAnswerList(List<AnswerReadOnly> readOnlyAnswerList) {
//		this.readOnlyAnswerList = readOnlyAnswerList;
//	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
}
