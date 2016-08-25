package com.hendall.surveyrest.assemblers;

import java.util.Map;

import com.hendall.surveyrest.datamodel.ViewMySurveys;
import com.hendall.surveyrest.entities.UserSurveyAccess;

public class ViewMySurveysAssembler {

	public static void assembleViewMyServeyVo(ViewMySurveys viewMySurveys, UserSurveyAccess userSurveyAccess,
			Map<String, String> providersMap, Map<Integer, String> surveyProviderMap) {
		if (viewMySurveys == null || userSurveyAccess == null) {
			return;
		}
		viewMySurveys.setSurveyKey(userSurveyAccess.getSurvey().getSurveyKey().toString());
		viewMySurveys.setSurveyNumber(userSurveyAccess.getSurvey().getSurveyKey().toString());
		String providerName = providersMap.get(surveyProviderMap.get(userSurveyAccess.getSurvey().getSurveyKey()));
		viewMySurveys.setSurveyProvider(providerName);
		viewMySurveys.setSurveyType(userSurveyAccess.getSurvey().getSurveyTypeLu().getSurveyName());
		viewMySurveys.setStartDate(userSurveyAccess.getSurvey().getStartDate());
		viewMySurveys.setActionDate(userSurveyAccess.getModifyDate());
		viewMySurveys.setStatus(userSurveyAccess.getStatus());
	}

}
