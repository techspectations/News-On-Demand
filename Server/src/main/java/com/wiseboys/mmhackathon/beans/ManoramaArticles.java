/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.util.List;

/**
 * Bean class to hold article details
 * @author rajkl
 *
 */
public class ManoramaArticles {	
	private String code = "";
	private String title = "";
	private String[] type = {};
	private int articlesCount = 0;
	private int page = 0;
	private int totalPages = 0;
	private int count = 0;
	private List<ManoramaArticle> articleSummary;
	private List<ManoramaCode> subSections;
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
	public String[] getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String[] type) {
		this.type = type;
	}
	/**
	 * @return the articlesCount
	 */
	public int getArticlesCount() {
		return articlesCount;
	}
	/**
	 * @param articlesCount the articlesCount to set
	 */
	public void setArticlesCount(int articlesCount) {
		this.articlesCount = articlesCount;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}
	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the articleSummary
	 */
	public List<ManoramaArticle> getArticleSummary() {
		return articleSummary;
	}
	/**
	 * @param articleSummary the articleSummary to set
	 */
	public void setArticleSummary(List<ManoramaArticle> articleSummary) {
		this.articleSummary = articleSummary;
	}
	/**
	 * @return the subSections
	 */
	public List<ManoramaCode> getSubSections() {
		return subSections;
	}
	/**
	 * @param subSections the subSections to set
	 */
	public void setSubSections(List<ManoramaCode> subSections) {
		this.subSections = subSections;
	}			
}
