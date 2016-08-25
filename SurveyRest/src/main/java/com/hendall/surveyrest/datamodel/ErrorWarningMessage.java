package com.hendall.surveyrest.datamodel;

public class ErrorWarningMessage {
	
	private String message;
	private String messageType;
	private int htmlControlId;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
