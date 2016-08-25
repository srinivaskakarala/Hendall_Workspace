package com.hendall.survey.ui.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class WebApplicationUtil {
	
	public static void setSessionAttribute(String key, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, object);
	}
	public static Object getRequestParam(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}

	public static Object getSessionAttribute(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}
	public static void setRequestAttribute(String key, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(key, object);
	}

	public static Object getRequestAttribute(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(key);
	}

	public static void addGlobalMessage(String message, FacesMessage.Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, message));
	}

	public static void addMessage(String id, String message, FacesMessage.Severity severity) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(severity, message, message));
	}
	public static Object getFlastAttribute(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}
	public static void setFlashAttribute(String key, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, object);
	}

}
