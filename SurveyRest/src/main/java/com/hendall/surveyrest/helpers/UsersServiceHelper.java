package com.hendall.surveyrest.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hendall.surveyrest.entities.AnswersId;
import com.hendall.surveyrest.assemblers.UsersAssembler;
import com.hendall.surveyrest.assemblers.ViewMySurveysAssembler;
import com.hendall.surveyrest.common.ServiceConstants;
import com.hendall.surveyrest.datamodel.SurveyersModel;
import com.hendall.surveyrest.datamodel.UserSession;
import com.hendall.surveyrest.datamodel.ViewMySurveys;
import com.hendall.surveyrest.entities.Answers;
import com.hendall.surveyrest.entities.ProvidersLu;
import com.hendall.surveyrest.entities.Survey;
import com.hendall.surveyrest.entities.UserSurveyAccess;
import com.hendall.surveyrest.entities.Users;
import com.hendall.surveyrest.jpa.JpaUtil;

public class UsersServiceHelper {

	/**
	 * Default constructor.
	 */
	public UsersServiceHelper() {
		// TODO Auto-generated constructor stub
	}

	public UserSession getUsersSurveys(String userName, String password) {
		try {
			Query query = JpaUtil.getEntityManager()
					.createQuery(" Select A from Users A where A.userId=:userId and A.password=:password", Users.class);
			query.setParameter("userId", userName);
			query.setParameter("password", password);
			List<Users> usersList = query.getResultList();
			UserSession userSession = new UserSession();
			if (CollectionUtils.isNotEmpty(usersList)) {
				UsersAssembler.assembleUserSession(usersList.get(0), userSession);
				return userSession;
			}
		} finally {
			JpaUtil.closeEntityManager();
		}
		
		return null;
	}
	
	public List<UserSession> getUsers(String state) {
		try {
			Query query = null;
			if ("ALL".equals(state)) {
				query = JpaUtil.getEntityManager()
						.createQuery(" Select A from Users A", Users.class);
			} else {
				query = JpaUtil.getEntityManager()
						.createQuery(" Select A from Users A where A.state=:state", Users.class);
				query.setParameter("state", state);
			}
			List<Users> usersObjectList = query.getResultList();
			List<UserSession> usersList = new ArrayList<UserSession>();			
			if (CollectionUtils.isNotEmpty(usersObjectList)) {				
				for (Users user:usersObjectList) {
					UserSession userSessionAssembled = new UserSession();
					UsersAssembler.assembleUserSession(user, userSessionAssembled);
					usersList.add(userSessionAssembled);
				}
				
				return usersList;
			}
		} finally {
			JpaUtil.closeEntityManager();
		}
		
		return null;
	}

	public Map<String, String> getStateUsers(String state) {
		Map<String, String> stateUsersMap = new HashMap<String, String>();
		Query query = JpaUtil.getEntityManager().createQuery(" Select A from Users A where A.state=:state",
				Users.class);
		query.setParameter("state", state);
		List<Users> usersList = query.getResultList();
		if (CollectionUtils.isNotEmpty(usersList)) {
			for (Users users : usersList) {
				stateUsersMap.put(users.getFirstName() + " " + users.getLastName() + "(" + users.getEmail() + ")",
						users.getEmail());
			}
		}
		return stateUsersMap;
	}

	public void delteSurvey(int surveyKey) {
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			JpaUtil.getEntityManager()
					.createNativeQuery(
							"Delete from answers where User_Surver_Access_Answers_Key in (Select user_survey_key from user_survey_access where survey_key = "
									+ surveyKey + ")")
					.executeUpdate();
			JpaUtil.getEntityManager()
					.createNativeQuery("Delete from user_survey_access where survey_key = " + surveyKey)
					.executeUpdate();
			JpaUtil.getEntityManager().createNativeQuery("Delete from survey where survey_key = " + surveyKey)
					.executeUpdate();
			JpaUtil.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			JpaUtil.getEntityManager().getTransaction().rollback();
		} finally {
			JpaUtil.closeEntityManager();
		}

	}

	public List<ViewMySurveys> getUsersSurveys(int userKey) {
		List<ViewMySurveys> viewMySurveyList = new ArrayList<ViewMySurveys>();

		try {
			Query query = JpaUtil.getEntityManager().createQuery(
					" Select Usa From UserSurveyAccess Usa  " + " join fetch Usa.survey sur "
							+ " join fetch Usa.users u " + " join fetch Usa.survey sur "
							+ " join fetch sur.surveyTypeLu type " + " where u.userKey =:userKey ",
					UserSurveyAccess.class);
			query.setParameter("userKey", userKey);
			List<UserSurveyAccess> userSurveyAccessList = query.getResultList();
			List<Integer> surveyKeyList = new ArrayList<Integer>();
			for (UserSurveyAccess userSurveyAccess : userSurveyAccessList) {
				if (!surveyKeyList.contains(userSurveyAccess.getSurvey().getSurveyKey()))
					surveyKeyList.add(userSurveyAccess.getSurvey().getSurveyKey());
			}

			Map<String, String> providersMap = new HashMap<String, String>();
			populateProvidersMap(providersMap);
			Map<Integer, String> surveyProviderMap = new HashMap<Integer, String>();
			Map<Integer, String> approverCommentsProviderMap = new HashMap<Integer, String>();
			if (CollectionUtils.isNotEmpty(surveyKeyList)) {
				Query providerAnswersQuery = JpaUtil.getEntityManager().createQuery(
						"Select A From Answers A  join fetch A.userSurveyAccess usa join fetch usa.survey s  Where s.surveyKey in (:surveyKeys) and A.id.htmlControlId=200",
						Answers.class);
				providerAnswersQuery.setParameter("surveyKeys", surveyKeyList);
				List<Answers> providersAnswersList = providerAnswersQuery.getResultList();
				populateSurveyMap(surveyProviderMap, providersAnswersList);
				
				Query approverCommentsAnswersQuery = JpaUtil.getEntityManager().createQuery(
						"Select A From Answers A  join fetch A.userSurveyAccess usa join fetch usa.survey s  Where s.surveyKey in (:surveyKeys) and A.id.htmlControlId=-3",
						Answers.class);
				approverCommentsAnswersQuery.setParameter("surveyKeys", surveyKeyList);
				List<Answers> approverCommentsAnswersList = approverCommentsAnswersQuery.getResultList();
				populateSurveyMap(approverCommentsProviderMap, approverCommentsAnswersList);
				
			}
		
			for (UserSurveyAccess answers : userSurveyAccessList) {
				ViewMySurveys viewMySurveys = new ViewMySurveys();
				ViewMySurveysAssembler.assembleViewMyServeyVo(viewMySurveys, answers, providersMap, surveyProviderMap, approverCommentsProviderMap);
				if (answers != null && answers.getSurvey()!= null && answers.getSurvey().getSurveyKey() != null) {
					List<Integer> users = new ArrayList<Integer>();
					Query otherUsersQuery = JpaUtil.getEntityManager().createQuery(
							"Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u where s.surveyKey=:surveyKey and usa.status=:status",
							UserSurveyAccess.class);
					otherUsersQuery.setParameter("surveyKey", answers.getSurvey().getSurveyKey());
					otherUsersQuery.setParameter("status", ServiceConstants.STATUS_IN_PROGRESS);
					List<UserSurveyAccess> otherUserSurveyAccessList = otherUsersQuery.getResultList();
						if (otherUserSurveyAccessList!= null && !otherUserSurveyAccessList.isEmpty()){
							for (UserSurveyAccess userSurveyAccess:otherUserSurveyAccessList){
								users.add(userSurveyAccess.getUsers().getUserKey());
								}
						}
					if (users != null && !users.isEmpty()) {
						viewMySurveys.setOtherSurveyerKeys(users);
					}
				}								
				viewMySurveyList.add(viewMySurveys);
			}
		} finally {
			JpaUtil.closeEntityManager();
		}

		return viewMySurveyList;
	}

	public void populateSurveyMap(Map<Integer, String> map, List<Answers> answersList) {
		for (Answers answers : answersList) {
			if (answers.getAnswer()!= null && answers.getUserSurveyAccess().getSurvey().getSurveyKey() != null) {
				map.put(answers.getUserSurveyAccess().getSurvey().getSurveyKey(), answers.getAnswer());
			}
		}

	}

	public void populateProvidersMap(Map<String, String> map) {
		List<ProvidersLu> providersList = JpaUtil.getEntityManager()
				.createQuery("Select A from ProvidersLu A", ProvidersLu.class).getResultList();
		if (CollectionUtils.isEmpty(providersList))
			return;
		for (ProvidersLu providersLu : providersList) {
			map.put(providersLu.getProviderKey().toString(), providersLu.getFacilityName());
		}
	}

	private void populateProviderName(List<Answers> userSurveyAccessList, Map<String, String> providersMap) {
		if (CollectionUtils.isEmpty(userSurveyAccessList))
			return;
		for (Answers answers : userSurveyAccessList) {
			answers.setAnswer(providersMap.get(answers.getAnswer()));
			JpaUtil.getEntityManager().detach(answers);
			;
		}

	}
	
private void saveComments(String comments, Integer userSruveyKey)
	{
		try {
			AnswersId answersId = new AnswersId();
			answersId.setHtmlControlId(ServiceConstants.INFECTION_CONTROL_SUPERVISOR_COMMENTS_ID.intValue());
			answersId.setUserSurverAccessAnswersKey(userSruveyKey.intValue());
			Answers answer = new Answers();
			answer.setAnswer(comments);
			answer.setId(answersId);
			answer.setCreateDate(new Date());
			answer.setCreateUser(Integer.valueOf(-1));
			if (StringUtils.isBlank(comments)) {
				//change this to check if the record exists before trying to delete.
				JpaUtil.getEntityManager().remove(JpaUtil.getEntityManager().merge(answer));
			} else {
				JpaUtil.getEntityManager().merge(answer);
			}
		} catch (Exception e) {	
			e.printStackTrace();	
			JpaUtil.getEntityManager().getTransaction().rollback();
		}
	}

	public String approveOrRejectSurvey(Integer surveyKey, Integer userKey, String status, String supervisorComments) {
		if (surveyKey == null || userKey == null || status == null)
			return "surveykey, userKey or status missing";
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			Query query = JpaUtil.getEntityManager().createQuery(
					"Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u where s.surveyKey=:surveyKey",
					UserSurveyAccess.class);
			query.setParameter("surveyKey", surveyKey);
			List<UserSurveyAccess> resultList = query.getResultList();
			for (UserSurveyAccess userSurveyAccess : resultList) {
				if ("SAVE".equals(status)) {
					saveComments(supervisorComments, userSurveyAccess.getUserSurveyKey());
				} else {
					if (userKey == userSurveyAccess.getUsers().getUserKey()) {
						if (ServiceConstants.STATUS_REVISION_REQUIRED.equals(status)) {
							userSurveyAccess.setStatus(ServiceConstants.STATUS_REETUREND_FOR_REVISION);
						} else {
							userSurveyAccess.setStatus(status);
						}					
					} else {
						userSurveyAccess.setStatus(status);
					}					
					JpaUtil.getEntityManager().merge(userSurveyAccess);
					saveComments(supervisorComments, userSurveyAccess.getUserSurveyKey());
				}
			}
			return "Success";
		} catch (Exception e) {

			e.printStackTrace();

			JpaUtil.getEntityManager().getTransaction().rollback();
		} finally {
			JpaUtil.closeEntityManager();
		}
		return "failed";
	}

	@Transactional
	public String assingToOtherUser(Integer userKey, Integer surveyKey, String supervisorEmail) {
		if (userKey == null || surveyKey == null || supervisorEmail == null)
			return "error";
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			Query userQuery = JpaUtil.getEntityManager()
					.createQuery(" Select u from Users u Where u.email=:supervisorEmail)", Users.class);
			userQuery.setParameter("supervisorEmail", supervisorEmail);
			List<Users> usresList = userQuery.getResultList();
			if (CollectionUtils.isEmpty(usresList)) {
				return "No user with that email address exists in the sytem";
			} else {
				Users superUser = usresList.get(0);
				Query userAccessQuery = JpaUtil.getEntityManager().createQuery(
						" Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u Where usa.status=:status and s.surveyKey=:surveyKey)",
						UserSurveyAccess.class);
				userAccessQuery.setParameter("status", ServiceConstants.STATUS_IN_PROGRESS);
				userAccessQuery.setParameter("surveyKey", surveyKey);
				List<UserSurveyAccess> userSurveyAccessList = userAccessQuery.getResultList();
				if (CollectionUtils.isEmpty(userSurveyAccessList)) {
					return "";
				} else {
					for (UserSurveyAccess userSurveyAccess: userSurveyAccessList) {
						userSurveyAccess.setStatus(ServiceConstants.STATUS_IN_SUBMITTED);
						JpaUtil.getEntityManager().merge(userSurveyAccess);
					}					

					Query supervisorAccessQuery = JpaUtil.getEntityManager().createQuery(
							" Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u Where u.email=:supervisorEmail and s.surveyKey=:surveyKey)",
							UserSurveyAccess.class);
					supervisorAccessQuery.setParameter("surveyKey", surveyKey);
					supervisorAccessQuery.setParameter("supervisorEmail", supervisorEmail);
					List<UserSurveyAccess> supervisorUserSurveyAccessList = supervisorAccessQuery.getResultList();
					UserSurveyAccess supervisorAccess = null;
					if (CollectionUtils.isNotEmpty(supervisorUserSurveyAccessList)) {
						supervisorAccess = supervisorUserSurveyAccessList.get(0);
					} else {
						supervisorAccess = new UserSurveyAccess();
						;
					}

					Survey survey = new Survey();
					survey.setSurveyKey(surveyKey);
					supervisorAccess.setSurvey(survey);
					supervisorAccess.setUsers(superUser);
					supervisorAccess.setStatus(ServiceConstants.STATUS_IN_PENDING_REVIEW);
					JpaUtil.getEntityManager().merge(supervisorAccess);
					// EmailService emailService = new EmailService();
					// emailService.sendEmail(ServiceConstants.EMAIL_FROM_ADDRESS,
					// supervisorEmail,
					// ServiceConstants.EMAIL_SUBJECT,
					// ServiceConstants.EMAIL_MESSAGE);

				}
			}
			return "Success";
		} catch (Exception e) {

			e.printStackTrace();

			JpaUtil.getEntityManager().getTransaction().rollback();
		} finally {
			JpaUtil.closeEntityManager();
		}
		return "failed";
	}

	public SurveyersModel getUsersForSurvey(Integer surveyKey) {
		SurveyersModel surveyersModel = new SurveyersModel();
		List<Integer> users = new ArrayList<Integer>();
		surveyersModel.setUserKeys(users);
		if (surveyKey == null)
			return surveyersModel;
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			Query query = JpaUtil.getEntityManager().createQuery(
					"Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u where s.surveyKey=:surveyKey and usa.status=:status",
					UserSurveyAccess.class);
			query.setParameter("surveyKey", surveyKey);
			query.setParameter("status", ServiceConstants.STATUS_IN_PROGRESS);
			List<UserSurveyAccess> resultList = query.getResultList();
				if (resultList!= null && !resultList.isEmpty()){
					for (UserSurveyAccess userSurveyAccess:resultList){
						users.add(userSurveyAccess.getUsers().getUserKey());
						}
				}						
			} catch (Exception e) {
	
				e.printStackTrace();
	
				JpaUtil.getEntityManager().getTransaction().rollback();
			} finally {
				JpaUtil.closeEntityManager();
			}	
		surveyersModel.setSurveyKey(surveyKey);
		return surveyersModel;
	}

	public SurveyersModel addUserstoSurvey(SurveyersModel surveyersModel) {
		List<Integer> userkeys = new ArrayList<Integer>();
		if (surveyersModel.getSurveyKey() == null || surveyersModel.getUserKeys() == null || surveyersModel.getUserKeys().isEmpty())
			return surveyersModel;
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			
			for (Integer userKey:surveyersModel.getUserKeys()) {
				
				Query userQuery = JpaUtil.getEntityManager()
						.createQuery(" Select u from Users u Where u.userKey=:userKey)", Users.class);
				userQuery.setParameter("userKey", userKey);
				List<Users> usresList = userQuery.getResultList();
				if (!CollectionUtils.isEmpty(usresList)) {
					Users superUser = usresList.get(0);
					Query surveyQuery = JpaUtil.getEntityManager().createQuery(
							" Select s from Survey s Where s.surveyKey=:surveyKey)",
							Survey.class);
					surveyQuery.setParameter("surveyKey", surveyersModel.getSurveyKey());					
					List<Survey> surveyList = surveyQuery.getResultList();
					Survey survey = new Survey();
						if (!CollectionUtils.isEmpty(usresList)) {
							survey = surveyList.get(0);
						
						UserSurveyAccess supervisorAccess = new UserSurveyAccess();
						survey.setSurveyKey(surveyersModel.getSurveyKey());
						supervisorAccess.setSurvey(survey);
						supervisorAccess.setUsers(superUser);
						supervisorAccess.setStatus(ServiceConstants.STATUS_IN_PROGRESS);
						JpaUtil.getEntityManager().merge(supervisorAccess);
						// EmailService emailService = new EmailService();
						// emailService.sendEmail(ServiceConstants.EMAIL_FROM_ADDRESS,
						// supervisorEmail,
						// ServiceConstants.EMAIL_SUBJECT,
						// ServiceConstants.EMAIL_MESSAGE);
						}
					}
				}
			
		} catch (Exception e) {
			
			e.printStackTrace();

			JpaUtil.getEntityManager().getTransaction().rollback();
		} finally {
			JpaUtil.closeEntityManager();
		}
		return getUsersForSurvey(surveyersModel.getSurveyKey());
	}

	public void delteUsersinSurvey(SurveyersModel surveyersModel) {
		if (surveyersModel.getSurveyKey() == null && surveyersModel.getUserKeys().isEmpty())
			return;
		int surveyKey = surveyersModel.getSurveyKey();
		for (Integer userKey : surveyersModel.getUserKeys()) {
			try {
				JpaUtil.getEntityManager().getTransaction().begin();
				JpaUtil.getEntityManager()
						.createNativeQuery(
								"Delete from answers where User_Surver_Access_Answers_Key in (Select user_survey_key from user_survey_access where survey_key = "
										+ surveyKey + " and user_key = "+userKey+")")
						.executeUpdate();
				JpaUtil.getEntityManager()
						.createNativeQuery("Delete from user_survey_access where survey_key = " + surveyKey +" and user_key = "+userKey )
						.executeUpdate();
				JpaUtil.getEntityManager().getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				JpaUtil.getEntityManager().getTransaction().rollback();
			} finally {
				JpaUtil.closeEntityManager();
			}
		}
		
	}
		

}
