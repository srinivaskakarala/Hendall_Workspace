package com.hendall.surveyrest.dto;

import java.io.Serializable;

public class StatesDTO implements Serializable {
	
	private String stateCode;
	private String stateName;
	
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
