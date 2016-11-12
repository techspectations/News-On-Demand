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
public class SearchArticlesResult {	
	private String searchType = "";
	private long articlesMatched = 0;
	private int page = 0;
	private int totalPages = 0;
	private int articlesCount = 0;	
	private List<ManoramaArticle> articles;
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return the articlesMatched
	 */
	public long getArticlesMatched() {
		return articlesMatched;
	}
	/**
	 * @param articlesMatched the articlesMatched to set
	 */
	public void setArticlesMatched(long articlesMatched) {
		this.articlesMatched = articlesMatched;
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
	 * @return the articles
	 */
	public List<ManoramaArticle> getArticles() {
		return articles;
	}
	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<ManoramaArticle> articles) {
		this.articles = articles;
	}
}	
