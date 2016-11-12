/**
 * 
 */
package com.wiseboys.mmhackathon.service;

import java.math.BigInteger;
import java.util.List;

import com.wiseboys.mmhackathon.beans.User;
import com.wiseboys.mmhackathon.beans.UserLike;
import com.wiseboys.mmhackathon.serviceimpl.NewsServiceImpl;

/**
 * All user related business logics are declared in this class. {@link NewsServiceImpl}
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
public interface UserService {
	public User doesUserExist(String email);
	public User addNewUserDetails(User user);
	public User updateUserDetails(User user);
	public boolean addUserLikes(List<UserLike> userLikes, BigInteger userId);
}
