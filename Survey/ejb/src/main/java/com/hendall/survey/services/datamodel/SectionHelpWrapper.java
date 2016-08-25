package com.hendall.survey.services.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.hendall.survey.services.schematopojo.Help;

public class SectionHelpWrapper {

	private List<Section> sections;
	private List<Help> help;

	public List<Section> getSections() {
		if (sections == null)
			sections = new ArrayList<Section>();
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Help> getHelp() {
		if (help == null)
			help = new ArrayList<Help>();
		return help;
	}

	public void setHelp(List<Help> help) {
		this.help = help;
	}

}
