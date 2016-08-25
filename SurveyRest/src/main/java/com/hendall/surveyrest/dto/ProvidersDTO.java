package com.hendall.surveyrest.dto;

import java.io.Serializable;

public class ProvidersDTO implements Serializable {
	
	private Integer providerKey;
	private String ccn;
	private String facilityName;
	
	public Integer getProviderKey() {
		return providerKey;
	}
	public void setProviderKey(Integer providerKey) {
		this.providerKey = providerKey;
	}
	public String getCcn() {
		return ccn;
	}
	public void setCcn(String ccn) {
		this.ccn = ccn;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

}
