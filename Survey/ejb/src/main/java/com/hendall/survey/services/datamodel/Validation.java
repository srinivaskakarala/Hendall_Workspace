package com.hendall.survey.services.datamodel;

public class Validation {
	private int sectionIndex;
	private int htmlControlId;
	private String question;
	private String message;
	private String errorMessageCompId;
	private String errorOrWarning;

	public int getHtmlControlId() {
		return htmlControlId;
	}

	public void setHtmlControlId(int htmlControlId) {
		this.htmlControlId = htmlControlId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSectionIndex() {
		return sectionIndex;
	}

	public void setSectionIndex(int sectionIndex) {
		this.sectionIndex = sectionIndex;
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
