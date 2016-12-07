package com.hendall.surveyrest.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hendall.surveyrest.assemblers.AnswersAssembler;
import com.hendall.surveyrest.assemblers.ProvidersAssembler;
import com.hendall.surveyrest.assemblers.StatesAssembler;
import com.hendall.surveyrest.common.ServiceConstants;
import com.hendall.surveyrest.datamodel.Answer;
import com.hendall.surveyrest.datamodel.DifferentUserAnswer;
import com.hendall.surveyrest.datamodel.Question;
import com.hendall.surveyrest.datamodel.Section;
import com.hendall.surveyrest.datamodel.SectionHelpWrapper;
import com.hendall.surveyrest.dto.ProvidersDTO;
import com.hendall.surveyrest.dto.StatesDTO;
import com.hendall.surveyrest.entities.Answers;
import com.hendall.surveyrest.entities.AnswersId;
import com.hendall.surveyrest.entities.ProvidersLu;
import com.hendall.surveyrest.entities.StatesLu;
import com.hendall.surveyrest.entities.Survey;
import com.hendall.surveyrest.entities.SurveyTypeLu;
import com.hendall.surveyrest.entities.UserSurveyAccess;
import com.hendall.surveyrest.entities.Users;
import com.hendall.surveyrest.jpa.JpaUtil;

/**
 * Session Bean implementation class AnswersService
 */
public class QuestionAnswerHelper {

	private QuestionsHelper questionsHelper;

	public QuestionAnswerHelper() {
		questionsHelper = new QuestionsHelper();
	}

	public List<StatesLu> getStates() {
		try {
			return JpaUtil.getEntityManager().createQuery("Select A from StatesLu A", StatesLu.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<StatesDTO> getStatesDTO() {
		StatesAssembler statesAssembler = new StatesAssembler();
		try {
			List<StatesLu> statesentitylist = getStates();
			return statesAssembler.GetStatesDTOList(statesentitylist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JpaUtil.closeEntityManager();
		}

		return null;
	}

	public List<ProvidersLu> getProviders(String state) {
		try {
			Query query = null;
			if ("ALL".equals(state)) {
				query = JpaUtil.getEntityManager()
						.createQuery("Select P from ProvidersLu P", ProvidersLu.class);
			} else {
				query = JpaUtil.getEntityManager()
						.createQuery("Select P from ProvidersLu P where P.statesLu.stateCode=:state", ProvidersLu.class);
				query.setParameter("state", state);
			}			
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<ProvidersDTO> getProvidersDTO(String state) {
		ProvidersAssembler providersAssembler = new ProvidersAssembler();
		try {
			List<ProvidersLu> providesEntityList = getProviders(state);
			return providersAssembler.getProvidersDTOList(providesEntityList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JpaUtil.closeEntityManager();
		}

		return null;
	}

	public SectionHelpWrapper getQuestionAnswers(Integer userKey, Integer surveyKey) {

		SectionHelpWrapper sectionHelpWrapper = questionsHelper.getQuestions();
		
		try {
			// Replace with generic class when new surveys are added.
			InfectionControlSpecialCases infectionControlSpecialCases = new InfectionControlSpecialCases();
			//infectionControlSpecialCases.addAddtionalQuestions(sectionHelpWrapper.getSections());
			if (!CollectionUtils.isNotEmpty(sectionHelpWrapper.getSections()))
				return sectionHelpWrapper;
			Query query = JpaUtil.getEntityManager().createQuery(
					"Select a from Answers a join fetch a.userSurveyAccess usa join fetch usa.users us join fetch usa.survey s where s.surveyKey=:surveyKey",
					Answers.class);
			query.setParameter("surveyKey", surveyKey);
			List<Answers> answersList = query.getResultList();
			Map<Integer, Answers> answersEntityMap = new HashMap<Integer, Answers>();
			Map<Integer, Map<Integer, Answers>> otherUsersAnswersEntityMap = new HashMap<Integer, Map<Integer, Answers>>();
			Query UserSurveyAccessQuery = JpaUtil.getEntityManager().createQuery(
					"Select Usa from UserSurveyAccess Usa join fetch Usa.survey s join fetch Usa.users u where s.surveyKey=:surveyKey and u.userKey=:userKey",
					UserSurveyAccess.class);			
			UserSurveyAccessQuery.setParameter("surveyKey", surveyKey);
			UserSurveyAccessQuery.setParameter("userKey", userKey);
			List<UserSurveyAccess> userSurveyAccessList = UserSurveyAccessQuery.getResultList();
			Integer userSurveyAccessKey = null;
			if (CollectionUtils.isNotEmpty(userSurveyAccessList)) {
				userSurveyAccessKey = userSurveyAccessList.get(0).getUserSurveyKey();
			}
			populateAnswersEntiyMap(answersEntityMap, answersList, otherUsersAnswersEntityMap, userKey);
			String approverEmail = null;
			for (Section section : sectionHelpWrapper.getSections()) {
				section.setSurveyKey(surveyKey);
				section.setUserKey(userKey);
				section.setUserSurveyAccessKey(userSurveyAccessKey);
				for (Question question : section.getSurveyQuestionAnswerList()) {
					for (Answer answer : question.getAnswersList()) {
						List<Answers> tmpList = new ArrayList<Answers>();
						populateChildEntitiesList(answer, tmpList, answersEntityMap);
						AnswersAssembler.assembleVo(answer, answersEntityMap.get(answer.getHtmlControlId()),
								answersEntityMap.get(answer.getParentId()), tmpList);				
						List<DifferentUserAnswer> differentUserAnswerList = new ArrayList<DifferentUserAnswer>();
						answer.setDifferentUserAnswerList(differentUserAnswerList);
						for (Map.Entry<Integer, Map<Integer, Answers>> entry : otherUsersAnswersEntityMap.entrySet()) {
						    Integer otherUserKey = entry.getKey();
						    Map<Integer, Answers> otherUserAnswerMap = entry.getValue();
						    if (otherUserAnswerMap.get(answer.getHtmlControlId()) != null) {
						    	DifferentUserAnswer differentUserAnswer = new DifferentUserAnswer();
							    differentUserAnswer.setUser(otherUserKey);						    
							    differentUserAnswer.setAnswer(otherUserAnswerMap.get(answer.getHtmlControlId()).getAnswer());
							    String answerDate = null;
							    if (otherUserAnswerMap.get(answer.getHtmlControlId()).getModifyDate() != null) {
							    	answerDate = otherUserAnswerMap.get(answer.getHtmlControlId()).getModifyDate().toString();
							    }
							    differentUserAnswer.setAnswerDate(answerDate);
							    differentUserAnswerList.add(differentUserAnswer);
						    }
						    
						}
					}
					question.populateObservationNumbers();
				}
			}
			infectionControlSpecialCases.processSpecialCases(sectionHelpWrapper.getSections());
			populateFiles(sectionHelpWrapper.getSections(), surveyKey);
		} finally {
			JpaUtil.closeEntityManager();
		}
		
		return sectionHelpWrapper;
	}

	/**** Not used ***/
	private boolean popluateRenderComments(Integer surveyKey, Section sectionHelpWrapper) {
		Query query = JpaUtil.getEntityManager()
				.createQuery("Select U From UserSurveyAccess U where U.userSurveyKey=:survekyKey and U.status=:status");
		query.setParameter("survekyKey", surveyKey);
		query.setParameter("status", ServiceConstants.STATUS_IN_SUBMITTED);
		int count = query.getMaxResults();
		if (count > 0) {
			return true;
		}
		return false;
	}

	public void populateFiles(List<Section> surverQuestionsList, Integer surveyKey) {
		/*
		 * if (surveyKey != null) { List<File> fileNames =
		 * getFileNames(surveyKey);
		 * 
		 * for (Section section : surverQuestionsList) { for (Question question
		 * : section.getSurveyQuestionAnswerList()) {
		 * if(question.getQuestionId()==ServiceConstants.
		 * INFECTION_CONTROL_FILE_UPLOAD_ID){ question.setFileNames(fileNames);
		 * } } } }
		 */
	}

	private void populateChildEntitiesList(Answer answer, List<Answers> tmpList,
			Map<Integer, Answers> answersEntityMap) {
		for (Answer answersVo : answer.getChildIdList()) {
			if (answersEntityMap.get(answersVo.getHtmlControlId()) != null)
				tmpList.add(answersEntityMap.get(answersVo.getHtmlControlId()));
		}

	}

	private void populateAnswersEntiyMap(Map<Integer, Answers> answersEntityMap, List<Answers> answersList, Map<Integer, Map<Integer, Answers>> otherUsersAnswersEntityMap, Integer userKey) {
		if (!CollectionUtils.isNotEmpty(answersList) || userKey == null)
			return;
		for (Answers answer : answersList) {
			if (answer != null && answer.getUserSurveyAccess() != null && answer.getUserSurveyAccess().getUsers() != null && answer.getUserSurveyAccess().getUsers().getUserKey() != null ) {
				Integer ansUserKey = answer.getUserSurveyAccess().getUsers().getUserKey();
				if (ansUserKey.equals(userKey)) {
					answersEntityMap.put(answer.getId().getHtmlControlId(), answer);
				} else if(otherUsersAnswersEntityMap.containsKey(ansUserKey)) {
					Map<Integer, Answers> otherUserMap = otherUsersAnswersEntityMap.get(ansUserKey);
					otherUserMap.put(answer.getId().getHtmlControlId(), answer);
				} else {
					Map<Integer, Answers> otherUserNewMap = new HashMap<Integer, Answers>();
					otherUserNewMap.put(answer.getId().getHtmlControlId(), answer);
					otherUsersAnswersEntityMap.put(ansUserKey, otherUserNewMap);
				}
			}
		}
	}

	public SectionHelpWrapper saveAnswers(List<Section> surverQuestionsList) {
		
		if (!CollectionUtils.isNotEmpty(surverQuestionsList))
			return null;

		Integer userKey = surverQuestionsList.get(0).getUserKey();
		Integer surveyKey = surverQuestionsList.get(0).getSurveyKey();
		Integer userSurveyKey = surverQuestionsList.get(0).getUserSurveyAccessKey();
		
		try {
			JpaUtil.getEntityManager().getTransaction().begin();

			if (surveyKey < 0) { // New Survey

				SurveyTypeLu surveyTypeLu = new SurveyTypeLu();
				surveyTypeLu.setSurveyTypeKey(1);
				Survey survey = new Survey();
				survey.setSurveyTypeLu(surveyTypeLu);
				survey.setStartDate(new Date());
				survey.setCreateUser(userKey);
			   JpaUtil.getEntityManager().persist(survey);
				
				
				Users user = new Users();
				user.setUserKey(userKey);
				UserSurveyAccess userSurveyAccess = new UserSurveyAccess();
				userSurveyAccess.setSurvey(survey);
				userSurveyAccess.setUsers(user);
				userSurveyAccess.setStatus(ServiceConstants.STATUS_IN_PROGRESS);
				JpaUtil.getEntityManager().persist (userSurveyAccess);
				
				surveyKey = survey.getSurveyKey();
				userSurveyKey = userSurveyAccess.getUserSurveyKey();
			} else {
				Query query = JpaUtil.getEntityManager().createQuery(
						"Select U From UserSurveyAccess U inner join fetch U.survey s where s.surveyKey=:surveKey and U.userSurveyKey=:userSurveyAccessKey");
				query.setParameter("surveKey", surveyKey);
				query.setParameter("userSurveyAccessKey", userSurveyKey);
				List<UserSurveyAccess> list = (List<UserSurveyAccess>) query.getResultList();
				UserSurveyAccess userSurveyAccess = list.get(0);
				userSurveyAccess.setStatus(ServiceConstants.STATUS_IN_PROGRESS);
				userSurveyAccess.setModifyDate(new Date());
				JpaUtil.getEntityManager().merge(userSurveyAccess);
				Survey survey = userSurveyAccess.getSurvey();
				survey.setStartDate(new Date());
				JpaUtil.getEntityManager().merge(survey);
			}

			// JpaUtil.getEntityManager().flush();

			List<Answers> entitiesList = new ArrayList<Answers>();
			for (Section surveyQuestionSection : surverQuestionsList) {
				for (Question surveyQuestionAnswer : surveyQuestionSection.getSurveyQuestionAnswerList()) {
					for (Answer answer : surveyQuestionAnswer.getAnswersList()) {
						if (!(answer.getAnswer()==null && answer.getAnswerDate()==null && answer.getAnswersList().isEmpty())) {
							if (answer.getHtmlControlId() == ServiceConstants.INFECTION_CONTROL_SUPERVISOR_COMMENTS_ID)
								continue;
							Answers entity = new Answers();
							AnswersId answersId = new AnswersId();
							answersId.setUserSurverAccessAnswersKey(userSurveyKey);
							entity.setId(answersId);
							entity.setCreateUser(userKey);
							entity.setModifyUser(userKey);
	
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
			}

			if (CollectionUtils.isNotEmpty(entitiesList)) {
				for (Answers entity : entitiesList) {
					if (!StringUtils.isBlank(entity.getAnswer())) {
						JpaUtil.getEntityManager().merge(entity);
						JpaUtil.getEntityManager().flush();
					} else {
						JpaUtil.getEntityManager().remove(JpaUtil.getEntityManager().contains(entity) ? entity
								: JpaUtil.getEntityManager().merge(entity));
						JpaUtil.getEntityManager().flush();
					}

				}
			}
			JpaUtil.getEntityManager().getTransaction().commit();			
		} catch (Exception e) {

			e.printStackTrace();

			JpaUtil.getEntityManager().getTransaction().rollback();
		} finally {
			JpaUtil.closeEntityManager();
		}
	
		return getQuestionAnswers(userKey, surveyKey);
	}

}
