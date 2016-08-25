package com.hendall.surveyrest.datamodel;

import java.io.Serializable;

import com.hendall.surveyrest.entities.ProvidersLu;

public class ProvidersData implements Serializable {

	private ProvidersLu providersLu = new ProvidersLu();
	private boolean hasSurveys;

	public ProvidersLu getProvidersLu() {
	/*	if(providersLu==null)
			providersLu = new ProvidersLu();*/
		return providersLu;
	}

	public void setProvidersLu(ProvidersLu providersLu) {
		this.providersLu = providersLu;
	}

	public boolean isHasSurveys() {
		return hasSurveys;
	}

	public void setHasSurveys(boolean hasSurveys) {
		this.hasSurveys = hasSurveys;
	}
	public String getAddress(){
		if(providersLu==null)
			return "";
		return providersLu.getStreetAddress()+" <br/>"+ providersLu.getCity()+", "+providersLu.getStatesLu().getStateCode()+" "+providersLu.getZipCode();
	}
}
