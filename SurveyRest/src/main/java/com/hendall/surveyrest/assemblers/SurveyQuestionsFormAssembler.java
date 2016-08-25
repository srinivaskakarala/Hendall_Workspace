package com.hendall.surveyrest.assemblers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hendall.survey.schematopojo.ClassName;
import com.hendall.survey.schematopojo.Help;
import com.hendall.survey.schematopojo.HtmlControl;
import com.hendall.surveyrest.common.ServiceConstants;
import com.hendall.surveyrest.datamodel.Answer;
import com.hendall.surveyrest.datamodel.Options;
import com.hendall.surveyrest.datamodel.Question;
import com.hendall.surveyrest.datamodel.Section;
import com.hendall.surveyrest.datamodel.SectionHelpWrapper;

public class SurveyQuestionsFormAssembler {

	public static void assebleViewObject(ClassName suvey, SectionHelpWrapper sectionHelpWrapper) {
		populateSections(suvey, sectionHelpWrapper.getSections());
		//populateHelpSections(suvey, sectionHelpWrapper.getHelp());

	}

	public static void populateSections(ClassName suvey, List<Section> sectionsList) {

		Map<Integer, List<Answer>> childMap = new HashMap<Integer, List<Answer>>();
		if (CollectionUtils.isNotEmpty(suvey.getSection())) {
			for (com.hendall.survey.schematopojo.Section jsonSection : suvey.getSection()) {
				Section section = new Section();
				section.setInstructions(suvey.getInstructions());
				section.setSurveyName(suvey.getTitle());
				section.setSectionTitle(jsonSection.getTitle());
				section.setVersion(suvey.getVersion());
				if (CollectionUtils.isNotEmpty(jsonSection.getQuestion())) {
					for (com.hendall.survey.schematopojo.Question jsonQuestion : jsonSection.getQuestion()) {
						Question question = new Question();
						question.setQuestionId(jsonQuestion.getQuestionID());
						question.setRenderAddObservation(false);
						question.setQuestionText(jsonQuestion.getQuestionText());
						question.setCitableTagName(jsonQuestion.getCitableTagName());
						if (StringUtils.isNotBlank(jsonQuestion.getCitableTagURL())) {
							question.setCitableTagURL(
									"/" + ServiceConstants.HELP_PDF_FOLDER + "/" + jsonQuestion.getCitableTagURL());
						} else {
							question.setCitableTagURL(null);
						}
						Map<String, Answer> tmpMapForOptions = new HashMap<String, Answer>();
						for (HtmlControl htmlControl : jsonQuestion.getHtmlControl()) {
							if (htmlControl.getFieldDefaultVisibility() != null
									&& !htmlControl.getFieldDefaultVisibility()) {
								question.setRenderAddObservation(true);
							}

							Answer answer = getNewAnswerObejct(htmlControl);
							if (ServiceConstants.HTML_CONTROL_TEXT_BOX.equalsIgnoreCase(htmlControl.getControlType())
									|| ServiceConstants.HTML_CONTROL_DATE.equalsIgnoreCase(htmlControl.getControlType())
									|| ServiceConstants.HTML_CONTROL_NUMBER
											.equalsIgnoreCase(htmlControl.getControlType())
									|| ServiceConstants.HTML_CONTROL_TEXT_AREA
											.equalsIgnoreCase(htmlControl.getControlType())) {

								answer.setHtmlControlId(htmlControl.getControlID());
								answer.setHtmlControlText(htmlControl.getControlText());
								answer.setPlaceHolderText(htmlControl.getPlaceholder());
								question.getAnswersList().add(answer);

							}

							if (ServiceConstants.HTML_CONTROL_RADIO.equalsIgnoreCase(htmlControl.getControlType())
									|| ServiceConstants.HTML_CONTROL_CHECK_BOX
											.equalsIgnoreCase(htmlControl.getControlType())
									|| ServiceConstants.HTML_CONTROL_SELECT
											.equalsIgnoreCase(htmlControl.getControlType())) {

								Options options = new Options();
								options.setValue(htmlControl.getControlText());
								options.setKey(htmlControl.getControlText());

								if (tmpMapForOptions.keySet().contains(htmlControl.getControlID().toString())) {
									tmpMapForOptions.get(htmlControl.getControlID().toString()).getHtmlOptions()
											.add(options);

								} else {
									answer.getHtmlOptions().add(options);
									tmpMapForOptions.put(htmlControl.getControlID().toString(), answer);
									question.getAnswersList().add(answer);
								}
							}
							populateChildMap(childMap, answer);
						}
						// populateAnswerListFromMap(surveyQuestionAnswer.getAnswersList(),
						// tmpMapForOptions);
						// Collections.sort(surveyQuestionAnswer.getAnswersList(),
						// Comparators.COMPONENTS_ORDER);
						section.getSurveyQuestionAnswerList().add(question);

					}
				}
				sectionsList.add(section);
			}
		}
		populateChildIdFromMap(childMap, sectionsList);

	}

	public static void populateHelpSections(ClassName suvey, List<Help> helpList) {
		if (suvey == null || CollectionUtils.isEmpty(suvey.getSection()))
			return;
		for (Help help : suvey.getHelp()) {
			helpList.add(help);
		}
	}

	private static void populateChildIdFromMap(Map<Integer, List<Answer>> childMap,
			List<Section> surveyQuestionSectionList) {
		for (Section surveyQuestionSection : surveyQuestionSectionList) {
			for (Question surveyQuestionAnswer : surveyQuestionSection.getSurveyQuestionAnswerList()) {
				for (Answer answer : surveyQuestionAnswer.getAnswersList()) {
					if (childMap.get(answer.getHtmlControlId()) != null) {
						answer.getChildIdList().addAll(childMap.get(answer.getHtmlControlId()));
					}
				}
			}
		}

	}

	private static void populateChildMap(Map<Integer, List<Answer>> childMap, Answer answerVo) {
		if (answerVo.getParentId() != 0) {
			if (childMap.containsKey(answerVo.getParentId())) {
				childMap.get(answerVo.getParentId()).add(answerVo);
			} else {
				List<Answer> list = new ArrayList<Answer>();
				list.add(answerVo);
				childMap.put(answerVo.getParentId(), list);
			}
		}
	}

	private static Answer getNewAnswerObejct(HtmlControl htmlControl) {
		Answer answer = new Answer();
		answer.setHtmlControlType(htmlControl.getControlType());
		answer.setHtmlControlId(htmlControl.getControlID());
		if (htmlControl.getParentControlID() != null)
			answer.setParentId(htmlControl.getParentControlID());
		answer.setDefaultVisible(
				htmlControl.getFieldDefaultVisibility() == null ? true : htmlControl.getFieldDefaultVisibility());
		if (htmlControl.getFieldRequired() == null || !htmlControl.getFieldRequired()) {
			answer.setRequired(false);
		} else {
			answer.setRequired(true);
		}
		return answer;
	}

	private static void populateAnswerListFromMap(List<Answer> answersList, Map<String, Answer> answersMap) {
		if (answersMap == null || answersMap.keySet().size() == 0)
			return;
		if (answersList == null) {
			answersList = new ArrayList<Answer>();
		}
		for (Answer answer : answersMap.values()) {
			answersList.add(answer);
		}
	}

}
