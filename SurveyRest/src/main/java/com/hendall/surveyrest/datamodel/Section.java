package com.hendall.surveyrest.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class Section implements Serializable {

	private static final long serialVersionUID = 1L;

	private String version;
	private Integer UserKey;
	private Integer SurveyKey;
	private Integer userSurveyAccessKey;
	private String status;
	private String instructions;
	private String surveyName;
	private String sectionTitle;
	private List<Question> surveyQuestionAnswerList;
	private List<Question> surveyQuestionAnswerListWithErrors;
	

	public List<Question> getSurveyQuestionAnswerList() {
		if (surveyQuestionAnswerList == null) {
			surveyQuestionAnswerList = new ArrayList<Question>();
		}
		return surveyQuestionAnswerList;
	}

	public void setSurveyQuestionAnswerList(List<Question> surveyQuestionAnswerList) {
		this.surveyQuestionAnswerList = surveyQuestionAnswerList;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Integer getUserKey() {
		return UserKey;
	}

	public void setUserKey(Integer userKey) {
		UserKey = userKey;
	}

	public Integer getSurveyKey() {
		return SurveyKey;
	}

	public void setSurveyKey(Integer surveyKey) {
		SurveyKey = surveyKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUserSurveyAccessKey() {
		return userSurveyAccessKey;
	}

	public void setUserSurveyAccessKey(Integer userSurveyAccessKey) {
		this.userSurveyAccessKey = userSurveyAccessKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Question> getSurveyQuestionAnswerListWithErrors() {
		if (surveyQuestionAnswerListWithErrors == null) {
			surveyQuestionAnswerListWithErrors = new ArrayList<Question>();
		}
		return surveyQuestionAnswerListWithErrors;
	}

	public void setSurveyQuestionAnswerListWithErrors(List<Question> surveyQuestionAnswerListWithErrors) {
		this.surveyQuestionAnswerListWithErrors = surveyQuestionAnswerListWithErrors;
	}

	public void populateSurveyQuestionAnswerListWithErrors() {
		surveyQuestionAnswerListWithErrors = new ArrayList<Question>();
		if (CollectionUtils.isNotEmpty(surveyQuestionAnswerList)) {
			for (Question question : surveyQuestionAnswerList) {
				if (question.isHasErrors())
					surveyQuestionAnswerListWithErrors.add(question);
			}
		}
	}



}
