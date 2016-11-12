/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

/**
 * @author rajkl
 * Bean class to hold manorama section details. Will be used for mapping mysql db table using hibernate
 */
@Entity
@Table(name="tbl_manorama_section")
public class ManoramaSection {

	@Id
	@GeneratedValue
	private BigInteger id; // Auto generated value in database
	
	private String code = "";
	private String name = "";
	private String title = "";
	private String[] type = {};
	private String parentCode = "";
	private Integer sectionCount;
	private Integer articleCount;
	private String apiUrl = "";
	@Transient
	private List<ManoramaSection> sections;
	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return StringUtils.arrayToCommaDelimitedString(type);
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String[] type) {
		this.type = type;
	}
	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}
	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * @return the sectionCount
	 */
	public Integer getSectionCount() {
		return sectionCount;
	}
	/**
	 * @param sectionCount the sectionCount to set
	 */
	public void setSectionCount(Integer sectionCount) {
		this.sectionCount = sectionCount;
	}
	/**
	 * @return the articleCount
	 */
	public Integer getArticleCount() {
		return articleCount;
	}
	/**
	 * @param articleCount the articleCount to set
	 */
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	/**
	 * @return the apiUrl
	 */
	public String getApiUrl() {
		return apiUrl;
	}
	/**
	 * @param apiUrl the apiUrl to set
	 */
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
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
