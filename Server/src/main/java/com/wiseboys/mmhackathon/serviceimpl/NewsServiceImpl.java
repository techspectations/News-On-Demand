/**
 * 
 */
package com.wiseboys.mmhackathon.serviceimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.beans.ManoramaArticleDetails;
import com.wiseboys.mmhackathon.beans.User;
import com.wiseboys.mmhackathon.beans.UserLike;
import com.wiseboys.mmhackathon.daoimpl.UserDAOImpl;
import com.wiseboys.mmhackathon.service.NewsService;
import com.wiseboys.mmhackathon.utils.ManoramaAPIsHelper;
import com.wiseboys.mmhackathon.utils.Utils;

/**
 * All news related business logics are implemented in this class. {@link NewsService}
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
@Service
public class NewsServiceImpl implements NewsService {
	
	private ManoramaAPIsHelper _mManoramaAPIsHelper = new  ManoramaAPIsHelper();
	
	/**
	 * Inject {@link UserDAOImpl}
	 */
	@Autowired	
	private UserDAOImpl _userDAO;
	
	
	/* Fetching articles based on user personal info and his likes
	 * We have to apply some rules on user personal details and get inferencing from it. So that 
	 * we can provide articles based on user 
	 * @see com.wiseboys.mmhackathon.service.NewsService#getArticlesByUserData()
	 */
	@Override
	public List<ManoramaArticle> getArticlesByUserData(BigInteger userId) {
		List<ManoramaArticle> articles = new ArrayList<ManoramaArticle>();
		try {
			User user = _userDAO.getUserById(userId);
			if(user != null) {
				List<UserLike> userLikes = _userDAO.getUserLikesByUserId(userId); 
				if(userLikes != null && userLikes.size() > 0) { // Get all news based based on user likes
					List<ManoramaArticle> tempArticles = searchNews(userLikes);
					if(tempArticles != null)
						articles.addAll(tempArticles);				
				}				
				if(articles.size() == 0) { // User does not have any interest so go ahead get all news based on location details				
					if(user != null && user.getLocation() != null && user.getLocation().trim().length() != 0) {
						String url = ManoramaAPIsHelper.SEARCH_ARTICLES 
											+ Utils.encodeToUtf8(user.getLocation().trim()) + "&size=" + ManoramaAPIsHelper.TOTAL_SEARCH_RESULTS;;
						List<ManoramaArticle> tempArticles = _mManoramaAPIsHelper.searchNews(url);
						if(tempArticles != null) {
							articles.addAll(tempArticles);
						}
					}
				}
				
				if(articles.size() == 0) { // User does not have any location saved so get all news based on his gender				
					if(user != null && user.getLocation() != null && user.getLocation().trim().length() != 0) {
						String gender = (user.getGender().trim().toLowerCase().equals("male"))? "Men latest news" : "Women latest news";
						String url = ManoramaAPIsHelper.SEARCH_ARTICLES 
														+ Utils.encodeToUtf8(gender)+ "&size=" + ManoramaAPIsHelper.TOTAL_SEARCH_RESULTS;;
						List<ManoramaArticle> tempArticles = _mManoramaAPIsHelper.searchNews(url);
						if(tempArticles != null) {
							articles.addAll(tempArticles);
						}
					}
				}
				
				if(articles.size() == 0) { // User does not have any interest so go ahead get all latest news
					String url = ManoramaAPIsHelper.SEARCH_ARTICLES + "latestnews" + "&size=" + ManoramaAPIsHelper.TOTAL_SEARCH_RESULTS;;
					List<ManoramaArticle> tempArticles = _mManoramaAPIsHelper.searchNews(url);
					if(tempArticles != null) {
						articles.addAll(tempArticles);
					}
				}
			}
		} catch(Exception exception) {
			System.out.println("Exception so give back all avaiable articles" +  exception.getLocalizedMessage());			
		}
		return articles;
	}
	
	/**
	 * Search news based on user likes, places and so on
	 * @param searchTerms
	 * @return List of articles
	 */
	private List<ManoramaArticle> searchNews(List<UserLike> searchTerms) {
		List<ManoramaArticle> articles = new ArrayList<ManoramaArticle>();
		try {
			for(UserLike searchTerm : searchTerms) {
				if(searchTerm != null && searchTerm.getLikeItem() != null) {
					String url = ManoramaAPIsHelper.SEARCH_ARTICLES 
														+ Utils.encodeToUtf8(searchTerm.getLikeItem().trim()) + "&size=" + ManoramaAPIsHelper.TOTAL_SEARCH_RESULTS;
					List<ManoramaArticle> tempArticles = _mManoramaAPIsHelper.searchNews(url);
					if(tempArticles != null) {
						articles.addAll(tempArticles);
					}
				}				
			}
		} catch(Exception exception) {
			System.out.println("Exception searchNews" + exception.getMessage());			
		}
		return articles;
	}

	/* Get articles based on the phone location
	 * @see com.wiseboys.mmhackathon.service.NewsService#getArticlesByLocation(java.lang.String)
	 */
	@Override
	public List<ManoramaArticle> getArticlesByLocation(String location) {
		try {
			String url = ManoramaAPIsHelper.SEARCH_ARTICLES 
					+ Utils.encodeToUtf8(location) + "&size=" + ManoramaAPIsHelper.TOTAL_SEARCH_RESULTS_LOCATION;;
			return _mManoramaAPIsHelper.searchNews(url);			
		} catch(Exception exception) {
			System.out.println("getArticlesByLocation Exception" + exception.getMessage());
		}
		return null;
	}

	/* 
	 * @see com.wiseboys.mmhackathon.service.NewsService#getAllLatestNews()
	 */
	@Override
	public List<ManoramaArticle> getAllLatestNews() {
		try {
			String url = ManoramaAPIsHelper.SEARCH_ARTICLES + "latest news" + "&size=" + ManoramaAPIsHelper.TOTAL_LATEST_NEWS_SIZE;;
			return _mManoramaAPIsHelper.searchNews(url);			
		} catch(Exception exception) {
			System.out.println("getAllLatestNews Exception" + exception.getMessage());
		}		
		return null;
	}

	/* 
	 * @see com.wiseboys.mmhackathon.service.NewsService#getArticleDetails(java.lang.String)
	 */
	@Override
	public ManoramaArticleDetails getArticleDetails(String articleId) {	
		try {			
			return _mManoramaAPIsHelper.getArticleDetails(articleId);			
		} catch(Exception exception) {
			System.out.println("getAllLatestNews Exception" + exception.getMessage());
		}		
		return null;
	}

}
