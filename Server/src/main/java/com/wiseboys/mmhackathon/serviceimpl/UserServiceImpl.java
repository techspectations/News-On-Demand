/**
 * 
 */
package com.wiseboys.mmhackathon.serviceimpl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiseboys.mmhackathon.beans.User;
import com.wiseboys.mmhackathon.beans.UserLike;
import com.wiseboys.mmhackathon.daoimpl.UserDAOImpl;
import com.wiseboys.mmhackathon.exceptions.InvalidInputException;
import com.wiseboys.mmhackathon.service.UserService;
import com.wiseboys.mmhackathon.utils.SessionIdentifierGenerator;

/**
 * All user related business logics are implemented in this class. {@link UserService}
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
@Service
public class UserServiceImpl implements UserService{

	/**
	 * Inject {@link UserDAOImpl}
	 */
	@Autowired
	private UserDAOImpl _userDAO;
	
	/* 
	 * @see com.wiseboys.mmhackathon.service.UserService#doesUserExist(java.lang.String)
	 */
	@Override
	public User doesUserExist(String email) {
		if(email != null && email.trim().length() != 0) {
			return _userDAO.getUserByEmail(email);
		} else {
			try {
				throw new InvalidInputException("Invalid email");
			} catch (InvalidInputException e) {				
				return null;
			}
		}		
	}

	/* 
	 * @see com.wiseboys.mmhackathon.service.UserService#addNewUserDetails(com.wiseboys.mmhackathon.beans.User)
	 */
	@Override
	public User addNewUserDetails(User user) {
		if(user != null) {
			user.setApiRequestToken(SessionIdentifierGenerator.nextSessionId() + SessionIdentifierGenerator.nextSessionId());// Set new session id
			user.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
			user.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
			return _userDAO.addNewUserDetails(user);
		} else {
			try {
				throw new InvalidInputException("Invalid object to insert");			
			} catch (InvalidInputException e) {				
				return null;
			}
		}		
	}

	/*
	 * @see com.wiseboys.mmhackathon.service.UserService#updateUserDetails(java.lang.String)
	 */
	@Override
	public User updateUserDetails(User user) {
		if(user.getEmailAddress() != null) {
			user.setApiRequestToken(SessionIdentifierGenerator.nextSessionId() + SessionIdentifierGenerator.nextSessionId());// Set new session id
			user.setUpdatedDate(new Timestamp(new java.util.Date().getTime()));
			return _userDAO.updateUserDetails(user);
		} else {
			try {
				throw new InvalidInputException("Invalid email");			
			} catch (InvalidInputException e) {				
				return null;
			}
		}
	}

	/* Inserting user likes 
	 * @see com.wiseboys.mmhackathon.service.UserService#addUserLikes(java.util.List)
	 */
	@Override
	public boolean addUserLikes(List<UserLike> userLikes, BigInteger userId) {
		if(userLikes != null) {
			_userDAO.deleteLikesByUserId(userId); // Clear previous likes before adding the latest likes
			_userDAO.addUserLikes(userLikes, userId);
			return true;
		} else {
			try {
				throw new InvalidInputException("Invalid likes object");			
			} catch (InvalidInputException e) {				
				return false;
			}
		}
	}

}
