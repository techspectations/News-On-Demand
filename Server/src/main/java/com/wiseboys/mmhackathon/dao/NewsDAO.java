/**
 * 
 */
package com.wiseboys.mmhackathon.dao;

import java.util.List;

import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.daoimpl.NewsDAOImpl;

/**
 * This interface has NEWS related database transaction functions.{@link NewsDAOImpl} 
 * class will provide actual implementation for all the functions 
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
public interface NewsDAO {
	public List<ManoramaArticle> getAllArticles();
}
