/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.beans.ManoramaArticleDetails;
import com.wiseboys.mmhackathon.beans.ManoramaArticles;
import com.wiseboys.mmhackathon.beans.ManoramaEditions;
import com.wiseboys.mmhackathon.beans.ManoramaSection;
import com.wiseboys.mmhackathon.beans.ManoramaSections;
import com.wiseboys.mmhackathon.beans.SearchArticlesResult;
import com.wiseboys.mmhackathon.daoimpl.ManoramaAPIsDAOImpl;

/**
 * @author rajkl
 */
@Service
public class ManoramaAPIsHelper {

	// Declare all constants
	private final static String authorizationKey = "d34c214a-fd6b-5b80-bae9-716abe3eab3c";
	private final static String acceptValue = "application/json";
	private final static String ALL_EDITIONS_URL = "https://developer.manoramaonline.com/api/editions";
	private final static String ALL_SECTIONS_URL = "https://developer.manoramaonline.com/api/editions/en/sections";
	private static String ALL_ARTICLES_URL = "https://developer.manoramaonline.com/api/editions/en/sections";
	private static String ARTICLE_DETAILS = "https://developer.manoramaonline.com/api/editions/en/articles";
	public static String SEARCH_ARTICLES = "https://developer.manoramaonline.com/api/editions/en/search?type=all&term=";
	public static long TOTAL_SEARCH_RESULTS = 5;
	public static long TOTAL_SEARCH_RESULTS_LOCATION = 20;
	public static long TOTAL_LATEST_NEWS_SIZE = 20;
	
	

	
	// Declare private global variables
	private static ManoramaAPIsDAOImpl _manoramaAPIsDAOImpl = new ManoramaAPIsDAOImpl();
	private static List<ManoramaSection> _sections;
	private static List<ManoramaArticle> _articles;
	
	/**
	 * Truncate all the tables data for every fresh set of data
	 */
	public void truncateAllManoramaTables(){
		_manoramaAPIsDAOImpl.truncateAllManoramaTables();
	}
	
	/**
	 * Get all editions HTTP request header values.
	 * Execute a GET HTTP request by passing ALL_EDITIONS_URL and proper header values    
	 */
	public void saveAllEditions() {
		try {
			HttpEntity<String> entity = getCommonHeaders();
			ResponseEntity<String> editionResponse = executeUrl(ALL_EDITIONS_URL, HttpMethod.GET, entity);
			if(editionResponse != null)
				processAllEditionsJsonResponse(editionResponse.getBody());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}				
	}	
	/**
	 * Parse all editions response and save them in local database
	 * @param editionJsonInString
	 */
	private void processAllEditionsJsonResponse(String editionJsonInString) {
		if (editionJsonInString != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				ManoramaEditions editions = mapper.readValue(editionJsonInString, ManoramaEditions.class);
				if (editions != null & editions.editions != null)
					_manoramaAPIsDAOImpl.saveEdition(editions.editions);			
			} catch (JsonParseException e) {				
				System.out.println(e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {	
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * Get all sections HTTP request header values.
	 * Execute a GET HTTP request by passing ALL_SECTIONS_URL and proper header values    
	 */
	public void saveAllSections() {
		try {
			HttpEntity<String> entity = getCommonHeaders();
			ResponseEntity<String> sectionsResponse = executeUrl(ALL_SECTIONS_URL, HttpMethod.GET, entity);
			if(sectionsResponse != null)
				processAllSectionsJsonResponse(sectionsResponse.getBody());	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}	
	/**
	 * Parse all sections response and save them in local database
	 * @param sectionJsonInString
	 */
	private void processAllSectionsJsonResponse(String sectionJsonInString) {
		if (sectionJsonInString != null) {
			ObjectMapper mapper = new ObjectMapper();
			// JSON from String to Object
			try {
				ManoramaSections manonramaSections = new ManoramaSections();
				manonramaSections = mapper.readValue(sectionJsonInString, ManoramaSections.class);
				if (manonramaSections != null && manonramaSections.getSections() != null) {
					_sections = new ArrayList<ManoramaSection>();
					for (ManoramaSection section : manonramaSections.getSections()) {
						List<ManoramaSection> allSections = new ArrayList<ManoramaSection>();
						if (section != null) {
							allSections.add(section);
							List<ManoramaSection> subSections = section.getSections(); // Get all sub sections
							if (subSections != null) {
								for (ManoramaSection subSection : subSections)
									allSections.add(subSection);
							}
						}						
						_manoramaAPIsDAOImpl.saveSections(allSections);
						_sections.addAll(allSections); // Save all the sections because we need to get articles of each section 
					}
				}
			} catch (JsonParseException e) {
				System.out.println(e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}	
	
	/**
	 * Get all articles HTTP request header values.
	 * Execute a GET HTTP request by passing ALL_SECTIONS_URL and proper header values    
	 */
	public void saveAllArticles() {
		try {
			HttpEntity<String> entity = getCommonHeaders();
			if(_sections != null) {
				_articles = new ArrayList<ManoramaArticle>();
				for(ManoramaSection section : _sections) { // Main for loop starts here
					if(section != null && section.getCode() != null) {
						String articleUrl = ALL_ARTICLES_URL + "/" + section.getCode().trim() + "/articles";
						System.out.println("Article url:===>" + articleUrl);
						ResponseEntity<String> articleResponseEntity = executeUrl(articleUrl, HttpMethod.GET, entity);				
						if(articleResponseEntity != null)
							processAllArticlesJsonResponse(articleResponseEntity.getBody());				
					}
				} // Main for loop ends here
			} 		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}				
	}
	
	/**
	 * Parse all articles response and save them in local database
	 * @param articleJsonInString
	 */
	private void processAllArticlesJsonResponse(String articleJsonInString) {
		if (articleJsonInString != null) {
			ObjectMapper mapper = new ObjectMapper();
			// JSON from String to Object
			try {
				ManoramaArticles articles = mapper.readValue(articleJsonInString, ManoramaArticles.class);
				if (articles != null && articles.getArticleSummary() != null) {		
					_articles.addAll(articles.getArticleSummary());
					_manoramaAPIsDAOImpl.saveArticles(articles.getArticleSummary());					
				}
			} catch (JsonParseException e) {
				System.out.println(e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Get all article details HTTP request header values.
	 * Execute a GET HTTP request by passing ALL_SECTIONS_URL and proper header values    
	 */
	public void saveArticleDetails() {
		try {
			HttpEntity<String> entity = getCommonHeaders();
			if(_articles != null) {
				for(ManoramaArticle article : _articles) { // Main for loop starts here
					if(article != null && article.getArticleID() != null) {
						String articleDetailsUrl = ARTICLE_DETAILS + "/" + article.getArticleID();
						System.out.println("Article Details URL:==>" + articleDetailsUrl);
						ResponseEntity<String> articleResponseEntity = executeUrl(articleDetailsUrl, HttpMethod.GET, entity);				
						if(articleResponseEntity != null)
							processArticleDetailsJsonResponse(articleResponseEntity.getBody());				
					}
				} // Main for loop ends here
			} 		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}				
	}
	
	/**
	 * Parse article details and save them in local db
	 * @param articleDetailsJsonInString
	 */
	private void processArticleDetailsJsonResponse(String articleDetailsJsonInString) {
		if (articleDetailsJsonInString != null) {
			ObjectMapper mapper = new ObjectMapper();
			// JSON from String to Object
			try {
				ManoramaArticleDetails articleDetails = mapper.readValue(articleDetailsJsonInString, ManoramaArticleDetails.class);
				if (articleDetails != null) {						
					_manoramaAPIsDAOImpl.saveArticleDetails(articleDetails);					
				}
			} catch (JsonParseException e) {
				System.out.println(e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Get all articles based on search
	 * Execute a GET HTTP request by passing ALL_SECTIONS_URL and proper header values    
	 */
	public List<ManoramaArticle> searchNews(String url) {
		try {
			HttpEntity<String> entity = getCommonHeaders();
			String searchUrl = url;
			System.out.println("Search Url url:===>" + searchUrl);
			ResponseEntity<String> articleResponseEntity = executeUrl(searchUrl, HttpMethod.GET, entity);				
			if(articleResponseEntity != null)
				return processSearchedArticlesJsonResponse(articleResponseEntity.getBody());						

		} catch(Exception e) {
			System.out.println(e.getMessage());		
		}
		return null;				
	}
	
	/**
	 * Parse all searched articles details
	 * @param articleJsonInString
	 */
	private List<ManoramaArticle> processSearchedArticlesJsonResponse(String articleJsonInString) {
		if (articleJsonInString != null) {
			ObjectMapper mapper = new ObjectMapper();
			// JSON from String to Object
			try {
				SearchArticlesResult articles = mapper.readValue(articleJsonInString, SearchArticlesResult.class);
				if (articles != null)									
					return articles.getArticles();			
			} catch (JsonParseException e) {
				System.out.println(e.getMessage());				
			} catch (JsonMappingException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} 
		return null;
	}
	
	
	/**
	 * Get all article details HTTP request header values.
	 * Execute a GET HTTP request by passing ALL_SECTIONS_URL and proper header values    
	 */
	public ManoramaArticleDetails getArticleDetails(String articleID) {
		try {
			HttpEntity<String> entity = getCommonHeaders();			
			String articleDetailsUrl = ARTICLE_DETAILS + "/" + articleID.trim();
			System.out.println("Article Details URL:==>" + articleDetailsUrl);
			ResponseEntity<String> articleResponseEntity = executeUrl(articleDetailsUrl, HttpMethod.GET, entity);				
			if(articleResponseEntity != null) 
				return processArticleDetailsResponse(articleResponseEntity.getBody());				 		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}				
		return null;
	}
	
	/**
	 * Parse article details
	 * @param articleDetailsJsonInString
	 */
	private ManoramaArticleDetails processArticleDetailsResponse(String articleDetailsJsonInString) {
		if (articleDetailsJsonInString != null) {
			ObjectMapper mapper = new ObjectMapper();
			// JSON from String to Object
			try {
				return mapper.readValue(articleDetailsJsonInString, ManoramaArticleDetails.class);				
			} catch (JsonParseException e) {
				System.out.println(e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	
	/**
	 * Get HTTP request headers for all Manorama REST APIs
	 * @return entity
	 */
	private HttpEntity<String> getCommonHeaders(){		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authorizationKey);
		headers.set("Accept", acceptValue);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
	
	/**
	 * Making actual HTTP request
	 * @param url
	 * @param verb
	 * @param entity
	 * @return ResponseEntity 
	 */
	private ResponseEntity<String> executeUrl(String url, HttpMethod verb, HttpEntity<String> entity) {
		try {
			return new RestTemplate().exchange(url, verb, entity, String.class);	
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
}
