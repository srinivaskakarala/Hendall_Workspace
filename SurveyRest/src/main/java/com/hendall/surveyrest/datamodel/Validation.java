package com.hendall.surveyrest.datamodel;

import java.util.List;

public class Validation {
	private int sectionIndex;
	private int questionId;
	private String question;
	private List<ErrorWarningMessage> errorWarningsList;
	private String message;
	private String errorMessageCompId;
	private String errorOrWarning;
	public int getSectionIndex() {
		return sectionIndex;
	}
	public void setSectionIndex(int sectionIndex) {
		this.sectionIndex = sectionIndex;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<ErrorWarningMessage> getErrorWarningsList() {
		return errorWarningsList;
	}
	public void setErrorWarningsList(List<ErrorWarningMessage> errorWarningsList) {
		this.errorWarningsList = errorWarningsList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMessageCompId() {
		return errorMessageCompId;
	}
	public void setErrorMessageCompId(String errorMessageCompId) {
		this.errorMessageCompId = errorMessageCompId;
	}
	public String getErrorOrWarning() {
		return errorOrWarning;
	}
	public void setErrorOrWarning(String errorOrWarning) {
		this.errorOrWarning = errorOrWarning;
	}


	

}
