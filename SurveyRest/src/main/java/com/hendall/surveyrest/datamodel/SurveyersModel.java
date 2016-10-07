package com.hendall.surveyrest.datamodel;

import java.util.ArrayList;
import java.util.List;

public class SurveyersModel {
	
	private Integer surveyKey;
	private List<Integer> userKeys;
	
	public Integer getSurveyKey() {
		return surveyKey;
	}
	public void setSurveyKey(Integer surveyKey) {
		this.surveyKey = surveyKey;
	}
	
	public List<Integer> getUserKeys() {
		if (userKeys == null) {
			userKeys = new ArrayList<Integer>();
		}
		return userKeys;
	}
	public void setUserKeys(List<Integer> userKeys) {
		this.userKeys = userKeys;
	}

}
