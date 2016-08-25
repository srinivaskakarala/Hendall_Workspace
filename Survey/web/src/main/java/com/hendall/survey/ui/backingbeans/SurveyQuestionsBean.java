package com.hendall.survey.ui.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.primefaces.context.RequestContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Answer;
import com.hendall.survey.services.datamodel.Question;
import com.hendall.survey.services.datamodel.Section;
import com.hendall.survey.services.datamodel.SectionHelpWrapper;
import com.hendall.survey.services.datamodel.UserSession;
import com.hendall.survey.services.datamodel.Validation;
import com.hendall.survey.services.ejb.QuestionAnswerService;
import com.hendall.survey.services.ejb.UsersService;
import com.hendall.survey.services.ejb.ValidationService;
import com.hendall.survey.ui.assemblers.MenuModelAssembler;
import com.hendall.survey.ui.constants.UIConstants;
import com.hendall.survey.ui.util.WebApplicationUtil;

@ManagedBean(name = UIConstants.SURVEY_QUESTIONS_BEAN)
@ViewScoped

public class SurveyQuestionsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Section> surveyQuestionSectionList;
	private List<Section> masterSurveyQuestionSectionList;
	private String sectionQuestionId;
	private static final String SECTION_QUETION_SEPERATOR = "__";
	private int pageIndex = 1;
	private MenuModel menuModel;
	private List<Validation> validationsList;

	private String errorSection;
	private String errorId;
	private String surveyKey;
	private UserSession userSession;
	private String approverEmailAddress;
	private boolean readOnlyMode = false;
	private String oldValue = null;
	@EJB
	private QuestionAnswerService questionAnswerService;
	@EJB
	private ValidationService validationService;
	@EJB
	private UsersService userService;
	private Map<String, String> approversMap;
	private String cancelId;

	public SurveyQuestionsBean() {
		masterSurveyQuestionSectionList = new ArrayList<Section>();
		surveyQuestionSectionList = new ArrayList<Section>();
		validationsList = new ArrayList<Validation>();
		approversMap = new HashMap<String, String>();

	}

	public void processDependentDropDowns(AjaxBehaviorEvent event) {
		String id = (String) WebApplicationUtil.getRequestParam("id");
		UIInput component = (UIInput) event.getComponent();
		InfectionControlSpecialCasesBean infectionControlSpecialCasesBean = new InfectionControlSpecialCasesBean();
		infectionControlSpecialCasesBean.processDropDowns(id, component.getValue().toString(),
				masterSurveyQuestionSectionList);

		System.out.println("tst");
	}

	@PostConstruct
	public void populateData() {
		userSession = (UserSession) WebApplicationUtil.getSessionAttribute(UIConstants.USER_SESSION_KEY);
		surveyKey = (String) WebApplicationUtil.getRequestAttribute(UIConstants.REQUEST_ATTRIBUTE_SATE_PROVIDER);
		surveyKey = (String) WebApplicationUtil.getRequestAttribute(UIConstants.REQUEST_ATTRIBUTE_SATE_PROVIDER);
		String mode = (String) WebApplicationUtil.getRequestAttribute(UIConstants.REQUEST_ATTRIBUTE_READ_ONLY_MODE);
		// approversMap=userService.getStateUsers(userSession.getState());
		if (mode != null) {
			readOnlyMode = Boolean.valueOf(mode);
		} else {
			readOnlyMode = false;
		}

		/* New Survey */
		if (surveyKey == null) {
			surveyKey = "-1";
		}
		SectionHelpWrapper sectionHelpWrapper = questionAnswerService.getQuestionAnswers(userSession.getUserKey(),
				Integer.valueOf(surveyKey));
		populateMasterListandMenu(sectionHelpWrapper);

	}

	public void populateMasterListandMenu(SectionHelpWrapper sectionHelpWrapper) {

		if (sectionHelpWrapper == null)
			return;
		masterSurveyQuestionSectionList = sectionHelpWrapper.getSections();
		populatePageSectionsData();
		menuModel = new DefaultMenuModel();
		MenuModelAssembler menuModelAssembler = new MenuModelAssembler();
		menuModelAssembler.assembleMenuModel(menuModel, sectionHelpWrapper);
	}

	public void save() {
		try {
			saveData();
			WebApplicationUtil.addGlobalMessage(UIConstants.SAVE_MESSAGE, FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().execute("$('.ui-layout-center').animate({scrollTop: 0}, 'fast');");
		} catch (Exception e) {
			e.printStackTrace();
			WebApplicationUtil.addGlobalMessage(UIConstants.ERROR_MESSAGE, FacesMessage.SEVERITY_ERROR);
		}

	}

	public void saveData() {
		userSession = (UserSession) WebApplicationUtil.getSessionAttribute(UIConstants.USER_SESSION_KEY);
		surveyQuestionSectionList.get(0).setUserKey(userSession.getUserKey());
		surveyQuestionSectionList.get(0).setSurveyKey(Integer.valueOf(surveyKey));
		SectionHelpWrapper sectionHelpWrapper = questionAnswerService.saveAnswers(surveyQuestionSectionList);
		masterSurveyQuestionSectionList = sectionHelpWrapper.getSections();
		surveyKey = masterSurveyQuestionSectionList.get(0).getSurveyKey().toString();
		WebApplicationUtil.setRequestAttribute(surveyKey, UIConstants.REQUEST_ATTRIBUTE_SATE_PROVIDER);
		populateMasterListandMenu(sectionHelpWrapper);
	}

	public String submit() {
		try {
			saveData();
			System.out.println(approverEmailAddress);
			UserSession userSession = (UserSession) WebApplicationUtil
					.getSessionAttribute(UIConstants.USER_SESSION_KEY);
			if (userSession.getEmail().equals(approverEmailAddress)) {
				WebApplicationUtil.addGlobalMessage("Supervisor's email address cant be your email address",
						FacesMessage.SEVERITY_ERROR);
				return "";
			}
			userService.assingToOtherUser(userSession.getUserKey(), Integer.valueOf(surveyKey), approverEmailAddress);
			WebApplicationUtil.addGlobalMessage(UIConstants.SUBMIT_MESSAGE, FacesMessage.SEVERITY_INFO);
			return "/pages/viewMySurveys.jsf";

		} catch (Exception e) {
			e.printStackTrace();
			WebApplicationUtil.addGlobalMessage(UIConstants.ERROR_MESSAGE, FacesMessage.SEVERITY_ERROR);
		}
		return "";

	}

	public void approveOrReject(AjaxBehaviorEvent event) {
		String id = event.getComponent().getId();
		if (id.contains(ServiceConstants.STATUS_APPROVE))
			userService.approveOrRejectSurvey(Integer.valueOf(surveyKey), ServiceConstants.STATUS_APPROVE);
		else
			userService.approveOrRejectSurvey(Integer.valueOf(surveyKey), ServiceConstants.STATUS_REJECT);
	}

	private void populateErrorMessages() {
		/*
		 * validationsList =
		 * validationService.getValidationsList(masterSurveyQuestionSectionList)
		 * ; if (CollectionUtils.isNotEmpty(validationsList)) { for (Validation
		 * validation : validationsList) { if
		 * (!StringUtils.isEmpty(validation.getMessage())) {
		 * WebApplicationUtil.addMessage(validation.getErrorMessageCompId(),
		 * validation.getMessage(), FacesMessage.SEVERITY_ERROR);
		 * 
		 * // WebApplicationUtil.addGlobalMessage( //
		 * validation.getMessage(),FacesMessage.SEVERITY_ERROR); }
		 * 
		 * } }
		 */
	}

	public void jumpToErrorSection() {
		pageIndex = Integer.parseInt(errorSection);
		populatePageSectionsData();
		populateErrorMessages();

	}

	public void jumpToSection(ActionEvent event) {
		saveData();
		MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
		pageIndex = Integer.parseInt(menuItem.getParams().get(UIConstants.SUBMENU_JUMP_TO_SECTION).get(0));
		populatePageSectionsData();
	}

	public void nextPage() {
		if (pageIndex < masterSurveyQuestionSectionList.size() + 1)
			pageIndex++;
		saveData();
		populatePageSectionsData();

	}

	public void prevPage() {
		if (pageIndex > 1)
			pageIndex--;
		saveData();
		populatePageSectionsData();

	}

	private void populatePageSectionsData() {
		surveyQuestionSectionList.clear();
		if (CollectionUtils.isNotEmpty(masterSurveyQuestionSectionList) && pageIndex >= 0) {
			if (pageIndex <= masterSurveyQuestionSectionList.size()) {
				surveyQuestionSectionList.add(masterSurveyQuestionSectionList.get(pageIndex - 1));
			}
			if (pageIndex == masterSurveyQuestionSectionList.size()) {
				UserSession userSession = (UserSession) WebApplicationUtil
						.getSessionAttribute(UIConstants.USER_SESSION_KEY);
				approversMap = userService.getStateUsers(userSession.getState());
			}
		}
	}

	public void addObservation() {
		System.out.println("value  : " + sectionQuestionId);
		String splitIndex[] = sectionQuestionId.split(SECTION_QUETION_SEPERATOR);
		boolean checkBoxAdded = false;
		boolean textboxAdded = false;
		for (Answer answer : surveyQuestionSectionList.get(Integer.valueOf(splitIndex[0])).getSurveyQuestionAnswerList()
				.get(Integer.valueOf(splitIndex[1])).getAnswersList()) {
			if (!answer.isDefaultVisible() && !answer.isRenderRemoveButton()) {
				if (answer.getHtmlControlType().equalsIgnoreCase(ServiceConstants.HTML_CONTROL_RADIO)
						&& !checkBoxAdded) {
					answer.setRenderRemoveButton(true);
					answer.setDefaultVisible(true);
					checkBoxAdded = true;
				}
				if (answer.getHtmlControlType().equalsIgnoreCase(ServiceConstants.HTML_CONTROL_TEXT_AREA)
						&& !textboxAdded) {
					answer.setRenderRemoveButton(true);
					answer.setDefaultVisible(true);
					textboxAdded = true;
				}
				if (textboxAdded && checkBoxAdded) {
					break;
				}
			}

		}
		Question question = surveyQuestionSectionList.get(Integer.valueOf(splitIndex[0])).getSurveyQuestionAnswerList()
				.get(Integer.valueOf(splitIndex[1]));
		question.populateObservationNumbers();

	}

	public void removeObservation() {
		String splitIndex[] = sectionQuestionId.split(SECTION_QUETION_SEPERATOR);

		for (Answer answer : surveyQuestionSectionList.get(Integer.valueOf(splitIndex[0])).getSurveyQuestionAnswerList()
				.get(Integer.valueOf(splitIndex[1])).getAnswersList()) {
			if (answer.getHtmlControlType().equalsIgnoreCase(ServiceConstants.HTML_CONTROL_RADIO)
					&& answer.getHtmlControlId() == Integer.valueOf(splitIndex[2])) {
				answer.setAnswer(null);
				answer.setRenderRemoveButton(false);
				answer.setDefaultVisible(false);
			}
			if (answer.getHtmlControlType().equalsIgnoreCase(ServiceConstants.HTML_CONTROL_TEXT_AREA)
					&& (answer.getParentId() == Integer.valueOf(splitIndex[2]))) {
				answer.setAnswer(null);
				answer.setRenderRemoveButton(false);
				answer.setDefaultVisible(false);
			}
		}
		Question question = surveyQuestionSectionList.get(Integer.valueOf(splitIndex[0])).getSurveyQuestionAnswerList()
				.get(Integer.valueOf(splitIndex[1]));
		question.populateObservationNumbers();
	}

	public void processOldValue(ValueChangeEvent event) {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id = map.get("id");
		String clientId = map.get("clientId");
		if (event.getComponent().getClientId().equals(clientId)) {
			oldValue = (String) event.getOldValue();
			System.out.println(id + "   ===== " + clientId + "====" + event.getOldValue());
		}

	}

	public String disableUnableToObserve(AjaxBehaviorEvent event) {
		// InfectionControlSpecialCasesBean infectionControlSpecialCasesBean =
		// new InfectionControlSpecialCasesBean();
		String value = (String) ((UIInput) event.getSource()).getValue();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String id = map.get("id");

		if (value.equals(ServiceConstants.UNABLE_TO_OBSERVE)) {
			Integer questionId = Integer.valueOf(WebApplicationUtil.getRequestParam("questionId").toString());

			for (Question question : surveyQuestionSectionList.get(0).getSurveyQuestionAnswerList()) {
				if (question.getQuestionId().intValue() == questionId.intValue()) {
					for (int k = 2; k < question.getAnswersList().size(); k++) {
						Answer answer = question.getAnswersList().get(k);
						if (StringUtils.isNotBlank(answer.getAnswer())) {
							RequestContext.getCurrentInstance().execute("PF('unableToObserveDialog').show();");
							cancelId = id;
							return "survey.jsf";

						}

					}
				}
			}

		}
		populateRenderObservations();
		for (Question question : surveyQuestionSectionList.get(0).getSurveyQuestionAnswerList()) {
			if (value != null) {
				List<Integer> dependentList = ServiceConstants.infectionControlDepenentComponents
						.get(Integer.valueOf(id));
				if (CollectionUtils.isEmpty(dependentList))
					continue;
				if (dependentList.contains(question.getQuestionId())) {
					if (ServiceConstants.INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.contains(value)) {
						question.setRenderQuestion(false);
					}
					if (ServiceConstants.INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST
							.contains(value)) {
						question.setRenderQuestion(true);
						// question.setRenderAddObservation(true);
					}
				}
			}
		}
		return "survey.jsf";
	}

	public void processUnableToObserveCancel(ActionEvent event) {
		for (Question question : surveyQuestionSectionList.get(0).getSurveyQuestionAnswerList()) {
			for (Answer answer : question.getAnswersList()) {
				if (question.isRenderAddObservation()) {
					if (answer.getAnswer() != null) {
						if (answer.getAnswer().equals(ServiceConstants.UNABLE_TO_OBSERVE)
								&& Integer.valueOf(cancelId) == answer.getHtmlControlId()) {
							answer.setAnswer(oldValue);
						}
					}
				}
			}
		}

	}

	public void processUnableToObserve(ActionEvent event) {
		populateRenderObservations();
	}

	private void populateRenderObservations() {
		for (Question question : surveyQuestionSectionList.get(0).getSurveyQuestionAnswerList()) {
			boolean containsUnableToObserve = false;
			for (Answer answer : question.getAnswersList()) {
				if (question.isRenderAddObservation()) {
					if (answer.getAnswer() != null) {
						if (answer.getAnswer().equals(ServiceConstants.UNABLE_TO_OBSERVE)
								|| answer.getAnswer().equals(ServiceConstants.NA)) {
							containsUnableToObserve = true;

						}
					}
				}
			}
			if (containsUnableToObserve) {
				question.setDisableAddObservation(true);
				if (CollectionUtils.isNotEmpty(question.getAnswersList())) {
					for (int i = 2; i < question.getAnswersList().size(); i++) {
						Answer answer = question.getAnswersList().get(i);
						answer.setAnswer(null);
						answer.setRenderRemoveButton(false);
						answer.setDefaultVisible(false);
					}
				}
				question.populateObservationNumbers();
			} else {
				question.setDisableAddObservation(false);
			}
		}
	}

	public List<Section> getSurveyQuestionSectionList() {
		return surveyQuestionSectionList;
	}

	public void setSurveyQuestionSectionList(List<Section> surveyQuestionSectionList) {
		this.surveyQuestionSectionList = surveyQuestionSectionList;
	}

	public String getSectionQuestionId() {
		return sectionQuestionId;
	}

	public void setSectionQuestionId(String sectionQuestionId) {
		this.sectionQuestionId = sectionQuestionId;
	}

	public List<Section> getMasterSurveyQuestionSectionList() {
		return masterSurveyQuestionSectionList;
	}

	public void setMasterSurveyQuestionSectionList(List<Section> masterSurveyQuestionSectionList) {
		this.masterSurveyQuestionSectionList = masterSurveyQuestionSectionList;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public List<Validation> getValidationsList() {
		return validationsList;
	}

	public void setValidationsList(List<Validation> validationsList) {
		this.validationsList = validationsList;
	}

	public String getErrorSection() {
		return errorSection;
	}

	public void setErrorSection(String errorSection) {
		this.errorSection = errorSection;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getSurveyKey() {
		return surveyKey;
	}

	public void setSurveyKey(String surveyKey) {
		this.surveyKey = surveyKey;
	}

	public String getApproverEmailAddress() {
		return approverEmailAddress;
	}

	public void setApproverEmailAddress(String approverEmailAddress) {
		this.approverEmailAddress = approverEmailAddress;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public boolean isReadOnlyMode() {
		return readOnlyMode;
	}

	public void setReadOnlyMode(boolean readOnlyMode) {
		this.readOnlyMode = readOnlyMode;
	}

	public Map<String, String> getApproversMap() {
		return approversMap;
	}

	public void setApproversMap(Map<String, String> approversMap) {
		this.approversMap = approversMap;
	}

	public String getCancelId() {
		return cancelId;
	}

	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}

}
