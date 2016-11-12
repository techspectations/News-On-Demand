/**
 * 
 */
package com.wiseboys.mmhackathon.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wiseboys.mmhackathon.beans.ManoramaEdition;
import com.wiseboys.mmhackathon.beans.ManoramaSection;
import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.beans.ManoramaArticleDetails;
import com.wiseboys.mmhackathon.dao.ManoramaAPIsDAO;
import com.wiseboys.mmhackathon.utils.HibernateUtil;

/**
 * @author rajkl
 * Data access implementation class for all manorama APIs related db tables
 */
public class ManoramaAPIsDAOImpl implements ManoramaAPIsDAO {
	
	// For DB Transactions
	private SessionFactory _sessionFactory = HibernateUtil.getSessionFactory();
	
	/**
	 * Truncate data from all manorama related tables
	 */
	@Override
	public synchronized void truncateAllManoramaTables() {		
		_sessionFactory.openSession().createSQLQuery("TRUNCATE TABLE tbl_manorama_edition").executeUpdate();
		_sessionFactory.openSession().createSQLQuery("TRUNCATE TABLE tbl_manorama_section").executeUpdate();
		_sessionFactory.openSession().createSQLQuery("TRUNCATE TABLE tbl_manorama_article").executeUpdate();
		_sessionFactory.openSession().createSQLQuery("TRUNCATE TABLE tbl_manorama_article_details").executeUpdate();				
	}
	

	
	/**
	 * Inserting editions as bulk
	 */
	@Override
	public synchronized void saveEdition(List<ManoramaEdition> editions) {
		if(editions != null) {
			Session session = _sessionFactory.openSession();
	        session.beginTransaction();	        
	        int i = 0;
	        for(ManoramaEdition edition : editions) {
	        	session.save(edition);
	        	if(i == 50) {
	        		session.flush();  //flush a batch of inserts and release memory:
	        		session.clear();
	        	}
	        	i++;
	        }	       
	        session.getTransaction().commit();      
	        session.close();	
		}		
	}
	
	/**
	 * Inserting sections as bulk
	 */
	@Override
	public synchronized void saveSections(List<ManoramaSection> sections) {
		if(sections != null) {
			Session session = _sessionFactory.openSession();
	        session.beginTransaction();	        
	        int i = 0;
	        for(ManoramaSection section : sections) {
	        	session.save(section);
	        	if(i == 50) {
	        		session.flush();  //flush a batch of inserts and release memory:
	        		session.clear();
	        	}
	        	i++;
	        }	        
	        session.getTransaction().commit();      
	        session.close();	
		}		
	}
	
	/**
	 * Inserting articles as bulk
	 */
	@Override
	public synchronized void saveArticles(List<ManoramaArticle> articles) {
		if(articles != null) {
			Session session = _sessionFactory.openSession();
	        session.beginTransaction();	        
	        int i = 0;
	        for(ManoramaArticle article : articles) {
	        	session.save(article);
	        	if(i == 50) {
	        		session.flush();  //flush a batch of inserts and release memory:
	        		session.clear();
	        	}
	        	i++;
	        }	        
	        session.getTransaction().commit();      
	        session.close();	
		}		
	}
	
	/**
	 * Inserting article details
	 */
	@Override
	public synchronized void saveArticleDetails(ManoramaArticleDetails articleDetails) {
		if(articleDetails != null) {
			Session session = _sessionFactory.openSession();
	        session.beginTransaction();	        	        
	        session.save(articleDetails);	               
	        session.getTransaction().commit();      
	        session.close();	
		}
	}

}
