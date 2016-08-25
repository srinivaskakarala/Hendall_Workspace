package com.hendall.survey.ui.backingbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.hendall.survey.services.datamodel.UserSession;
import com.hendall.survey.services.ejb.UsersService;
import com.hendall.survey.ui.constants.UIConstants;
import com.hendall.survey.ui.util.WebApplicationUtil;

@ManagedBean(name = UIConstants.LOGIN_BEAN)
@ViewScoped
public class LoginBean implements Serializable {

	private String userName;
	private String password;

	@EJB
	private UsersService usersService;

	public String loginPage() {
		UserSession userSession = usersService.getUsersSurveys(userName, password);
		if (userSession == null) {
			WebApplicationUtil.addGlobalMessage("Invalid user name or password", FacesMessage.SEVERITY_ERROR);
			return "";
		} else {
			WebApplicationUtil.setSessionAttribute(UIConstants.USER_SESSION_KEY, userSession);
			WebApplicationUtil.addGlobalMessage("Susccess", FacesMessage.SEVERITY_ERROR);
			return "/pages/viewMySurveys.jsf";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.jsf";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
