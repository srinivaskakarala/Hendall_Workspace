package com.hendall.surveyrest.datamodel;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.hendall.surveyrest.common.ServiceConstants;
import com.hendall.surveyrest.util.Comparators;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4011894524068974035L;
	private Integer questionId;
	private String questionText;
	private boolean renderAddObservation;
	private boolean renderQuestion = true;
	private boolean disableAddObservation = false;
	private String citableTagName;
	private String citableTagURL;
	private Validation validation;
	private List<Answer> answersList;
	private boolean hasErrors=false;
	private List<File> fileNames;
	private int obsevationNumber;
	private List<DifferentUserAnswer> differentUserAnswerList;

	public void populateObservationNumbers() {
		//RederReove button is should be set for both radio and text area.
		if (CollectionUtils.isNotEmpty(answersList) && renderAddObservation) {
			ArrayList<Answer> tmp = new ArrayList<Answer>();
			tmp.add(answersList.get(0));
			tmp.add(answersList.get(1));
			tmp.get(0).setObservationNumber(2);
			tmp.get(1).setObservationNumber(1);
			answersList.remove(0);// Remove first 2 elements in the list.
			answersList.remove(0);
			//Collections.sort(answersList, Comparators.COMPONENTS_ORDER_BY_ID);
			Iterator<Answer> iterator = answersList.iterator();
			int counter = 4;
			while (iterator.hasNext()) {
				Answer answer = iterator.next();
				if (answer.isRenderRemoveButton()) {
					answer.setObservationNumber(counter);
					tmp.add(answer);
					counter=counter+1;
					iterator.remove();
				}
			}
			iterator = answersList.iterator();
			while (iterator.hasNext()) {
				Answer answer = iterator.next();
				if (!answer.isRenderRemoveButton() && answer.getObservationNumber() != 1
						&& answer.getObservationNumber() != 2) {
					answer.setObservationNumber(counter);
					tmp.add(answer);
					counter=counter+1;
					iterator.remove();
				}
			}
			answersList = tmp;
		//	Collections.sort(answersList, Comparators.COMPONENTS_ORDER_BY_OBESRVATON_NUMBER);
		}

	}
	public void removeObservationOne(){
		populateObservationNumbers();
		if (CollectionUtils.isNotEmpty(answersList) && renderAddObservation) {
			Answer firstAnswer = answersList.get(0);
			for(int nextIndex=2;nextIndex<answersList.size();nextIndex=nextIndex+2){
				Answer secondAnswer =answersList.get(nextIndex);
				firstAnswer.setAnswer(secondAnswer.getAnswer());
				firstAnswer=secondAnswer;
				
			}
			 firstAnswer = answersList.get(1);
			for(int nextIndex=3;nextIndex<answersList.size();nextIndex=nextIndex+2){
				Answer secondAnswer =answersList.get(nextIndex);
				firstAnswer.setAnswer(secondAnswer.getAnswer());
				firstAnswer=secondAnswer;
				
			}
		}
	}

	public int getObsevationNumber() {
		int numberOfObservations = 0;
		if (CollectionUtils.isNotEmpty(answersList)) {
			for (Answer answer : answersList) {
				if (answer.isDefaultVisible()
						&& answer.getHtmlControlType().equals(ServiceConstants.HTML_CONTROL_TEXT_AREA)) {
					numberOfObservations++;
				}
			}
			return numberOfObservations + 1;
		}
		return 2;

	}
	
	public void setObsevationNumber(int obsevationNumber) {
		this.obsevationNumber = obsevationNumber;
	}

	public String getCitableTagName() {
		return citableTagName;
	}

	public void setCitableTagName(String citableTagName) {
		this.citableTagName = citableTagName;
	}

	public String getCitableTagURL() {
		return citableTagURL;
	}

	public void setCitableTagURL(String citableTagURL) {
		this.citableTagURL = citableTagURL;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<Answer> getAnswersList() {
		if (answersList == null) {
			answersList = new ArrayList<Answer>();
		}
		return answersList;
	}

	public void setAnswersList(List<Answer> answersList) {
		this.answersList = answersList;
	}

	public boolean isRenderAddObservation() {
		return renderAddObservation;
	}

	public void setRenderAddObservation(boolean renderAddObservation) {
		this.renderAddObservation = renderAddObservation;
	}

	public boolean isDisableAddObservation() {
		return disableAddObservation;
	}

	public void setDisableAddObservation(boolean disableAddObservation) {
		this.disableAddObservation = disableAddObservation;
	}

	public boolean isRenderQuestion() {
		return renderQuestion;
	}

	public void setRenderQuestion(boolean renderQuestion) {
		this.renderQuestion = renderQuestion;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}
	public boolean isHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	public List<File> getFileNames() {
		if(fileNames==null)
			fileNames =new ArrayList<File>();
		return fileNames;
	}
	public void setFileNames(List<File> fileNames) {
		this.fileNames = fileNames;
	}
	public List<DifferentUserAnswer> getDifferentUserAnswerList() {
		return differentUserAnswerList;
	}
	public void setDifferentUserAnswerList(List<DifferentUserAnswer> differentUserAnswerList) {
		this.differentUserAnswerList = differentUserAnswerList;
	}
	
	
}
