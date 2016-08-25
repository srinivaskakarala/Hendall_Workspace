package com.hendall.survey.ui.backingbeans;

import java.util.List;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.datamodel.Options;
import com.hendall.survey.services.datamodel.Question;
import com.hendall.survey.services.datamodel.Section;
import org.apache.commons.collections4.CollectionUtils;

public class InfectionControlSpecialCasesBean {

	public void processDropDowns(String id, String value, List<Section> masterSurveyQuestionSectionList) {
		populateHospitalNames(id, value, masterSurveyQuestionSectionList);
	}

	public void radioChangeAction() {

	}

	private void populateHospitalNames(String id, String value, List<Section> masterSurveyQuestionSectionList) {
		for (Section section : masterSurveyQuestionSectionList) {
			for (Question question : section.getSurveyQuestionAnswerList()) {
				for (Answer answer : question.getAnswersList()) {
					if (Integer.valueOf(id) == ServiceConstants.INFECTION_CONTROL_STATE_CONTROL_ID && answer
							.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_HOSPITAL_NAME_CONTROL_ID) {
						List<Options> optionsList = answer.getDependentHtmlOptionsMap().get(value);
						answer.setHtmlOptions(optionsList);
						for (Question subQuestion : section.getSurveyQuestionAnswerList()) {
							for (Answer subAnswer : subQuestion.getAnswersList()) {
								if (subAnswer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_HOSPITAL_CCN_ID)
									subAnswer.setAnswer("");
							}
						}

					}
					if (Integer.valueOf(id) == ServiceConstants.INFECTION_CONTROL_HOSPITAL_NAME_CONTROL_ID.intValue()
							&& answer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_HOSPITAL_CCN_ID) {
						List<Options> optionsList = answer.getDependentHtmlOptionsMap().get(value);
						if (CollectionUtils.isNotEmpty(optionsList))
							answer.setAnswer(optionsList.get(0).getValue());
					}
				}
			}
		}

	}
}
