package com.hendall.surveyrest.helpers;

import java.io.InputStream;
import java.util.logging.Logger;

import com.hendall.survey.schematopojo.ClassName;
import com.hendall.surveyrest.assemblers.SurveyQuestionsFormAssembler;
import com.hendall.surveyrest.datamodel.SectionHelpWrapper;
import com.hendall.surveyrest.util.JsonUtil;

public class QuestionsHelper {
	private static final Logger log = Logger.getLogger(QuestionsHelper.class.getName());
	private static final String file ="Hospital_Infection_Control_Worksheet_v0_22a.json";
	

	public QuestionsHelper() { 	

	}
	

	public SectionHelpWrapper getQuestions() {
		
		InputStream is = QuestionsHelper.class.getClassLoader()
				.getResourceAsStream("/" +file);
		SectionHelpWrapper sectionHelpWrapperList = new SectionHelpWrapper();
		ClassName className = JsonUtil.readFromFile(is);
		SurveyQuestionsFormAssembler.assebleViewObject(className, sectionHelpWrapperList);
		return sectionHelpWrapperList;

	}


}
