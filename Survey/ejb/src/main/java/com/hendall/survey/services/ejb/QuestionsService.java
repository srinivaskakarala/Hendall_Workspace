package com.hendall.survey.services.ejb;

import javax.ejb.Stateless;

import com.hendall.survey.services.assemblers.SurveyQuestionsFormAssembler;
import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.SectionHelpWrapper;
import com.hendall.survey.services.schematopojo.ClassName;
import com.hendall.survey.util.JsonUtil;
import java.util.logging.Logger;
import java.io.*;

/**
 * Session Bean implementation class QuestionsService
 */
@Stateless

public class QuestionsService {

	/**
	 * Default constructor.
	 */

	private static final Logger log = Logger.getLogger(QuestionsService.class.getName());

	public QuestionsService() {

	}

	public SectionHelpWrapper getQuestions() {
		SectionHelpWrapper sectionHelpWrapperList = new SectionHelpWrapper();
		InputStream is = QuestionsService.class.getClassLoader()
				.getResourceAsStream("/" + ServiceConstants.INFECTION_CONTROL_FILE_NAME);
		ClassName className = JsonUtil.readFromFile(is);
		SurveyQuestionsFormAssembler.assebleViewObject(className, sectionHelpWrapperList);
		return sectionHelpWrapperList;

	}

	public int getNumberOfSectionsPerPage(String client) {
		return 1;
	}

}
