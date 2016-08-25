package com.hendall.survey.services.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hendall.survey.services.assemblers.AnswersAssembler;
import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.datamodel.Question;
import com.hendall.survey.services.datamodel.Section;
import com.hendall.survey.services.datamodel.SectionHelpWrapper;
import com.hendall.survey.services.entities.Answers;
import com.hendall.survey.services.entities.AnswersId;
import com.hendall.survey.services.entities.StatesLu;
import com.hendall.survey.services.entities.Survey;
import com.hendall.survey.services.entities.SurveyTypeLu;
import com.hendall.survey.services.entities.UserSurveyAccess;
import com.hendall.survey.services.entities.Users;

/**
 * Session Bean implementation class AnswersService
 */
@Stateless
@LocalBean
public class QuestionAnswerService extends BaseService {
	@EJB
	private QuestionsService questionsService;

	public QuestionAnswerService() {
		// TODO Auto-generated constructor stub
	}

	public List<StatesLu> getStates() {
		try {
			return getEntityManager().createQuery("Select A from StatesLu A", StatesLu.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public SectionHelpWrapper getQuestionAnswers(Integer userKey, Integer surveyKey) {

		SectionHelpWrapper sectionHelpWrapper = questionsService.getQuestions();
		// Replace with generic class when new surveys are added.
		InfectionControlSpecialCases infectionControlSpecialCases = new InfectionControlSpecialCases(getEntityManager());
		infectionControlSpecialCases.addAddtionalQuestions(sectionHelpWrapper.getSections());
		if (!CollectionUtils.isNotEmpty(sectionHelpWrapper.getSections()))
			return sectionHelpWrapper;
		Query query = getEntityManager().createQuery(
				"Select a from Answers a join fetch a.userSurveyAccess usa join fetch usa.survey s where s.surveyKey=:surveyKey",
				Answers.class);
		query.setParameter("surveyKey", surveyKey);
		List<Answers> answersList = query.getResultList();
		Map<Integer, Answers> answersEntityMap = new HashMap<Integer, Answers>();
		populateAnswersEntiyMap(answersEntityMap, answersList);
		for (Section section : sectionHelpWrapper.getSections()) {
			section.setSurveyKey(surveyKey);
			section.setUserKey(userKey);
			if (CollectionUtils.isNotEmpty(answersList)) {
				section.setUserSurveyAccessKey(answersList.get(0).getId().getUserSurverAccessAnswersKey());
			}
			for (Question question : section.getSurveyQuestionAnswerList()) {
				for (Answer answer : question.getAnswersList()) {

					List<Answers> tmpList = new ArrayList<Answers>();
					populateChildEntitiesList(answer, tmpList, answersEntityMap);
					AnswersAssembler.assembleVo(answer, answersEntityMap.get(answer.getHtmlControlId()),
							answersEntityMap.get(answer.getParentId()), tmpList);
				}
				question.populateObservationNumbers();
			}
		}
		infectionControlSpecialCases.processSpecialCases(sectionHelpWrapper.getSections());
		return sectionHelpWrapper;
	}
	
	
	private void populateChildEntitiesList(Answer answer, List<Answers> tmpList,
			Map<Integer, Answers> answersEntityMap) {
		for (Answer answersVo : answer.getChildIdList()) {
			if (answersEntityMap.get(answersVo.getHtmlControlId()) != null)
				tmpList.add(answersEntityMap.get(answersVo.getHtmlControlId()));
		}

	}

	private void populateAnswersEntiyMap(Map<Integer, Answers> answersEntityMap, List<Answers> answersList) {
		if (!CollectionUtils.isNotEmpty(answersList))
			return;
		for (Answers answer : answersList) {
			answersEntityMap.put(answer.getId().getHtmlControlId(), answer);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public SectionHelpWrapper saveAnswers(List<Section> surverQuestionsList) {
		if (!CollectionUtils.isNotEmpty(surverQuestionsList))
			return null;
		Survey survey = null;
		Integer userKey = surverQuestionsList.get(0).getUserKey();
		Integer surveyKey = surverQuestionsList.get(0).getSurveyKey();
		Integer userSurveyKey = surverQuestionsList.get(0).getUserSurveyAccessKey();
		if (surveyKey == -1) { // New Survey
			SurveyTypeLu surveyTypeLu = new SurveyTypeLu();
			surveyTypeLu.setSurveyTypeKey(1);
			survey = new Survey();
			survey.setSurveyTypeLu(surveyTypeLu);
			survey.setStartDate(new Date());
			survey = getEntityManager().merge(survey);
			Users user = new Users();
			user.setUserKey(userKey);
			UserSurveyAccess userSurveyAccess = new UserSurveyAccess();
			userSurveyAccess.setSurvey(survey);
			userSurveyAccess.setUsers(user);
			userSurveyAccess.setStatus(ServiceConstants.STATUS_IN_PROGRESS);
			userSurveyAccess = getEntityManager().merge(userSurveyAccess);
			surveyKey = survey.getSurveyKey();
			userSurveyKey=userSurveyAccess.getUserSurveyKey();
		}

		getEntityManager().flush();

		List<Answers> entitiesList = new ArrayList<Answers>();
		for (Section surveyQuestionSection : surverQuestionsList) {
			for (Question surveyQuestionAnswer : surveyQuestionSection.getSurveyQuestionAnswerList()) {
				for (Answer answer : surveyQuestionAnswer.getAnswersList()) {
					Answers entity = new Answers();
					AnswersId answersId = new AnswersId();
					answersId.setUserSurverAccessAnswersKey(userSurveyKey);
					entity.setId(answersId);

					if (!answer.isDefaultVisible()) {
						answer.setAnswer(null);
						answer.getAnswersList().clear();
					}
					AnswersAssembler.assembleEntity(answer, entity);

					if (entity != null) {
						entitiesList.add(entity);
					}

				}
			}
		}

		if (CollectionUtils.isNotEmpty(entitiesList)) {
			for (Answers entity : entitiesList) {
				if (!StringUtils.isBlank(entity.getAnswer())) {
					getEntityManager().merge(entity);
					getEntityManager().flush();
				} else {
					getEntityManager()
							.remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
					getEntityManager().flush();
				}

			}
		}
		return getQuestionAnswers(userKey, surveyKey);
	}

}
