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

import com.wiseboys.mmhackathon.beans.User;
import com.wiseboys.mmhackathon.beans.UserLike;
import com.wiseboys.mmhackathon.dao.UserDAO;
import com.wiseboys.mmhackathon.utils.HibernateUtil;

/**
 * Implementations for {@link UserDAO}
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
@Component
public class UserDAOImpl implements UserDAO {

	// For DB Transactions
	private SessionFactory _sessionFactory = HibernateUtil.getSessionFactory();
	
	/* 
	 * Get user details based on email address
	 * @see com.wiseboys.mmhackathon.dao.UserDAO#getUserByEmail(java.lang.String)
	 */
	@Override
	public synchronized User getUserByEmail(String email) {
		User user = null;
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from User where emailAddress=:email");
			query.setString("email", email.trim());
			user = (User) query.uniqueResult();			
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
		return user;	
	}
	
	/*
	 * @see com.wiseboys.mmhackathon.dao.UserDAO#getUserById(java.math.BigInteger)
	 */
	@Override
	public synchronized User getUserById(BigInteger id) {		
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from User where id=:id");
			query.setBigInteger("id", id);
			transaction.commit();
			return (User) query.uniqueResult();						
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			Logger.getLogger("con").info("Exception: " + ex.getMessage());		
		} finally {
			session.close();
		}
		return null;	
	}	

	/* 
	 * @see com.wiseboys.mmhackathon.dao.UserDAO#addNewUserDetails(com.wiseboys.mmhackathon.beans.User)
	 */
	@Override
	public synchronized User addNewUserDetails(User user) {
		if(user != null) {
			Session session = _sessionFactory.openSession();
	        session.beginTransaction();	        
	        BigInteger userId = (BigInteger) session.save(user);
	        user.setId(userId);
	        session.getTransaction().commit();      
	        session.close();	
	        return user;
		} else {
			return null;	
		}		
	}

	/* 
	 * @see com.wiseboys.mmhackathon.dao.UserDAO#updateUserDetails(java.lang.String)
	 */
	@Override
	public synchronized User updateUserDetails(User user) {		
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();			
			Query query = session.createQuery("update User set displayName= :displayName,"														
														+ "age= :age,"
														+ "socialNetworkName= :socialNetworkName,"
														+ "socialNetworkId= :socialNetworkId,"
														+ "socialNetworkToken= :socialNetworkToken,"
														+ "gender= :gender,"
														+ "facebookLocationId= :facebookLocationId,"
														+ "location= :location,"
														+ "apiRequestToken= :apiRequestToken,"
														+ "updatedDate= :updatedDate"
														+ " where emailAddress=:email");
			query.setString("displayName", user.getDisplayName());			
			query.setInteger("age", user.getAge());
			query.setString("socialNetworkName", user.getSocialNetworkName());
			query.setString("socialNetworkId", user.getSocialNetworkId());
			query.setString("socialNetworkToken", user.getSocialNetworkToken());
			query.setString("gender", user.getGender());
			query.setString("facebookLocationId", user.getFacebookLocationId());
			query.setString("location", user.getLocation());
			query.setString("apiRequestToken", user.getApiRequestToken());
			query.setTimestamp("updatedDate", user.getUpdatedDate());
			query.setString("email", user.getEmailAddress().trim());
			query.executeUpdate();	
			
			query = session.createQuery("from User where emailAddress=:email");
			query.setString("email", user.getEmailAddress().trim());
			user = (User) query.uniqueResult();				
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			Logger.getLogger("con").info("Exception: " + ex.getMessage());		
		} finally {
			session.close();
		}
		return user;	
	}

	/* Inserting multiple user likes
	 * @see com.wiseboys.mmhackathon.dao.UserDAO#addUserLikes(java.util.List)
	 */
	@Override
	public synchronized void addUserLikes(List<UserLike> userLikes, BigInteger userId) {
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			int i = 0;
	        for(UserLike userLike : userLikes) {
	        	userLike.setUserId(userId);
	        	session.save(userLike);
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
	 * @see com.wiseboys.mmhackathon.dao.UserDAO#deleteLikesByUserId(java.math.BigInteger)
	 */
	@Override
	public synchronized void deleteLikesByUserId(BigInteger userId) {		
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from UserLike where userId=:userId");
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

	/* (non-Javadoc)
	 * @see com.wiseboys.mmhackathon.dao.UserDAO#getUserLikesByUserId(java.math.BigInteger)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserLike> getUserLikesByUserId(BigInteger userId) {
		Session session = _sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();		
			Query query = session.createQuery("from UserLike where userId=:userId");
			query.setBigInteger("userId", userId);
			List<UserLike> userLikes = query.list();
			transaction.commit();
			return userLikes;
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			Logger.getLogger("con").info("Exception: " + ex.getMessage());		
		} finally {
			session.close();
		}
		return null;
	}	
}
