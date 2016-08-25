package com.hendall.survey.services.ejb;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.datamodel.Question;
import com.hendall.survey.services.datamodel.Section;
import com.hendall.survey.services.datamodel.Validation;

public class InfectionControlValidation {

	public List<Validation> validate(List<Section> surveyQuestionSectionList,  Map<Integer, Answer> answersMap) {
		for (Section section : surveyQuestionSectionList) {
			for (Question question : section.getSurveyQuestionAnswerList()) {
				validate(question);
			}
		}
		return null;
	}

	private void validate(Question question) {
		//Validate Start date and end date
		List<Answer> answersList = question.getAnswersList();
		for (Answer answer : answersList) {
			if(answer.isRequired()&&StringUtils.isEmpty(answer.getAnswer())&&ServiceConstants.singleValueCompoents.contains(answer.getHtmlControlType())){
				
				
			}
			
		}
		
	}

	private Validation getValidationObject(String message, String messageType){
		Validation validation = new Validation();
		validation.setMessage(message);
		validation.setMessage(messageType);;
		return validation;
	}
	

}
