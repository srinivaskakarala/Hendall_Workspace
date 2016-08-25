package com.hendall.survey.ui.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.UserSession;
import com.hendall.survey.services.datamodel.ViewMySurveys;
import com.hendall.survey.services.ejb.UsersService;
import com.hendall.survey.ui.assemblers.MenuModelAssembler;
import com.hendall.survey.ui.constants.UIConstants;
import com.hendall.survey.ui.util.WebApplicationUtil;

@ManagedBean(name = UIConstants.VIEW_MY_SURVEY_BEAN)
@ViewScoped
public class ViewMySurveysBean implements Serializable {

	@EJB
	private UsersService userService;
	private List<ViewMySurveys> ViewMySurveysList;
	private MenuModel menuModel;
	private String surveyKey;
	private String status;

	public ViewMySurveysBean() {
		ViewMySurveysList = new ArrayList<ViewMySurveys>();
	}

	@PostConstruct
	public void populateUserSurveyAccessList() {
		UserSession userSession = (UserSession) WebApplicationUtil.getSessionAttribute(UIConstants.USER_SESSION_KEY);
		ViewMySurveysList = userService.getUsersSurveys(userSession.getUserKey());
		menuModel = new DefaultMenuModel();
		MenuModelAssembler menuModelAssembler = new MenuModelAssembler();
		menuModelAssembler.assembleMenuModel(menuModel, null);
	}

	public String editSurvery() {
		WebApplicationUtil.setRequestAttribute(UIConstants.REQUEST_ATTRIBUTE_SATE_PROVIDER, surveyKey);
		UserSession userSession = (UserSession) WebApplicationUtil.getSessionAttribute(UIConstants.USER_SESSION_KEY);
		userSession.setCurrentSurveyStatus(status);
		return "survey.jsf";
	}

	public String startNewSurvery() {
		UserSession userSession = (UserSession) WebApplicationUtil.getSessionAttribute(UIConstants.USER_SESSION_KEY);
		userSession.setCurrentSurveyStatus(status);
		return "survey.jsf";
	}

	public String viewSurvery() {
		WebApplicationUtil.setRequestAttribute(UIConstants.REQUEST_ATTRIBUTE_SATE_PROVIDER, surveyKey);
		WebApplicationUtil.setRequestAttribute(UIConstants.REQUEST_ATTRIBUTE_READ_ONLY_MODE, "true");
		UserSession userSession = (UserSession) WebApplicationUtil.getSessionAttribute(UIConstants.USER_SESSION_KEY);
		userSession.setCurrentSurveyStatus(status);
		return "viewSurveyReadOnly.jsf";
	}
	public String  delete(){
		//WebApplicationUtil.getRequestParam(key)
		userService.delteSurvey(Integer.valueOf(surveyKey));
		return "viewMySurveys.jsf";
		
	}
	public List<ViewMySurveys> getViewMySurveysList() {
		return ViewMySurveysList;
	}

	public void setViewMySurveysList(List<ViewMySurveys> viewMySurveysList) {
		ViewMySurveysList = viewMySurveysList;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public String getSurveyKey() {
		return surveyKey;
	}

	public void setSurveyKey(String surveyKey) {
		this.surveyKey = surveyKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
