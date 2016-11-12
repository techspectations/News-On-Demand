/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

/**
 * @author rajkl
 *
 */
public class ManoramaDataExtractorService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManoramaAPIsHelper manoramaAPIsHelper = new ManoramaAPIsHelper();
		manoramaAPIsHelper.truncateAllManoramaTables();
		manoramaAPIsHelper.saveAllEditions();
		manoramaAPIsHelper.saveAllSections();
		manoramaAPIsHelper.saveAllArticles();
		manoramaAPIsHelper.saveArticleDetails();
	}

}
