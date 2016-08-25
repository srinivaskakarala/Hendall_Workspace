package com.hendall.surveyrest.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.collections4.CollectionUtils;

import com.hendall.surveyrest.common.ServiceConstants;
import com.hendall.surveyrest.datamodel.Answer;
import com.hendall.surveyrest.datamodel.Options;
import com.hendall.surveyrest.datamodel.Question;
import com.hendall.surveyrest.datamodel.Section;
import com.hendall.surveyrest.entities.ProvidersLu;
import com.hendall.surveyrest.entities.StatesLu;

public class InfectionControlSpecialCases {
	private static final String PROVIDERS_TYPE = "01 - Hospital";
	private static final String PROVIDERS_SUB_TYPE = "Critical Access Hospitals";
	private List<ProvidersLu> stateProvidersList;
	private EntityManager entityManager;

	public InfectionControlSpecialCases(EntityManager entityManager) {
		stateProvidersList = new ArrayList<ProvidersLu>();
		this.entityManager = entityManager;
		stateProvidersList = entityManager
				.createQuery(
						"Select A from  ProvidersLu  A Where A.providerType='" + PROVIDERS_TYPE
								+ "' and A.providerSubtype != '" + PROVIDERS_SUB_TYPE + "' order by A.facilityName",
						ProvidersLu.class)
				.getResultList();
	}

	public void processSpecialCases(List<Section> surverQuestionsList) {
		String selectedStateCode = null;
		Map<Integer, Boolean> valuesMap = new HashMap<Integer, Boolean>();

		for (Section section : surverQuestionsList) {
			for (Question question : section.getSurveyQuestionAnswerList()) {
				for (Answer answer : question.getAnswersList()) {
					if (ServiceConstants.INFECTION_CONTROL_SKIP_LOGIC_QUESTIONS.contains(question.getQuestionId())) {
						valuesMap.put(question.getQuestionId(), CollectionUtils.isNotEmpty(answer.getAnswersList()));
					}

					if (answer.getHtmlControlType().equals(ServiceConstants.HTML_CONTROL_RADIO)) {
						if ((answer != null && answer.getAnswer() != null)
								&& (answer.getAnswer().equals(ServiceConstants.UNABLE_TO_OBSERVE)
										|| answer.getAnswer().equals(ServiceConstants.NA)))
							question.setDisableAddObservation(true);
					}

					if (answer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_STATE_CONTROL_ID) {
						List<StatesLu> satesList = entityManager
								.createQuery("Select A from StatesLu A order by A.stateName ", StatesLu.class)
								.getResultList();
						answer.getHtmlOptions().clear();
						Options selectState = new Options();
						selectState.setKey("");
						selectState.setValue("Select a state");
						answer.getHtmlOptions().add(selectState);

						for (StatesLu statesLu : satesList) {
							Options option = new Options();
							option.setKey(statesLu.getStateCode());
							option.setValue(statesLu.getStateName());
							answer.getHtmlOptions().add(option);
						}
						selectedStateCode = answer.getAnswer();
					}
					if (answer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_HOSPITAL_NAME_CONTROL_ID) {
						answer.getHtmlOptions().clear();
						Options selectProvider = new Options();
						selectProvider.setKey("");
						selectProvider.setValue("Select a Hospital");
						answer.getHtmlOptions().add(selectProvider);
						Map<String, List<Options>> dependentHtmlOptionsMap = new HashMap<String, List<Options>>();

						if (selectedStateCode != null) {
							answer.setHtmlOptions(dependentHtmlOptionsMap.get(selectedStateCode));
						}
					}

				}
			}
		}
		for (Integer id : ServiceConstants.INFECTION_CONTROL_SKIP_LOGIC_QUESTIONS) {
			renderNoSecondObesrvations(surverQuestionsList, valuesMap.get(id), id);
			System.out.println(surverQuestionsList.size());
		}

	}

	private Options getOption(String key, String value) {
		Options option = new Options();
		option.setKey(key);
		option.setValue(value);
		return option;
	}

	private void renderNoSecondObesrvations(List<Section> surverQuestionsList, Boolean isCheckBoxChecked, Integer id) {
		if (isCheckBoxChecked == null || CollectionUtils.isEmpty(surverQuestionsList) || id == null)
			return;
		List<Integer> dependentList = ServiceConstants.INFECTION_CONTROL_SKIP_LOGIC_DEPENDENT_QUESTIONS.get(id);
		for (Section section : surverQuestionsList) {
			for (Question question : section.getSurveyQuestionAnswerList()) {
				if (CollectionUtils.isEmpty(dependentList))
					continue;
				if (dependentList.contains(question.getQuestionId())) {
					if (isCheckBoxChecked) {
						question.setRenderQuestion(
								ServiceConstants.INFECTION_CONTROL_SKIP_LOGIC_QUESTIONS_RENDER_TRUE.contains(id));
					} else {
						question.setRenderQuestion(
								!ServiceConstants.INFECTION_CONTROL_SKIP_LOGIC_QUESTIONS_RENDER_TRUE.contains(id));
					}
				}

			}
		}
	}

	public void addAddtionalQuestions(List<Section> surverQuestionsList) {
		if (CollectionUtils.isEmpty(surverQuestionsList)) {
			return;
		}
		Section section = new Section();
		section.setSectionTitle(ServiceConstants.INFECTION_CONTROL_ADDTIONAL_COMMENTS_SECTION_TITLE);
		section.setSurveyKey(surverQuestionsList.get(0).getSurveyKey());
		section.setUserKey(surverQuestionsList.get(0).getUserKey());
		Question question = new Question();
		question.setQuestionId(ServiceConstants.INFECTION_CONTROL_ADDTIONAL_COMMENTS_ID);
		question.setQuestionText(ServiceConstants.INFECTION_CONTROL_ADDTIONAL_COMMENTS_QUESTION);
		Answer answer = new Answer();
		answer.setHtmlControlId(ServiceConstants.INFECTION_CONTROL_ADDTIONAL_COMMENTS_ID);
		answer.setHtmlControlType(ServiceConstants.HTML_CONTROL_TEXT_AREA);
		answer.setDefaultVisible(true);

		question.getAnswersList().add(answer);
		section.getSurveyQuestionAnswerList().add(question);

		Question questionFile = new Question();
		questionFile.setQuestionId(ServiceConstants.INFECTION_CONTROL_FILE_UPLOAD_ID);
		questionFile.setQuestionText(ServiceConstants.INFECTION_CONTROL_FILE_UPLOAD_QUESTION);
		Answer answerFile = new Answer();
		answerFile.setHtmlControlId(ServiceConstants.INFECTION_CONTROL_FILE_UPLOAD_ID);
		answerFile.setHtmlControlType(ServiceConstants.HTML_CONTROL_FILE_UPLOAD);
		answerFile.setDefaultVisible(true);

		questionFile.getAnswersList().add(answerFile);
		section.getSurveyQuestionAnswerList().add(questionFile);
		surverQuestionsList.add(section);

		Question questionSupervisorComments = new Question();
		questionSupervisorComments.setQuestionId(ServiceConstants.INFECTION_CONTROL_SUPERVISOR_COMMENTS_ID);
		questionSupervisorComments
				.setQuestionText(ServiceConstants.INFECTION_CONTROL_SUPERVISOR_COMMENTS_SECTION_TITLE);
		Answer answerSupervisorComments = new Answer();
		answerSupervisorComments.setHtmlControlId(ServiceConstants.INFECTION_CONTROL_SUPERVISOR_COMMENTS_ID);
		answerSupervisorComments.setHtmlControlType(ServiceConstants.HTML_CONTROL_TEXT_AREA);
		answerSupervisorComments.setDefaultDisalbled(true);
		answerSupervisorComments.setDefaultVisible(true);
		questionSupervisorComments.getAnswersList().add(answerSupervisorComments);
		section.getSurveyQuestionAnswerList().add(questionSupervisorComments);

		/*
		 * Section emailSection = new Section();
		 * emailSection.setSectionTitle(ServiceConstants.
		 * INFECTION_CONTROL_ADDTIONAL_APPROVER_DETAILS_SECTION_TITLE);
		 * emailSection.setSurveyKey(surverQuestionsList.get(0).getSurveyKey());
		 * emailSection.setUserKey(surverQuestionsList.get(0).getUserKey());
		 * Question emailQuestion = new Question();
		 * emailQuestion.setQuestionId(ServiceConstants.
		 * INFECTION_CONTROL_APPROVER_ID);
		 * emailQuestion.setQuestionText(ServiceConstants.
		 * INFECTION_CONTROL_ADDTIONAL_APPROVER_DETAILS_QUESTION); Answer
		 * emailAnswer = new Answer();
		 * emailAnswer.setHtmlControlId(ServiceConstants.
		 * INFECTION_CONTROL_APPROVER_ID);
		 * emailAnswer.setHtmlControlType(ServiceConstants.HTML_CONTROL_TEXT_BOX
		 * ); emailAnswer.setDefaultVisible(true);
		 * emailQuestion.getAnswersList().add(emailAnswer);
		 * emailSection.getSurveyQuestionAnswerList().add(emailQuestion);
		 * surverQuestionsList.add(emailSection);
		 */

	}
}
