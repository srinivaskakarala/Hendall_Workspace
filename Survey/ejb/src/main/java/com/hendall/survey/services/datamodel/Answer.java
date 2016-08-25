package com.hendall.survey.services.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Answer implements Serializable {
	private String htmlControlType;
	private String htmlControlText;
	private String answer;
	private List<String> answersList;
	private Date answerDate;
	private boolean defaultVisible;
	private boolean defaultDisalbled = false;
	private List<Options> htmlOptions;
	private int htmlControlId;
	private int parentId;
	private List<Answer> childIdList;
	private boolean required;
	private String fieldType;
	private String fieldLength;
	private String placeHolderText;
	private Map<String, List<Options>> dependentHtmlOptionsMap = new HashMap<String, List<Options>>();
	private boolean renderRemoveButton;
	private int observationNumber = 0;
	private String message;
	private String mesageType;

	public String getHtmlControlType() {
		return htmlControlType;
	}

	public void setHtmlControlType(String htmlControlType) {
		this.htmlControlType = htmlControlType;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<String> getAnswersList() {
		if (answersList == null) {
			answersList = new ArrayList<String>();
		}
		return answersList;
	}

	public void setAnswersList(List<String> answersList) {
		this.answersList = answersList;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getHtmlControlText() {
		return htmlControlText;
	}

	public void setHtmlControlText(String htmlControlText) {
		this.htmlControlText = htmlControlText;
	}

	public List<Options> getHtmlOptions() {
		if (htmlOptions == null) {
			htmlOptions = new ArrayList<Options>();
		}
		return htmlOptions;
	}

	public void setHtmlOptions(List<Options> htmlOptions) {
		this.htmlOptions = htmlOptions;
	}

	public int getHtmlControlId() {
		return htmlControlId;
	}

	public void setHtmlControlId(int htmlControlId) {
		this.htmlControlId = htmlControlId;
	}

	public boolean isDefaultVisible() {
		return defaultVisible;
	}

	public void setDefaultVisible(boolean defaultVisible) {
		this.defaultVisible = defaultVisible;
	}

	public boolean isRenderRemoveButton() {
		return renderRemoveButton;
	}

	public void setRenderRemoveButton(boolean renderRemoveButton) {
		this.renderRemoveButton = renderRemoveButton;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<Answer> getChildIdList() {
		if (childIdList == null)
			childIdList = new ArrayList<Answer>();
		return childIdList;
	}

	public void setChildIdList(List<Answer> childIdList) {
		this.childIdList = childIdList;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}

	public Map<String, List<Options>> getDependentHtmlOptionsMap() {
		return dependentHtmlOptionsMap;
	}

	public void setDependentHtmlOptionsMap(Map<String, List<Options>> dependentHtmlOptionsMap) {
		this.dependentHtmlOptionsMap = dependentHtmlOptionsMap;
	}

	public boolean isDefaultDisalbled() {
		return defaultDisalbled;
	}

	public void setDefaultDisalbled(boolean defaultDisalbled) {
		this.defaultDisalbled = defaultDisalbled;
	}

	public String getPlaceHolderText() {
		return placeHolderText;
	}

	public void setPlaceHolderText(String placeHolderText) {
		this.placeHolderText = placeHolderText;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMesageType() {
		return mesageType;
	}

	public void setMesageType(String mesageType) {
		this.mesageType = mesageType;
	}

	public int getObservationNumber() {
		return observationNumber;
	}

	public void setObservationNumber(int observationNumber) {
		this.observationNumber = observationNumber;
	}

	public String getObservatgionNumberString() {
		
		return String.valueOf(this.observationNumber / 2);
	}
}
