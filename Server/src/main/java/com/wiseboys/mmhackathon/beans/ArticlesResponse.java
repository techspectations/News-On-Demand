/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.util.List;

/**
 * @author Irudaya Raj
 * Nov 11, 2016 2016
 */
public class ArticlesResponse {
	private List<ManoramaArticle> articles;
	
	public ArticlesResponse(){};
	/**
	 * 
	 */
	public ArticlesResponse(List<ManoramaArticle> articles) {
		this.articles = articles;
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
