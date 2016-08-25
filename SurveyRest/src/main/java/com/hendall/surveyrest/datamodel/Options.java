package com.hendall.surveyrest.datamodel;

public class Options {

	private String value;
	private String key;
	
	public Options(){}

	public Options(String value, String key) {
		super();
		this.value = value;
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
