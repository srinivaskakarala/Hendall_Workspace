
package com.hendall.surveyrest.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hendall.surveyrest.datamodel.Section;
import com.hendall.surveyrest.datamodel.SectionHelpWrapper;
import com.hendall.surveyrest.datamodel.UserSession;
import com.hendall.surveyrest.datamodel.ViewMySurveys;
import com.hendall.surveyrest.dto.ProvidersDTO;
import com.hendall.surveyrest.dto.StatesDTO;
import com.hendall.surveyrest.entities.ProvidersLu;
import com.hendall.surveyrest.entities.StatesLu;
import com.hendall.surveyrest.helpers.QuestionAnswerHelper;
import com.hendall.surveyrest.helpers.QuestionsHelper;
import com.hendall.surveyrest.helpers.UsersServiceHelper;
import com.hendall.surveyrest.jpa.JpaUtil;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {
   
	private static final String path = "Hospital_Infection_Control_Worksheet_v0_22a.json";

	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("questions")
    public SectionHelpWrapper getIt() {
		try {
			 EntityManager entityManager = JpaUtil.getEntityManager();
			System.out.println(entityManager.createQuery("Select u From Users u").getResultList().size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QuestionsHelper questionsHelper = new QuestionsHelper();
        return questionsHelper.getQuestions();
    }
    
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/questionanswers")
    public SectionHelpWrapper getQuestionAnswers(@QueryParam("userKey") int userKey,
    		@QueryParam("surveyKey") int surveyKey){
		QuestionAnswerHelper  questionAnswerHelper = new QuestionAnswerHelper();
		return questionAnswerHelper.getQuestionAnswers(userKey, surveyKey);
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/questionanswers")
	public SectionHelpWrapper saveSurvey(List<Section> answers){
		
		QuestionAnswerHelper  questionAnswerHelper = new QuestionAnswerHelper();		
		return questionAnswerHelper.saveAnswers(answers);
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/questionanswers")
	public void deleteSurvey(@QueryParam("surveyKey") int surveyKey){		
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();		
		usersServiceHelper.delteSurvey(surveyKey);
	}

	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/states")
    public List<StatesDTO>  getStates(){
		QuestionAnswerHelper  questionAnswerHelper = new QuestionAnswerHelper();
		return questionAnswerHelper.getStatesDTO();
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/providers")
    public List<ProvidersDTO>  getProviders(@QueryParam("state") String state ){
		QuestionAnswerHelper  questionAnswerHelper = new QuestionAnswerHelper();
		return questionAnswerHelper.getProvidersDTO(state);
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/usersurveys")
    public List<ViewMySurveys>  getUserSurveys(@QueryParam("userKey") int userKey ){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.getUsersSurveys(userKey);
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/authentication")
    public UserSession  getUserSurveys(@QueryParam("userName") String userName,
    		@QueryParam("password") String password){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.getUsersSurveys(userName, password);
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/users")
    public List<UserSession>  getUserSurveys(@QueryParam("state") String state){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.getUsers(state);
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/status")
    public String  getServiceStatus(){
		return "available";
	}
	
	@PUT
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/assignsurvey")
    public String  getUserSurveys(@QueryParam("userKey") int userKey,
    		@QueryParam("surveyKey") int surveyKey,
    		@QueryParam("userEmail") String userEmail){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.assingToOtherUser(userKey, surveyKey, userEmail);
	}
}
