/**
 * 
 */
package com.wiseboys.mmhackathon.daoimpl;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.wiseboys.mmhackathon.beans.UserTravel;
import com.wiseboys.mmhackathon.dao.NotificationsDAO;
import com.wiseboys.mmhackathon.utils.HibernateUtil;

/**
 * Implementations of all NEWS related database transaction
 * functions.{@link NotificationsDAO}
 * 
 * @author Irudaya Raj Nov 10, 2016 2016
 */
@Component
public class NotificationDAOImpl implements NotificationsDAO {

	// For DB Transactions
	private SessionFactory _sessionFactory = HibernateUtil.getSessionFactory();

	/*
	 * Add travel details to tbl_user_travel_details
	 * 
	 * @see com.wiseboys.mmhackathon.dao.NewsDAO#getAllArticles()
	 */
	@Override
	public synchronized void addNewTravelDetails(List<UserTravel> userTravelDetails) {
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			int i = 0;
	        for(UserTravel travel : userTravelDetails) {	        	
	        	session.save(travel);
	        	if(i == 50) {
	        		session.flush();  //flush a batch of inserts and release memory:
	        		session.clear();
	        	}
	        	i++;
	        }	 	
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			Logger.getLogger("con").info("Exception: " + ex.getMessage());		
		} finally {
			session.close();
		}
	}

	/*
	 * @see com.wiseboys.mmhackathon.dao.NotificationsDAO#deleteTravelDetailsByUserId(java.math.BigInteger)
	 */
	@Override
	public void deleteTravelDetailsByUserId(BigInteger userId) {
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from UserTravel where userId=:userId");
			query.setBigInteger("userId", userId);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			Logger.getLogger("con").info("Exception: " + ex.getMessage());		
		} finally {
			session.close();
		}			
	}

}
