/**
 * 
 */
package com.wiseboys.mmhackathon.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.dao.NewsDAO;
import com.wiseboys.mmhackathon.utils.HibernateUtil;

/**
 * Implementations of all NEWS related database transaction
 * functions.{@link NewsDAO}
 * 
 * @author Irudaya Raj Nov 10, 2016 2016
 */
@Component
public class NewsDAOImpl implements NewsDAO {

	// For DB Transactions
	private SessionFactory _sessionFactory = HibernateUtil.getSessionFactory();

	/*
	 * Get all articles
	 * 
	 * @see com.wiseboys.mmhackathon.dao.NewsDAO#getAllArticles()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ManoramaArticle> getAllArticles() {
		List<ManoramaArticle> articles = new ArrayList<ManoramaArticle>();
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			articles = session.createCriteria(ManoramaArticle.class).list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			Logger.getLogger("con").info("Exception: " + ex.getMessage());		
		} finally {
			session.close();
		}
		return articles;
	}

}
