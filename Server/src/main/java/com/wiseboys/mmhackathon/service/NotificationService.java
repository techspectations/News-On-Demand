/**
 * 
 */
package com.wiseboys.mmhackathon.service;

import java.util.List;

import com.wiseboys.mmhackathon.beans.UserTravel;

/**
 * All Business class declarations for notification related features
 * @author Irudaya Raj
 * Nov 12, 2016 2016
 */
public interface NotificationService {
	public void addNewTravelDetails(List<UserTravel> userTravelDetails);	
}
