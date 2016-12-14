package com.hendall.surveyrest.datamodel;

import java.util.ArrayList;
import java.util.List;

public class SurveyersModel {
	
	private Integer surveyKey;
	private List<SurveyerUserModel> surveyerUserModels;
	
	public Integer getSurveyKey() {
		return surveyKey;
	}
	public void setSurveyKey(Integer surveyKey) {
		this.surveyKey = surveyKey;
	}
	
	public List<SurveyerUserModel> getSurveyerUserModels() {
		if (surveyerUserModels == null) {
			surveyerUserModels = new ArrayList<SurveyerUserModel>();
		}
		return surveyerUserModels;
	}
	public void setSurveyerUserModels(List<SurveyerUserModel> surveyerUserModels) {
		this.surveyerUserModels = surveyerUserModels;
	}

}
