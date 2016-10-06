package com.hendall.surveyrest.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;

import com.hendall.surveyrest.assemblers.UsersAssembler;
import com.hendall.surveyrest.assemblers.ViewMySurveysAssembler;
import com.hendall.surveyrest.common.ServiceConstants;
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
			List<Integer> survyeKeyList = new ArrayList<Integer>();
			for (UserSurveyAccess userSurveyAccess : userSurveyAccessList) {
				if (!survyeKeyList.contains(userSurveyAccess.getSurvey().getSurveyKey()))
					survyeKeyList.add(userSurveyAccess.getSurvey().getSurveyKey());
			}

			Map<String, String> providersMap = new HashMap<String, String>();
			populateProvidersMap(providersMap);
			Map<Integer, String> surveyProviderMap = new HashMap<Integer, String>();
			if (CollectionUtils.isNotEmpty(survyeKeyList)) {
				Query answersQuery = JpaUtil.getEntityManager().createQuery(
						"Select A From Answers A  join fetch A.userSurveyAccess usa join fetch usa.survey s  Where s.surveyKey in (:surveyKeys) and A.id.htmlControlId=200",
						Answers.class);
				answersQuery.setParameter("surveyKeys", survyeKeyList);
				List<Answers> answersList = answersQuery.getResultList();

				populateSurveyProviderMap(surveyProviderMap, answersList);
			}
		
			for (UserSurveyAccess answers : userSurveyAccessList) {
				ViewMySurveys viewMySurveys = new ViewMySurveys();
				ViewMySurveysAssembler.assembleViewMyServeyVo(viewMySurveys, answers, providersMap, surveyProviderMap);
				viewMySurveyList.add(viewMySurveys);
			}
		} finally {
			JpaUtil.closeEntityManager();
		}

		return viewMySurveyList;
	}

	public void populateSurveyProviderMap(Map<Integer, String> map, List<Answers> answersList) {
		for (Answers answers : answersList) {
			map.put(answers.getUserSurveyAccess().getSurvey().getSurveyKey(), answers.getAnswer());
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

	public void approveOrRejectSurvey(Integer surveyKey, String status) {
		if (surveyKey == null)
			return;
		Query query = JpaUtil.getEntityManager().createQuery(
				"Select u from UserSurveyAccess u join fetch u.survey s where s.surveyKey=:surveyKey",
				UserSurveyAccess.class);
		query.setParameter("surveyKey", surveyKey);
		List<UserSurveyAccess> resultList = query.getResultList();
		for (UserSurveyAccess userSurveyAccess : resultList) {
			userSurveyAccess.setStatus(status);
			JpaUtil.getEntityManager().merge(userSurveyAccess);
		}

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
						" Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u Where u.userKey=:userKey and s.surveyKey=:surveyKey)",
						UserSurveyAccess.class);
				userAccessQuery.setParameter("userKey", userKey);
				userAccessQuery.setParameter("surveyKey", surveyKey);
				List<UserSurveyAccess> userSurveyAccessList = userAccessQuery.getResultList();
				if (CollectionUtils.isEmpty(userSurveyAccessList)) {
					return "";
				} else {
					UserSurveyAccess userSurveyAccess = userSurveyAccessList.get(0);
					userSurveyAccess.setStatus(ServiceConstants.STATUS_IN_SUBMITTED);
					JpaUtil.getEntityManager().merge(userSurveyAccess);

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

	public List<Integer> getUsersForSurvey(Integer surveyKey) {
		List<Integer> users = new ArrayList<Integer>();
		if (surveyKey == null)
			return users;
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			Query query = JpaUtil.getEntityManager().createQuery(
					"Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u where s.surveyKey=:surveyKey and usa.status=:status",
					UserSurveyAccess.class);
			query.setParameter("surveyKey", surveyKey);
			query.setParameter("status", ServiceConstants.STATUS_IN_PROGRESS);
			List<UserSurveyAccess> resultList = query.getResultList();
			for (UserSurveyAccess userSurveyAccess:resultList){
				users.add(userSurveyAccess.getUsers().getUserKey());
				}			
			} catch (Exception e) {
	
				e.printStackTrace();
	
				JpaUtil.getEntityManager().getTransaction().rollback();
			} finally {
				JpaUtil.closeEntityManager();
			}
		return users;
	}

	public List<Integer> addUserstoSurvey(Integer surveyKey, List<Integer> users) {
		List<Integer> userkeys = new ArrayList<Integer>();
		if (surveyKey == null || users == null || users.isEmpty())
			return userkeys;
		try {
			JpaUtil.getEntityManager().getTransaction().begin();
			
			for (Integer userKey:users) {
				
				Query userQuery = JpaUtil.getEntityManager()
						.createQuery(" Select u from Users u Where u.userKey=:userKey)", Users.class);
				userQuery.setParameter("userKey", userKey);
				List<Users> usresList = userQuery.getResultList();
				if (!CollectionUtils.isEmpty(usresList)) {
					Users superUser = usresList.get(0);
					Query surveyQuery = JpaUtil.getEntityManager().createQuery(
							" Select s from survey s Where s.surveyKey=:surveyKey)",
							Survey.class);
					surveyQuery.setParameter("surveyKey", surveyKey);					
					List<Survey> surveyList = surveyQuery.getResultList();
					Survey survey = new Survey();
						if (!CollectionUtils.isEmpty(usresList)) {
							survey = surveyList.get(0);
						
						UserSurveyAccess supervisorAccess = new UserSurveyAccess();
						survey.setSurveyKey(surveyKey);
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
		return getUsersForSurvey(surveyKey);
	}
		

}
