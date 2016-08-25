package com.hendall.survey.ui.assemblers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.hendall.survey.services.common.ServiceConstants;
import com.hendall.survey.services.datamodel.Section;
import com.hendall.survey.services.datamodel.SectionHelpWrapper;
import com.hendall.survey.services.schematopojo.Help;
import com.hendall.survey.services.schematopojo.HelpSectionLink;
import com.hendall.survey.ui.constants.UIConstants;

public class MenuModelAssembler {
	private static final String SECTION = "Section";
	private static final String FORM_ID = "surveryQuestionForm";
	private static final String QUESTIONS_PANEL_GIRD = "surveyQuestionPanldGrid";
	private static final String NEXT_PREV_BUTTON_GIRD_HEADER = "buttonsGrid_header";
	private static final String NEXT_PREV_BUTTON_GIRD_FOOTER = "buttonsGrid_footer";
	private Map<String, Integer> pageIndexMap = new LinkedHashMap<String, Integer>();

	public void assembleMenuModel(MenuModel menuModel, SectionHelpWrapper sectionHelpWrapper) {

		DefaultMenuItem homeMenuItem = new DefaultMenuItem(UIConstants.SUBMENU_HOME);
		homeMenuItem.setUrl(UIConstants.SUBMENU_HOME_URL);
		menuModel.addElement(homeMenuItem);

		DefaultSubMenu subMenuJump = new DefaultSubMenu(UIConstants.SUBMENU_JUMP_TO_SECTION);
		if (sectionHelpWrapper != null && CollectionUtils.isNotEmpty(sectionHelpWrapper.getSections())) {
			populateJumpToSections(subMenuJump, sectionHelpWrapper.getSections());
			menuModel.addElement(subMenuJump);
		}

		if (sectionHelpWrapper != null && CollectionUtils.isNotEmpty(sectionHelpWrapper.getHelp())) {
			DefaultSubMenu subMenuHelp = new DefaultSubMenu(UIConstants.SUBMENU_HELP);
			populateHelpMenu(subMenuHelp, sectionHelpWrapper.getHelp());
			menuModel.addElement(subMenuHelp);
		}

		if (sectionHelpWrapper != null) {
			DefaultSubMenu subMenuAdmin = new DefaultSubMenu(UIConstants.SUBMENU_ADMIN);
			menuModel.addElement(subMenuAdmin);
		}

		DefaultMenuItem logoutItem = new DefaultMenuItem(UIConstants.SUBMENU_LOGOUT);
		logoutItem.setCommand("#{" + UIConstants.LOGIN_BEAN + "." + UIConstants.SUBMENU_LOGOUT_COMMAND + "}");
		menuModel.addElement(logoutItem);

	}

	private void populateHelpMenu(DefaultSubMenu subMenuHelp, List<Help> helpList) {
		if (CollectionUtils.isEmpty(helpList))
			return;
		for (Help help : helpList) {
			DefaultSubMenu subMenu = new DefaultSubMenu(help.getHelpSectionName());
			for (HelpSectionLink helpSectonLink : help.getHelpSectionLink()) {
				DefaultMenuItem menuItem = new DefaultMenuItem(helpSectonLink.getHelpLinkName());
				menuItem.setTarget("_blank");
				if (helpSectonLink.getHelpLinkURL() != null && helpSectonLink.getHelpLinkURL().contains("http")) {
					menuItem.setUrl(helpSectonLink.getHelpLinkURL());

				} else {
					if (StringUtils.isNotBlank(helpSectonLink.getHelpLinkURL()))
						menuItem.setUrl("/" + ServiceConstants.HELP_PDF_FOLDER + "/" + helpSectonLink.getHelpLinkURL());
				}
				subMenu.addElement(menuItem);
			}
			subMenuHelp.addElement(subMenu);
		}

	}

	public void populateJumpToSections(DefaultSubMenu submenuJump, List<Section> sectionsList) {
		Map<String, List<DefaultMenuItem>> tmpMap = new LinkedHashMap<String, List<DefaultMenuItem>>();
		populatePageIndexMap(sectionsList, pageIndexMap);
		populateMapSectionMenuItems(tmpMap, sectionsList);
		for (String key : tmpMap.keySet()) {
			List<DefaultMenuItem> menuItemList = tmpMap.get(key);
			if (CollectionUtils.isNotEmpty(menuItemList)) {
				DefaultSubMenu submenuTmp = new DefaultSubMenu(key);
				submenuJump.addElement(submenuTmp);
				for (DefaultMenuItem defaultMenuItem : menuItemList) {
					populateMenuItemAttributes(defaultMenuItem);
					submenuTmp.addElement(defaultMenuItem);

				}
			} else {
				DefaultMenuItem menuItem = new DefaultMenuItem(key);
				populateMenuItemAttributes(menuItem);
				submenuJump.addElement(menuItem);

			}
		}
	}

	private void populateMenuItemAttributes(DefaultMenuItem menuItem) {
		menuItem.setParam(UIConstants.SUBMENU_JUMP_TO_SECTION, pageIndexMap.get(menuItem.getValue()) + 1);
		menuItem.setUpdate(":"+FORM_ID+":"+QUESTIONS_PANEL_GIRD+", "+":"+FORM_ID+":"+NEXT_PREV_BUTTON_GIRD_HEADER+", "+":"+FORM_ID+":"+NEXT_PREV_BUTTON_GIRD_FOOTER);
		menuItem.setCommand(
				"#{" + UIConstants.SURVEY_QUESTIONS_BEAN + "." + UIConstants.SUBMENU_JUMP_TO_SECTION_COMMAND + "}");
		menuItem.setOncomplete("$('.ui-layout-unit-content').animate({scrollTop: 10}, 0);");
		//menuItem.setStyle("width:100%");
	}

	private void populatePageIndexMap(List<Section> sectionsList, Map<String, Integer> pageIndexMap) {
		for (int k = 0; k < sectionsList.size(); k++) {
			Section section = sectionsList.get(k);
			pageIndexMap.put(section.getSectionTitle(), k);

		}

	}

	private void populateMapSectionMenuItems(Map<String, List<DefaultMenuItem>> tmpMap, List<Section> sectionsList) {

		for (Section section : sectionsList) {
			if (!section.getSectionTitle().contains(SECTION)) {
				List<DefaultMenuItem> tmpList = new ArrayList<DefaultMenuItem>();
				tmpMap.put(section.getSectionTitle(), tmpList);

			} else {
				String key = section.getSectionTitle().substring(0, 9);
				DefaultMenuItem menuItem = new DefaultMenuItem(section.getSectionTitle());
				if (tmpMap.containsKey(key)) {
					tmpMap.get(key).add(menuItem);

				} else {
					tmpMap.put(key, new ArrayList<DefaultMenuItem>());
					tmpMap.get(key).add(menuItem);
				}
			}

		}
	}

}
