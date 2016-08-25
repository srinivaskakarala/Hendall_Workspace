package com.hendall.survey.services.datamodel;

import java.io.Serializable;
import java.util.Date;

import com.hendall.survey.util.ServiceUtils;

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
	private Date startDate;

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

}
