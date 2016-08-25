package com.hendall.survey.services.common;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ServiceConstants {
	private ServiceConstants() {
	}

	public static String INFECTION_CONTROL_FILE_NAME = "Hospital_Infection_Control_Worksheet_v0_14a.json";
	public static String HELP_PDF_FOLDER = "helppdf";
	public static String PROPERTIES_FOLDER = "survey.folderpath";
	public static String PROPERTIES_FILE = "survey.jsonfile";

	public static final String UNABLE_TO_OBSERVE = "Unable to observe";
	public static final String NA = "N/A";

	public static String STATUS_REJECT = "Rejected";
	public static String STATUS_APPROVE = "Approved";
	public static String STATUS_IN_PROGRESS = "In Progress";
	public static String STATUS_IN_PENDING_REVIEW = "Pending Review";
	public static String STATUS_IN_SUBMITTED = "Submitted";

	public static String EMAIL_FROM_ADDRESS = "no-reply-specialty-survey@hendall.com";

	public static String EMAIL_SUBJECT = "New Survey Review pending";
	public static String EMAIL_MESSAGE = "New survey has been assigned to you. Please login to your account.";

	public static Integer INFECTION_CONTROL_STATE_CONTROL_ID = 100;
	public static Integer INFECTION_CONTROL_HOSPITAL_NAME_CONTROL_ID = 200;
	public static Integer INFECTION_CONTROL_HOSPITAL_CCN_ID = 300;
	public static final Integer INFECTION_CONTROL_ADDTIONAL_COMMENTS_ID = -1;
	public static final String INFECTION_CONTROL_ADDTIONAL_COMMENTS_QUESTION = "You can use this section to include any additional comments or observations you may have, regarding this survey.";
	public static final String INFECTION_CONTROL_ADDTIONAL_COMMENTS_SECTION_TITLE = "Survey Notes";

	public static final Integer INFECTION_CONTROL_APPROVER_ID = -2;
	public static final String INFECTION_CONTROL_ADDTIONAL_APPROVER_DETAILS_SECTION_TITLE = "Survey Approver Details";
	public static final String INFECTION_CONTROL_ADDTIONAL_APPROVER_DETAILS_QUESTION = "Please enter the email address of your supervisor in this section. \n Upon submission of the survey, an email will be sent to your supervisor, for their appproval.";
	public static final String INFECTION_CONTROL_ADDTIONAL_APPROVER_EMAIL = "Supervisor Email Address:";

	public static final String DATA_SAPERATOR = "\\|\\|";

	public static final String HTML_CONTROL_RADIO = "radio";
	public static final String HTML_CONTROL_CHECK_BOX = "checkbox";
	public static final String HTML_CONTROL_SELECT = "select";
	public static final String HTML_CONTROL_TEXT_BOX = "textbox";
	public static final String HTML_CONTROL_DATE = "date";
	public static final String HTML_CONTROL_NUMBER = "number";
	public static final String HTML_CONTROL_TEXT_AREA = "textarea";
	public static final List<String> inputTextComponents = new ArrayList<String>();
	public static final List<String> singleValueCompoents = new ArrayList<String>();
	public static final List<String> multipleSelectionComponents = new ArrayList<String>();

	public static final String REQUEIRED_MESSAGE = "Required.";
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_123710="No observations available (If selected, ALL questions from 4.A.3 – 4.A.6 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_123710="Second observation not available (If selected, questions 4.A.3 – 4.A.6 RIGHT column will be blocked)";
	public static List<Integer> LIST_4A3_TO_4A6 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4A3_TO_4A6_ID=123710;
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_134710="No observations available (If selected, ALL questions from 4.B.2 – 4.B.7 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_134710="Second observation not available (If selected, questions 4.B.2 – 4.B.7 RIGHT column will be blocked)";
	public static List<Integer> LIST_4B2_TO_4B7 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4B2_TO_4B7_ID=134710;
																				
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_88600="Yes - Stop here and skip to Section 3.B";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_88600="No - Answer all questions in this section";
	public static List<Integer> LIST_3A6_TO_3A18 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_3A6_TO_3A18_ID=88600;
	
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_141710="No observations available (If selected, ALL questions from 4.B.9 – 4.B.13 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_141710="Second observation not available (If selected, questions 4.B.9 – 4.B.13 RIGHT column will be blocked)";
	public static List<Integer> LIST_4B9_TO_413 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4B9_TO_413_ID=141710;
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_147710="No observations available (If selected, ALL questions from 4.C.1 – 4.C.8 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_147710="Second observation not available (If selected, questions 4.C.1 – 4.C.8 RIGHT column will be blocked)";
	public static List<Integer> LIST_4C1_TO_4C8 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4C1_TO_4C8_ID=147710;
	
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_155710="No observations available (If selected, ALL questions from 4.C.9 – 4.C.15 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_155710="Second observation not available (If selected, questions 4.C.9 – 4.C.15 RIGHT column will be blocked)";
	public static List<Integer> LIST_4C9_TO_4C15 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4C9_TO_4C15_ID=155710;
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_162800="Yes – Answer questions 4.D.1 – 4.D.3";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_162800="No – Questions 4.D.1 – 4.D.3 will be blocked";
	public static List<Integer> LIST_4D1_TO_4D3 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4D1_TO_4D3_ID=162800;
	
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_206900="Yes - Answer questions 4.I.9 – 4.I.17";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_206900="No - Questions 4.I.9 – 4.I.17 will be blocked";
	public static List<Integer> LIST_4I9_TO_4I17= new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4I9_TO_4I17_ID=206900;
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_104700="Yes: Stop here and skip to Section 3.C";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_104700="No: Answer all questions in this section";
	public static List<Integer> LIST_3B5_TO_3B19 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_3B5_TO_3B19_ID=104700;
	
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_182950="No observation available (If selected, ALL questions from 4.G.2-4.G.9 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_182950="Second observation not available (If selected, questions 4.G.2 – 4.G.9 RIGHT column will be blocked)";
	public static List<Integer> LIST_4G2_TO_4G9 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4G2_TO_4G9_ID=182950;
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_19150="No observation available (If selected, ALL questions from 4.H.2-4.H.8 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_191950="Second observation not available (If selected, questions 4.H.2 – 4.H.8 RIGHT column will be blocked)";
	public static List<Integer> LIST_4H2_TO_4H8 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4H2_TO_4H8_ID=191950;
	
	public static final String INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_198950="No observation available (If selected, ALL questions from 4.I.1-4.I.8 will be blocked)";
	public static final String INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_198950="Second observation not available (If selected, questions 4.I.1 – 4.I.8 RIGHT column will be blocked)";
	public static List<Integer> LIST_4I1_TO_4I8 = new ArrayList<Integer>();
	public static Integer INFECTION_CONTROL_SKIP_4I1_TO_4I8_ID=198950;
	
	
	public static List<String> INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST = new ArrayList<String>();
	public static List<String> INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST = new ArrayList<String>();
	public static Map<Integer, List<Integer>> infectionControlDepenentComponents = new HashMap<Integer, List<Integer>>();
	public static List<Integer> INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST = new ArrayList<Integer>();

	static {
		inputTextComponents.add(HTML_CONTROL_TEXT_BOX);
		inputTextComponents.add(HTML_CONTROL_DATE);
		inputTextComponents.add(HTML_CONTROL_NUMBER);
		inputTextComponents.add(HTML_CONTROL_TEXT_AREA);

		singleValueCompoents.add(HTML_CONTROL_TEXT_BOX);
		singleValueCompoents.add(HTML_CONTROL_DATE);
		singleValueCompoents.add(HTML_CONTROL_NUMBER);
		singleValueCompoents.add(HTML_CONTROL_TEXT_AREA);
		singleValueCompoents.add(HTML_CONTROL_RADIO);
		singleValueCompoents.add(HTML_CONTROL_SELECT);

		multipleSelectionComponents.add(HTML_CONTROL_RADIO);
		multipleSelectionComponents.add(HTML_CONTROL_CHECK_BOX);
		multipleSelectionComponents.add(HTML_CONTROL_SELECT);

		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_123710);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_134710);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_88600);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_141710);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_147710);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_155710);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_162800);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_206900);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_162800);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_206900);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_104700);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_182950);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_19150);
		INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_RENDER_TRUE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_198950);
		

		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_123710);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_134710);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_88600);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_141710);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_147710);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_155710);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_162800);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_NO_OBSERVATION_AVALIABLE_206900);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_104700);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_182950);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_191950);
		INFECTION_CONTROL_SECOND_OBSERVATION_AVALIABLE_RENDER_FALSE_LIST.add(INFECTION_CONTROL_SECON_OBSERVATION_NOT_AVALIABLE_198950);
		
		
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4A3_TO_4A6_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4B2_TO_4B7_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_3A6_TO_3A18_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4B9_TO_413_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4C1_TO_4C8_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4C9_TO_4C15_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4D1_TO_4D3_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4I9_TO_4I17_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_3B5_TO_3B19_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4G2_TO_4G9_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4H2_TO_4H8_ID);
		INFECTION_CONTROL_NO_SECOND_OBSERVATION_ID_LIST.add(INFECTION_CONTROL_SKIP_4I1_TO_4I8_ID);		
		
		LIST_4A3_TO_4A6.add(14500);
		LIST_4A3_TO_4A6.add(14600);
		LIST_4A3_TO_4A6.add(14700);
		LIST_4A3_TO_4A6.add(14800);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4A3_TO_4A6_ID, LIST_4A3_TO_4A6);
		
		
		LIST_4B2_TO_4B7.add(15800);
		LIST_4B2_TO_4B7.add(15900);
		LIST_4B2_TO_4B7.add(16000);
		LIST_4B2_TO_4B7.add(16100);
		LIST_4B2_TO_4B7.add(16200);
		LIST_4B2_TO_4B7.add(16300);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4B2_TO_4B7_ID, LIST_4B2_TO_4B7);
		
		
		LIST_3A6_TO_3A18.add(10300);
		LIST_3A6_TO_3A18.add(10400);
		LIST_3A6_TO_3A18.add(10500);
		LIST_3A6_TO_3A18.add(10600);
		LIST_3A6_TO_3A18.add(10700);
		LIST_3A6_TO_3A18.add(10800);
		LIST_3A6_TO_3A18.add(10900);
		LIST_3A6_TO_3A18.add(11000);
		LIST_3A6_TO_3A18.add(11100);
		LIST_3A6_TO_3A18.add(11200);
		LIST_3A6_TO_3A18.add(11300);
		LIST_3A6_TO_3A18.add(11400);
		LIST_3A6_TO_3A18.add(11500);
		LIST_3A6_TO_3A18.add(11600);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_3A6_TO_3A18_ID,LIST_3A6_TO_3A18);		
		
		
		LIST_4B9_TO_413.add(16600);
		LIST_4B9_TO_413.add(16700);
		LIST_4B9_TO_413.add(16800);
		LIST_4B9_TO_413.add(16900);
		LIST_4B9_TO_413.add(17000);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4B9_TO_413_ID,LIST_4B9_TO_413);	
		
		
		LIST_4C1_TO_4C8.add(17400);
		LIST_4C1_TO_4C8.add(17500);
		LIST_4C1_TO_4C8.add(17600);
		LIST_4C1_TO_4C8.add(17700);
		LIST_4C1_TO_4C8.add(17800);
		LIST_4C1_TO_4C8.add(17900);
		LIST_4C1_TO_4C8.add(18000);
		LIST_4C1_TO_4C8.add(18100);
		LIST_4C1_TO_4C8.add(18200);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4C1_TO_4C8_ID,LIST_4C1_TO_4C8);	

		LIST_4C9_TO_4C15.add(18500);
		LIST_4C9_TO_4C15.add(18600);
		LIST_4C9_TO_4C15.add(18700);
		LIST_4C9_TO_4C15.add(18800);
		LIST_4C9_TO_4C15.add(18900);
		LIST_4C9_TO_4C15.add(19000);
		LIST_4C9_TO_4C15.add(19100);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4C9_TO_4C15_ID,LIST_4C9_TO_4C15);	
		
		LIST_4D1_TO_4D3.add(19400);
		LIST_4D1_TO_4D3.add(19500);
		LIST_4D1_TO_4D3.add(19600);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4D1_TO_4D3_ID,LIST_4D1_TO_4D3);	
		
		LIST_4I9_TO_4I17.add(24500);
		LIST_4I9_TO_4I17.add(24600);
		LIST_4I9_TO_4I17.add(24700);
		LIST_4I9_TO_4I17.add(24800);
		LIST_4I9_TO_4I17.add(24900);
		LIST_4I9_TO_4I17.add(25000);
		LIST_4I9_TO_4I17.add(25100);
		LIST_4I9_TO_4I17.add(25200);
		LIST_4I9_TO_4I17.add(25300);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4I9_TO_4I17_ID,LIST_4I9_TO_4I17);	

		LIST_3B5_TO_3B19.add(12100);
		LIST_3B5_TO_3B19.add(12200);
		LIST_3B5_TO_3B19.add(12300);
		LIST_3B5_TO_3B19.add(12400);
		LIST_3B5_TO_3B19.add(12500);
		LIST_3B5_TO_3B19.add(12600);
		LIST_3B5_TO_3B19.add(12700);
		LIST_3B5_TO_3B19.add(12800);
		LIST_3B5_TO_3B19.add(12900); 
		LIST_3B5_TO_3B19.add(13000);
		LIST_3B5_TO_3B19.add(13100);
		LIST_3B5_TO_3B19.add(13200);
		LIST_3B5_TO_3B19.add(13300);
		LIST_3B5_TO_3B19.add(13400);
		LIST_3B5_TO_3B19.add(13500);
		LIST_3B5_TO_3B19.add(13600);
		LIST_3B5_TO_3B19.add(13700);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_3B5_TO_3B19_ID,LIST_3B5_TO_3B19);	
		
		
		LIST_4G2_TO_4G9.add(21700);
		LIST_4G2_TO_4G9.add(21800);
		LIST_4G2_TO_4G9.add(21900);
		LIST_4G2_TO_4G9.add(22000);
		LIST_4G2_TO_4G9.add(22100);
		LIST_4G2_TO_4G9.add(22200);
		LIST_4G2_TO_4G9.add(22300);
		LIST_4G2_TO_4G9.add(22400);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4G2_TO_4G9_ID,LIST_4G2_TO_4G9);	

		LIST_4H2_TO_4H8.add(22700);
		LIST_4H2_TO_4H8.add(22800);
		LIST_4H2_TO_4H8.add(22900);
		LIST_4H2_TO_4H8.add(23000);
		LIST_4H2_TO_4H8.add(23100);
		LIST_4H2_TO_4H8.add(23200);
		LIST_4H2_TO_4H8.add(23300);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4H2_TO_4H8_ID, LIST_4H2_TO_4H8);
		
		LIST_4I1_TO_4I8.add(23500);
		LIST_4I1_TO_4I8.add(23600);
		LIST_4I1_TO_4I8.add(23700);
		LIST_4I1_TO_4I8.add(23800);
		LIST_4I1_TO_4I8.add(23900);
		LIST_4I1_TO_4I8.add(24000);
		LIST_4I1_TO_4I8.add(24100);
		LIST_4I1_TO_4I8.add(24200);
		infectionControlDepenentComponents.put(INFECTION_CONTROL_SKIP_4I1_TO_4I8_ID, LIST_4I1_TO_4I8);

	}

}
