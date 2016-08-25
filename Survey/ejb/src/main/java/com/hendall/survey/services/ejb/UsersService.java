package com.hendall.survey.services.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;

import com.hendall.survey.services.assemblers.UsersAssembler;
import com.hendall.survey.services.assemblers.ViewMySurveysAssembler;
import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.UserSession;
import com.hendall.survey.services.datamodel.ViewMySurveys;
import com.hendall.survey.services.entities.Answers;
import com.hendall.survey.services.entities.ProvidersLu;
import com.hendall.survey.services.entities.Survey;
import com.hendall.survey.services.entities.UserSurveyAccess;
import com.hendall.survey.services.entities.Users;

/**
 * Session Bean implementation class UsersService
 */
@Stateless
@LocalBean
public class UsersService extends BaseService {

	/**
	 * Default constructor.
	 */
	public UsersService() {
		// TODO Auto-generated constructor stub
	}

	public UserSession getUsersSurveys(String userName, String password) {
		Query query = getEntityManager()
				.createQuery(" Select A from Users A where A.userId=:userId and A.password=:password", Users.class);
		query.setParameter("userId", userName);
		query.setParameter("password", password);
		List<Users> usersList = query.getResultList();
		UserSession userSession = new UserSession();
		if (CollectionUtils.isNotEmpty(usersList)) {
			UsersAssembler.assembleUserSession(usersList.get(0), userSession);
			return userSession;
		}

		return null;
	}
	public Map<String, String> getStateUsers(String state){
		Map<String, String> stateUsersMap = new HashMap<String, String>();
		Query query = getEntityManager()
				.createQuery(" Select A from Users A where A.state=:state", Users.class);
		query.setParameter("state", state); 
		List<Users> usersList=query.getResultList();
		if(CollectionUtils.isNotEmpty(usersList)){
			for (Users users : usersList) {
				stateUsersMap.put( users.getFirstName()+" "+users.getLastName() +"("+users.getEmail()+")", users.getEmail());
			}
		}
		return stateUsersMap;
	}
	
	public void delteSurvey(int surveyKey){
		getEntityManager().createNativeQuery("Delete from answers where User_Surver_Access_Answers_Key in (Select user_survey_key from user_survey_access where survey_key = "+surveyKey+")").executeUpdate();
		getEntityManager().createNativeQuery("Delete from user_survey_access where survey_key = "+surveyKey).executeUpdate();
		getEntityManager().createNativeQuery("Delete from survey where survey_key = "+surveyKey).executeUpdate();
	}

	public List<ViewMySurveys> getUsersSurveys(int userKey) {

		Query query = getEntityManager().createQuery(" Select Usa From UserSurveyAccess Usa  "
				+ " join fetch Usa.survey sur " + " join fetch Usa.users u " + " join fetch Usa.survey sur "
				+ " join fetch sur.surveyTypeLu type " + " where u.userKey =:userKey ", UserSurveyAccess.class);
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
			Query answersQuery = getEntityManager().createQuery(
					"Select A From Answers A  join fetch A.userSurveyAccess usa join fetch usa.survey s  Where s.surveyKey in (:surveyKeys) and A.id.htmlControlId=200",
					Answers.class);
			answersQuery.setParameter("surveyKeys", survyeKeyList);
			List<Answers> answersList = answersQuery.getResultList();
			populateSurveyProviderMap(surveyProviderMap, answersList);
		}
		List<ViewMySurveys> viewMySurveyList = new ArrayList<ViewMySurveys>();

		for (UserSurveyAccess answers : userSurveyAccessList) {
			ViewMySurveys viewMySurveys = new ViewMySurveys();
			ViewMySurveysAssembler.assembleViewMyServeyVo(viewMySurveys, answers, providersMap, surveyProviderMap);
			viewMySurveyList.add(viewMySurveys);
		}

		return viewMySurveyList;
	}

	public void populateSurveyProviderMap(Map<Integer, String> map, List<Answers> answersList) {
		for (Answers answers : answersList) {
			map.put(answers.getUserSurveyAccess().getSurvey().getSurveyKey(), answers.getAnswer());
		}

	}

	public void populateProvidersMap(Map<String, String> map) {
		List<ProvidersLu> providersList = getEntityManager()
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
			getEntityManager().detach(answers);
			;
		}

	}

	public void approveOrRejectSurvey(Integer surveyKey, String status) {
		if (surveyKey == null)
			return;
		Query query = getEntityManager().createQuery(
				"Select u from UserSurveyAccess u join fetch u.survey s where s.surveyKey=:surveyKey",
				UserSurveyAccess.class);
		query.setParameter("surveyKey", surveyKey);
		List<UserSurveyAccess> resultList = query.getResultList();
		for (UserSurveyAccess userSurveyAccess : resultList) {
			userSurveyAccess.setStatus(status);
			getEntityManager().merge(userSurveyAccess);
		}

	}

	@Transactional
	public String assingToOtherUser(Integer userKey, Integer surveyKey, String supervisorEmail) {
		if (userKey == null || surveyKey == null || supervisorEmail == null)
			return "error";
		Query userQuery = getEntityManager().createQuery(" Select u from Users u Where u.email=:supervisorEmail)",
				Users.class);
		userQuery.setParameter("supervisorEmail", supervisorEmail);
		List<Users> usresList = userQuery.getResultList();
		if (CollectionUtils.isEmpty(usresList)) {
			return "No user with that email address exists in the sytem";
		} else {
			Users superUser = usresList.get(0);
			Query userAccessQuery = getEntityManager().createQuery(
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
				getEntityManager().merge(userSurveyAccess);
				
				Query supervisorAccessQuery = getEntityManager().createQuery(
						" Select usa from UserSurveyAccess usa join fetch usa.survey s join fetch usa.users u Where u.email=:supervisorEmail and s.surveyKey=:surveyKey)",
						UserSurveyAccess.class);
				supervisorAccessQuery.setParameter("surveyKey", surveyKey);
				supervisorAccessQuery.setParameter("supervisorEmail", supervisorEmail);
				List<UserSurveyAccess> supervisorUserSurveyAccessList = supervisorAccessQuery.getResultList();
				UserSurveyAccess supervisorAccess = null;
				if(CollectionUtils.isNotEmpty(supervisorUserSurveyAccessList)){
					supervisorAccess = supervisorUserSurveyAccessList.get(0);
				}else{
					 supervisorAccess = new UserSurveyAccess();;
				}
				
				
				Survey survey = new Survey();
				survey.setSurveyKey(surveyKey);
				supervisorAccess.setSurvey(survey);
				supervisorAccess.setUsers(superUser);
				supervisorAccess.setStatus(ServiceConstants.STATUS_IN_PENDING_REVIEW);
				getEntityManager().merge(supervisorAccess);
				EmailService emailService = new EmailService();
				emailService.sendEmail(ServiceConstants.EMAIL_FROM_ADDRESS, supervisorEmail,
						ServiceConstants.EMAIL_SUBJECT, ServiceConstants.EMAIL_MESSAGE);

			}
		}
		return "Successful";
	}

}
