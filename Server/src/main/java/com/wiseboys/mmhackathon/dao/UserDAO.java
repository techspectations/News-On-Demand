/**
 * 
 */
package com.wiseboys.mmhackathon.dao;

import java.math.BigInteger;
import java.util.List;

import com.wiseboys.mmhackathon.beans.User;
import com.wiseboys.mmhackathon.beans.UserLike;
import com.wiseboys.mmhackathon.daoimpl.UserDAOImpl;

/**
 * This interface has user related database transaction functions.{@link UserDAOImpl} 
 * class will provide actual implementation for all the functions 
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
public interface UserDAO {
	public User getUserByEmail(String email);
	public User getUserById(BigInteger id);
	public User addNewUserDetails(User user);
	public User updateUserDetails(User user);
	public void addUserLikes(List<UserLike> userLikes, BigInteger userId);
	public void deleteLikesByUserId(BigInteger userId);
	public List<UserLike> getUserLikesByUserId(BigInteger userId);
}
