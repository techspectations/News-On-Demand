/**
 * 
 */
package com.wiseboys.mmhackathon.controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiseboys.mmhackathon.beans.StatusResponse;
import com.wiseboys.mmhackathon.beans.User;
import com.wiseboys.mmhackathon.beans.UserLike;
import com.wiseboys.mmhackathon.serviceimpl.UserServiceImpl;
import com.wiseboys.mmhackathon.utils.Constants;

/**
 * @author Irudaya Raj
 * All user related REST APIs request is handled in this controller class
 */
@Controller
public class UserController {
	
	/**
	 * Inject {@link UserServiceImpl}
	 */
	@Autowired
	private UserServiceImpl _userService;
	
	// Declaring API end points
	private final static String ADD_USER = Constants.NEWS_API_BASE_PATH + "/users";
	private final static String POST_LIKES = Constants.NEWS_API_BASE_PATH + "/users/{userId}/likes";

	/**
	 * Add new user details
	 * @param User details as JSON data
	 * @return Updated or Added JSON
	 */
	@RequestMapping(value=ADD_USER, method = RequestMethod.POST, produces = Constants.RESPONSE_DATA_TYPE, consumes = Constants.REQUEST_DATA_TYPE)
	public @ResponseBody User postUserDetails(@RequestBody User user){
		if(user != null){	
			User existingUser = _userService.doesUserExist(user.getEmailAddress().trim());
			if(existingUser != null) {// Update the user details
				return _userService.updateUserDetails(user); 
			} else { // New user to news application so add him to database
				return _userService.addNewUserDetails(user); 		
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Add user likes
	 * @param User likes as JSON array
	 * @return true on success post
	 */
	@RequestMapping(value=POST_LIKES, method = RequestMethod.POST, produces = Constants.RESPONSE_DATA_TYPE, consumes = Constants.REQUEST_DATA_TYPE)
	public @ResponseBody StatusResponse postUserLikes(@RequestBody UserLike[] likeRequest, @PathVariable BigInteger userId){
		List<UserLike> userLikes = Arrays.asList(likeRequest);
		if(_userService.addUserLikes(userLikes, userId)) {
			return new StatusResponse(true);
		} else {
			return new StatusResponse(false);
		}
	}
	
}
	