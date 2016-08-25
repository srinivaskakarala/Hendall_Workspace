package com.hendall.survey.services.assemblers;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.entities.Answers;
import com.hendall.survey.util.ServiceUtils;

public class AnswersAssembler {

	public static void assembleEntity(Answer vo, Answers entity) {
		if (vo == null || entity == null) {
			return;
		}
		// UserSurveyAccess userSurveyAccess = new UserSurveyAccess();
		// userSurveyAccess.setUserSurveyProviderKey(1);
		// entity.setUserSurveyAccess(userSurveyAccess);
		if (ServiceConstants.singleValueCompoents.contains(vo.getHtmlControlType())) {
			if (vo.getHtmlControlType().equals(ServiceConstants.HTML_CONTROL_DATE) && vo.getAnswerDate() != null) {
				entity.setAnswer(
						ServiceUtils.converDateToString(vo.getAnswerDate(), ServiceUtils.DATE_FORMAT_MM_DD_YYYY));
			} else {
				entity.setAnswer(vo.getAnswer());
			}
		} else {
			StringBuilder tmpValues = new StringBuilder();
			for (String value : vo.getAnswersList()) {
				tmpValues.append(value).append(ServiceConstants.DATA_SAPERATOR);
			}
			if (!StringUtils.isBlank(tmpValues)) {
				entity.setAnswer(StringUtils.removeEnd(tmpValues.toString(), ServiceConstants.DATA_SAPERATOR));
			} else {
				entity.setAnswer(null);
			}
		}

		entity.getId().setHtmlControlId(vo.getHtmlControlId());
		entity.setCreateDate(new Date());
		entity.setCreateUser(10);
		// answersId.setUserKey(10);
		// entity.setId(answersId);

	}

	public static void assembleVo(Answer vo, Answers entity, Answers parentEntity, List<Answers> childEntitesList) {
		if (vo == null) {
			return;
		}
		if (ServiceConstants.singleValueCompoents.contains(vo.getHtmlControlType())) {
			if (entity != null) {

				if (vo.getHtmlControlType().equals(ServiceConstants.HTML_CONTROL_DATE)) {
					vo.setAnswerDate(
							ServiceUtils.converStringToDate(entity.getAnswer(), ServiceUtils.DATE_FORMAT_MM_DD_YYYY));
				} else {
					vo.setAnswer(entity.getAnswer());
				}
			}
			if(vo.isDefaultVisible()){
				vo.setRenderRemoveButton(true);
				vo.setDefaultVisible(true);
			}else{
				
				if (entity != null||parentEntity != null || !CollectionUtils.isEmpty(childEntitesList)) {
					vo.setRenderRemoveButton(true);
					vo.setDefaultVisible(true);
				} 
			}
		} else {
			if (entity != null) {
				String[] tmpValues = entity.getAnswer().split(ServiceConstants.DATA_SAPERATOR);
				for (String value : tmpValues) {
					vo.getAnswersList().add(value);
				}
			}
		}

	}

}
