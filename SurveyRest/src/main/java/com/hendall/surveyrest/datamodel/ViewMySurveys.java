package com.hendall.surveyrest.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hendall.surveyrest.util.ServiceUtils;

public class ViewMySurveys implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String surveyKey;
	private String surveyNumber;
	private String surveyType;
	private String surveyProvider;
	private String status;
	private Date endDate;
	private Date actionDate;
	private String supervisorComments;
	private boolean isActive = true;
	private Date startDate;
	private List<Integer> otherSurveyerKeys;


	public String getSurveyKey() {
		return surveyKey;
	}

	public void setSurveyKey(String surveyKey) {
		this.surveyKey = surveyKey;
	}

	public String getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(String surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public String getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	public String getSurveyProvider() {
		return surveyProvider;
	}

	public void setSurveyProvider(String surveyProvider) {
		this.surveyProvider = surveyProvider;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEndDateString() {
		if (endDate != null) {
			return ServiceUtils.converDateToString(endDate, ServiceUtils.DATE_FORMAT_MM_DD_YYYY);
		}
		return "";

	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionDateString() {
		if (actionDate != null) {
			return ServiceUtils.converDateToString(actionDate, ServiceUtils.DATE_FORMAT_MM_DD_YYYY);
		}
		return "";

	}

	public String getSupervisorComments() {
		return supervisorComments;
	}

	public void setSupervisorComments(String supervisorComments) {
		this.supervisorComments = supervisorComments;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartDateString() {
		if (startDate != null) {
			return ServiceUtils.converDateToString(startDate, ServiceUtils.DATE_FORMAT_MM_DD_YYYY);
		}
		return "";

	}

	public List<Integer> getOtherSurveyerKeys() {
		if (otherSurveyerKeys == null)
			otherSurveyerKeys = new ArrayList<Integer>();
		return otherSurveyerKeys;
	}

	public void setOtherSurveyerKeys(List<Integer> otherSurveyerKeys) {
		this.otherSurveyerKeys = otherSurveyerKeys;
	}



}
