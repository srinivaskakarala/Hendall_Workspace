package com.hendall.survey.services.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.collections4.CollectionUtils;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.datamodel.Options;
import com.hendall.survey.services.datamodel.Question;
import com.hendall.survey.services.datamodel.Section;
import com.hendall.survey.services.entities.ProvidersLu;
import com.hendall.survey.services.entities.StatesLu;

public class InfectionControlSpecialCases {
	private static final String PROVIDERS_TYPE ="01 - Hospital";
	private static final String PROVIDERS_SUB_TYPE ="Critical Access Hospitals";
	private List<ProvidersLu> stateProvidersList;
	private EntityManager entityManager;
	public InfectionControlSpecialCases(EntityManager entityManager) {
		stateProvidersList = new ArrayList<ProvidersLu>();
		this.entityManager=entityManager;
		stateProvidersList = entityManager
				.createQuery("Select A from  ProvidersLu  A Where A.providerType='"+PROVIDERS_TYPE+"' and A.providerSubtype != '"+PROVIDERS_SUB_TYPE+"'", ProvidersLu.class).getResultList();
	}

	public void processSpecialCases(List<Section> surverQuestionsList) {
		String selectedStateCode = null;
		Map<Integer, String> valuesMap = new HashMap<Integer, String>();

		for (Section section : surverQuestionsList) {
			for (Question question : section.getSurveyQuestionAnswerList()) {
				for (Answer answer : question.getAnswersList()) {
					if (ServiceConstants.INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST
							.contains(answer.getHtmlControlId())) {
						valuesMap.put(answer.getHtmlControlId(), answer.getAnswer());
					}
					if (answer.getHtmlControlType().equals(ServiceConstants.HTML_CONTROL_RADIO)) {
						if ((answer != null && answer.getAnswer() != null)
								&& (answer.getAnswer().equals(ServiceConstants.UNABLE_TO_OBSERVE)
										|| answer.getAnswer().equals(ServiceConstants.NA)))
							question.setDisableAddObservation(true);
					}

					if (answer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_STATE_CONTROL_ID) {
						List<StatesLu> satesList = entityManager.createQuery("Select A from StatesLu A", StatesLu.class)
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
					if (answer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_HOSPITAL_CCN_ID) {
						answer.setDefaultDisalbled(true);
						
						Map<String, List<Options>> dependentHtmlOptionsMap = new HashMap<String, List<Options>>();
						for (ProvidersLu providersLu : stateProvidersList) {
							Options option = getOption(providersLu.getProviderKey().toString(), providersLu.getCcn());
							if (dependentHtmlOptionsMap.containsKey(providersLu.getProviderKey())) {
								dependentHtmlOptionsMap.get(providersLu.getProviderKey()).add(option);
							} else {
								List<Options> arrayList = new ArrayList<Options>();
								arrayList.add(option);
								dependentHtmlOptionsMap.put(providersLu.getProviderKey().toString(), arrayList);
							}

						}
						answer.setDependentHtmlOptionsMap(dependentHtmlOptionsMap);
					}
					if (answer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_HOSPITAL_NAME_CONTROL_ID) {
						answer.getHtmlOptions().clear();
						Options selectProvider = new Options();
						selectProvider.setKey("");
						selectProvider.setValue("Select a Hospital");
						answer.getHtmlOptions().add(selectProvider);
						Map<String, List<Options>> dependentHtmlOptionsMap = new HashMap<String, List<Options>>();
						for (ProvidersLu stateProviderLu : stateProvidersList) {
							Options option = getOption(stateProviderLu.getProviderKey().toString(),
									stateProviderLu.getFacilityName());
							if (dependentHtmlOptionsMap.containsKey(stateProviderLu.getStatesLu().getStateCode())) {
								dependentHtmlOptionsMap.get(stateProviderLu.getStatesLu().getStateCode()).add(option);
							} else {

								List<Options> arrayList = new ArrayList<Options>();
								Options selectHospitalOpation = getOption("", "Select a Hospital");
								arrayList.add(selectHospitalOpation);
								arrayList.add(option);
								dependentHtmlOptionsMap.put(stateProviderLu.getStatesLu().getStateCode(), arrayList);
							}

						}
						answer.setDependentHtmlOptionsMap(dependentHtmlOptionsMap);
						if (selectedStateCode != null) {
							answer.setHtmlOptions(dependentHtmlOptionsMap.get(selectedStateCode));
						}
					}

				}
			}
		}
		for (Integer id : ServiceConstants.INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST) {
			renderNoSecondObesrvations(surverQuestionsList, valuesMap.get(id), id);
		}

	}

	private Options getOption(String key, String value) {
		Options option = new Options();
		option.setKey(key);
		option.setValue(value);
		return option;
	}

	private void renderNoSecondObesrvations(List<Section> surverQuestionsList, String value, Integer id) {
		if (value == null || CollectionUtils.isEmpty(surverQuestionsList) || id == null)
			return;
		List<Integer> dependentList = ServiceConstants.infectionControlDepenentComponents.get(id);
		for (Section section : surverQuestionsList) {
			for (Question question : section.getSurveyQuestionAnswerList()) {
				if (CollectionUtils.isEmpty(dependentList))
					continue;
				if (dependentList.contains(question.getQuestionId())) {

					if (ServiceConstants.INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.contains(value)) {
						question.setRenderQuestion(false);
					}
					if (ServiceConstants.INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST
							.contains(value)) {
						question.setRenderQuestion(true);
						// question.setRenderAddObservation(true);
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
		surverQuestionsList.add(section);

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
