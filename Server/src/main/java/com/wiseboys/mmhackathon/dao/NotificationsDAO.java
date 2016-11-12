/**
 * 
 */
package com.wiseboys.mmhackathon.dao;

import java.math.BigInteger;
import java.util.List;

import com.wiseboys.mmhackathon.beans.UserNotification;
import com.wiseboys.mmhackathon.beans.UserTravel;
import com.wiseboys.mmhackathon.daoimpl.NotificationDAOImpl;

/**
 * This interface has notification and travel details related database transaction functions.{@link NotificationDAOImpl} 
 * class will provide actual implementation for all the functions 
 * @author Irudaya Raj
 * Nov 12, 2016 2016
 */
public interface NotificationsDAO {
	public void addNewTravelDetails(List<UserTravel> userTravelDetails);
	public void deleteTravelDetailsByUserId(BigInteger userId);
	public List<UserTravel> getAllEvents();
	public UserNotification postNotificationDetails(UserNotification notification);
	public UserNotification getUserNotification(String userId);
}
