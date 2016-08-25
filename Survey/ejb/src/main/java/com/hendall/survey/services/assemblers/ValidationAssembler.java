package com.hendall.survey.services.assemblers;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.datamodel.Validation;

public class ValidationAssembler {

	public static void populateValidationObject(Answer answer, Validation validation, Map<Integer, Answer> answersMap) {

	if (ServiceConstants.singleValueCompoents.contains(answer.getHtmlControlType())&&StringUtils.isEmpty(answer.getAnswer())) {
validation.setMessage(ServiceConstants.REQUEIRED_MESSAGE +" "+answer.getHtmlControlText()+" "+answer.getFieldType()+ "  "+answer.getHtmlControlId());
	}

	}

}
