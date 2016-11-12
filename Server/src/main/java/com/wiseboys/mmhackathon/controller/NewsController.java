/**
 * 
 */
package com.wiseboys.mmhackathon.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiseboys.mmhackathon.beans.ArticlesResponse;
import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.beans.ManoramaArticleDetails;
import com.wiseboys.mmhackathon.serviceimpl.NewsServiceImpl;
import com.wiseboys.mmhackathon.utils.Constants;

/**
 * All news related API request is handled in this class
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
@Controller
public class NewsController {

	/**
	 * Inject {@link NewsServiceImpl}
	 */
	@Autowired
	private NewsServiceImpl _newsService;
	
	// Declaring API end points
	private final static String ALL_ARTICLES = Constants.NEWS_API_BASE_PATH + "/articles/{userId}";
	private final static String LOCATION_ARTICLE = Constants.NEWS_API_BASE_PATH + "/{payload}";
	private final static String ARTICLE_DETAILS = Constants.NEWS_API_BASE_PATH + "/article/{articleId}";
	
	/**
	 * Getting all available articles
	 * @return All articles
	 */
	@RequestMapping(value=ALL_ARTICLES, method = RequestMethod.GET, produces = Constants.RESPONSE_DATA_TYPE)
	public @ResponseBody ArticlesResponse getArticlesByUser(@PathVariable BigInteger userId){
		List<ManoramaArticle> articles = _newsService.getArticlesByUserData(userId);
		if(articles != null)
			return new ArticlesResponse(articles);
		else
			return new ArticlesResponse(new ArrayList<ManoramaArticle>());			
	}
	
	/**
	 * Getting all available articles based on the input location string
	 * Clients should pass latestnews string to get all latest news
	 * @return All articles
	 */
	@RequestMapping(value=LOCATION_ARTICLE, method = RequestMethod.GET, produces = Constants.RESPONSE_DATA_TYPE)
	public @ResponseBody ArticlesResponse getArticlesByLocation(@PathVariable String payload){
		List<ManoramaArticle> articles =  new ArrayList<ManoramaArticle>();
		try {
			if(payload != null) {								
				if(payload.toLowerCase().equals("latestnews")) {					
					articles = _newsService.getAllLatestNews();
					if(articles != null)
						return new ArticlesResponse(articles);
					else
						return new ArticlesResponse(new ArrayList<ManoramaArticle>());	
				} else { // REST Client passed location details
					articles = _newsService.getArticlesByLocation(payload.trim());
					if(articles != null)
						return new ArticlesResponse(articles);
					else
						return new ArticlesResponse(new ArrayList<ManoramaArticle>());
				}
			} 
		} catch(Exception exception) {
			System.out.println("Exception in getArticlesByLocation" + exception.getMessage());
		}	
		return new ArticlesResponse();		
	}
	
	/**
	 * Getting article details
	 * @return All articles
	 */
	@RequestMapping(value=ARTICLE_DETAILS, method = RequestMethod.GET, produces = Constants.RESPONSE_DATA_TYPE)
	public @ResponseBody ManoramaArticleDetails getArticlesDetails(@PathVariable String articleId){
		return _newsService.getArticleDetails(articleId);
	}
	
}
