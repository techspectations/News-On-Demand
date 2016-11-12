/**
 * 
 */
package com.wiseboys.mmhackathon.schedulers;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wiseboys.mmhackathon.beans.UserTravel;
import com.wiseboys.mmhackathon.daoimpl.NotificationDAOImpl;
import com.wiseboys.mmhackathon.serviceimpl.NotificationServiceImpl;
import com.wiseboys.mmhackathon.utils.Constants;
import com.wiseboys.mmhackathon.utils.Utils;

/**
 * Quarz job for watching travel notifications
 * Get all events from database and send notifications to mobile
 * @author Irudaya Raj Nov 12, 2016 2016
 */
public class QuartzJob implements Job {
	
	// Declaring private variables
	private NotificationDAOImpl _notificationDAO = new NotificationDAOImpl();
	private NotificationServiceImpl _notificationService = new NotificationServiceImpl();
	
	/**
	 * Retrieve data from database 
	 * Send out notification
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		checkforNotificationEvent();
	}
	
	/**
	 * Checking for all events from the database
	 * We have not checked for the past date : // TODO: This will be done in the next version
	 */
	private void checkforNotificationEvent() {
		try {
			List<UserTravel> events = _notificationDAO.getAllEvents();
			if(events != null) {
				for(UserTravel event : events) {
					if(event != null && event.getEvent() != null && event.getEvent().trim().length() != 0 
							&& event.getEvent().toLowerCase().contains("travel")) {
						System.out.println("There is a travel event====>" + event.getEvent());
						boolean isThereEventToday = isThereEventToday(event);
						if(isThereEventToday) {
						//	UserNotification userDetails = _notificationService.getUserNotification(event.getUserId());
//							_notificationService.sendNotification(new NotificaitonMessage(event.getEvent(), 
//									"News pdf url", Constants.FCM_URL, Constants.FCM_API_KEY,
//									userDetails.getNotificationToken()));// Get notification token
						}
					} else {
						System.out.println("It's not a travel event ==>" + event.getEvent());
					}
				}
			}
		} catch(Exception e) {
			System.out.println("Exception in checkforNotificationEvent" +  e.getMessage());
		}		
	}
	
	/**
	 * We will send notification before 5 hours of travel
	 * @param travel
	 * @return true if event is there otherwise false 
	 */
	private boolean isThereEventToday(UserTravel travel) {
		Date eventDate = Utils.getDateFromString(travel.getDate(), Constants.ANDROID_EVENT_DATE_FORMAT);
		System.out.println("eventDate date" + eventDate.toString());
		Date currentDate = Utils.getCurrentDateWithFormat(Constants.ANDROID_EVENT_DATE_FORMAT);
		System.out.println("currentDate date" + currentDate.toString());
		int totalHoursDifference = Utils.getHourDifferenceBetweenDates(eventDate, currentDate);
		System.out.println("totalHoursDifference" + totalHoursDifference);
		if(totalHoursDifference <= Constants.NOTIFICATION_HOUR_DIFFRENCE) {
			return true;
		}			
		return false;
	}
	
}
