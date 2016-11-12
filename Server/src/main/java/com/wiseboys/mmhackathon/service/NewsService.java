/**
 * 
 */
package com.wiseboys.mmhackathon.service;

import java.math.BigInteger;
import java.util.List;

import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.beans.ManoramaArticleDetails;
import com.wiseboys.mmhackathon.serviceimpl.NewsServiceImpl;

/**
 * All news related business logics are declared in this class. {@link NewsServiceImpl}
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
public interface NewsService {
	public List<ManoramaArticle> getArticlesByUserData(BigInteger userId);
	public List<ManoramaArticle> getArticlesByLocation(String location);
	public List<ManoramaArticle> getAllLatestNews();
	public ManoramaArticleDetails getArticleDetails(String articleId);
}
