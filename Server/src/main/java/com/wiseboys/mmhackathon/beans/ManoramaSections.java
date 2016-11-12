/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.util.List;

/**
 * @author rajkl
 * Bean class to hold manorama section details. Will be used for mapping mysql db table using hibernate
 */
public class ManoramaSections {
	private List<ManoramaSection> sections;

	/**
	 * @return the sections
	 */
	public List<ManoramaSection> getSections() {
		return sections;
	}

	/**
	 * @param sections the sections to set
	 */
	public void setSections(List<ManoramaSection> sections) {
		this.sections = sections;
	}	
}
