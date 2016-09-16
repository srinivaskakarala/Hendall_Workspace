package com.hendall.surveyrest.datamodel;

public class AnswerReadOnly {
	
	private String selectValue;
	private String textValue;
	private int observationNumber;
	
	public String getSelectValue() {
		return selectValue;
	}
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}
	public int getObservationNumber() {
		return observationNumber;
	}
	public void setObservationNumber(int observationNumber) {
		this.observationNumber = observationNumber;
	}

}
