
package com.hendall.surveyrest.services;

import java.io.InputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hendall.surveyrest.datamodel.Section;
import com.hendall.surveyrest.datamodel.SectionHelpWrapper;
import com.hendall.surveyrest.datamodel.SurveyersModel;
import com.hendall.surveyrest.datamodel.UserSession;
import com.hendall.surveyrest.datamodel.ViewMySurveys;
import com.hendall.surveyrest.dto.ProvidersDTO;
import com.hendall.surveyrest.dto.StatesDTO;
import com.hendall.surveyrest.entities.ProvidersLu;
import com.hendall.surveyrest.entities.StatesLu;
import com.hendall.surveyrest.helpers.FileHelper;
import com.hendall.surveyrest.helpers.QuestionAnswerHelper;
import com.hendall.surveyrest.helpers.QuestionsHelper;
import com.hendall.surveyrest.helpers.UsersServiceHelper;
import com.hendall.surveyrest.jpa.JpaUtil;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
    public UserSession  authentication(@QueryParam("userName") String userName,
    		@QueryParam("password") String password){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.getUsersSurveys(userName, password);
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/users")
    public List<UserSession>  getUsers(@QueryParam("state") String state){
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
    public String  manageSurvey(@QueryParam("userKey") int userKey,
    		@QueryParam("surveyKey") int surveyKey,
    		@QueryParam("userEmail") String userEmail){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.assingToOtherUser(userKey, surveyKey, userEmail);
	}
	
	@PUT
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/approveorreject")
    public String  approveOrRejectSurvey(@QueryParam("surveyKey") int surveyKey,
    		@QueryParam("userKey") int userKey,
    		@QueryParam("status") String status,
    		@QueryParam("comments") String supervisorComments){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.approveOrRejectSurvey(surveyKey, userKey, status, supervisorComments);
	}
	
	@PUT
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/file")
    public String  uploadFile( @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("path") String path){
		FileHelper  fileHelper = new FileHelper();
		return fileHelper.uploadFile(uploadedInputStream, fileDetail, path);
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/surveyUsers")
    public SurveyersModel  getUsersForSurvey(@QueryParam("surveyKey") Integer surveyKey){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.getUsersForSurvey(surveyKey);
	}
	
	@PUT
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/surveyUsers")
    public SurveyersModel  addUserstoSurvey(SurveyersModel surveyersModel){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.addUserstoSurvey(surveyersModel);
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/deleteSurveyUsers")
	public void deleteUsersinSurvey(SurveyersModel surveyersModel){		
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();		
		usersServiceHelper.delteUsersinSurvey(surveyersModel);
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/primaryuser")
    public Boolean  isPrimaryUser(@QueryParam("surveyKey") Integer surveyKey,
    		@QueryParam("userKey") int userKey){
		UsersServiceHelper  usersServiceHelper = new UsersServiceHelper();
		return usersServiceHelper.isPrimaryUser(surveyKey, userKey);
	}
}
