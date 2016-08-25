package com.hendall.survey.services.ejb;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hendall.survey.services.assemblers.ValidationAssembler;
import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.datamodel.Question;
import com.hendall.survey.services.datamodel.Section;
import com.hendall.survey.services.datamodel.SectionHelpWrapper;
import com.hendall.survey.services.datamodel.Validation;
import com.hendall.survey.services.entities.Answers;
import com.hendall.survey.util.ServiceUtils;

@Stateless
@LocalBean
public class ValidationService extends BaseService {

	private Map<Integer, Answer> answersMap = new HashMap<Integer, Answer>();
	@EJB
	private QuestionAnswerService questionAnswerService;

	public List<Validation> getValidationsList(Integer userKey, Integer surveyKey) {

		SectionHelpWrapper questionAnswers = questionAnswerService.getQuestionAnswers(userKey, surveyKey);
		populateAnswersMap(questionAnswers.getSections());
		

		return null;
	}

	private void populateAnswersMap(List<Section> surveyQuestionSectionList) {
		for (Section section : surveyQuestionSectionList) {
			for (Question question : section.getSurveyQuestionAnswerList()) {
				for (Answer answer : question.getAnswersList()) {
					answersMap.put(answer.getHtmlControlId(), answer);
				}
			}

		}
	}

}
