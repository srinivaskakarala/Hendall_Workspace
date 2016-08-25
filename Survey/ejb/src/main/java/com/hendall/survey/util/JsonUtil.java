package com.hendall.survey.util;

import java.io.File;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendall.survey.services.schematopojo.ClassName;
import com.hendall.survey.services.schematopojo.Help;
import com.hendall.survey.services.schematopojo.HelpSectionLink;
import com.hendall.survey.services.schematopojo.Question;
import com.hendall.survey.services.schematopojo.Section;

public class JsonUtil {
	public static ClassName readFromFile(InputStream is) {
		ClassName className = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			// objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			className = objectMapper.readValue(is, ClassName.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return className;
	}

	public static void main(String[] args) {
		try {
			String path = "C:/Users/Kalyan Pamula/OneDrive/Hendall_Workspace/Survey/ejb/src/main/java/Hospital_Infection_Control_Worksheet_v0_14a.json";

			

			ObjectMapper objectMapper = new ObjectMapper();
			File f = new File(path);
			ClassName className = objectMapper.readValue(f, ClassName.class);

			System.out.println(className);
			for (Section section : className.getSection()) {
				for (Question question : section.getQuestion()) {
					System.out.println(question.getQuestionID()+"   "+question.getQuestionText() + "   " + question.getFileUpload() + "  "
							+ question.getHtmlControl());
				}

			}

			System.out.println("\n\n\n------------------------------------------\n\n\n");
			for (Help help : className.getHelp()) {
				for (HelpSectionLink link : help.getHelpSectionLink()) {
					System.out.println(help.getHelpSectionName() + "===========" + link.getHelpLinkName() + "======"
							+ link.getHelpLinkURL());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
