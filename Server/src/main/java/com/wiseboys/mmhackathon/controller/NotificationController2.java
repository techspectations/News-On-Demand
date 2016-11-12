/**
 * 
 */
package com.wiseboys.mmhackathon.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiseboys.mmhackathon.beans.StatusResponse;
import com.wiseboys.mmhackathon.beans.UserTravel;
import com.wiseboys.mmhackathon.serviceimpl.NotificationServiceImpl;
import com.wiseboys.mmhackathon.utils.Constants;

/**
 * All notification related API request is handled in this class
 * @author Irudaya Raj
 * Nov 12, 2016 2016
 */
@Controller
public class NotificationController2 {

	/**
	 * Inject {@link NotificationServiceImpl}
	 */
	@Autowired
	private NotificationServiceImpl _notificationService;
	
	// Declaring API end points
	private final static String POST_USER_TRAVEL_DETAILS = Constants.NEWS_API_BASE_PATH + "/travels";
	
	
	/**
	 * Post all user Calender details. 
	 * @return Status of the calender details post
	 */
	@RequestMapping(value=POST_USER_TRAVEL_DETAILS, method = RequestMethod.POST, produces = Constants.RESPONSE_DATA_TYPE)
	public @ResponseBody StatusResponse postUserTravelDetails(@RequestBody UserTravel[] travelDetails){
		List<UserTravel> userTravelEvents = Arrays.asList(travelDetails);
		if(userTravelEvents != null) {
			System.out.println("Got user travel details");
			_notificationService.addNewTravelDetails(userTravelEvents);
			return new StatusResponse(true);
		} else {
			return new StatusResponse(false);
		}				
	}	
	
}
