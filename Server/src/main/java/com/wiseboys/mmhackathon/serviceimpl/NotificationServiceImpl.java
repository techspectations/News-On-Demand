/**
 * 
 */
package com.wiseboys.mmhackathon.serviceimpl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiseboys.mmhackathon.beans.NotificaitonMessage;
import com.wiseboys.mmhackathon.beans.UserNotification;
import com.wiseboys.mmhackathon.beans.UserTravel;
import com.wiseboys.mmhackathon.daoimpl.NotificationDAOImpl;
import com.wiseboys.mmhackathon.service.NotificationService;
import com.wiseboys.mmhackathon.utils.FCMNotificationSender;

/**
 * All notification related business logics are implemented in this class. {@link NotificationService}
 * @author Irudaya Raj
 * Nov 12, 2016 
 */
@Service
public class NotificationServiceImpl implements NotificationService {
	
	/**
	 * Inject {@link NotificationDAOImpl}
	 */
	@Autowired	
	private NotificationDAOImpl _notificationDAOImpl;

	/* (non-Javadoc)
	 * @see com.wiseboys.mmhackathon.service.NotificationService#addNewTravelDetails(java.util.List)
	 */
	@Override
	public void addNewTravelDetails(List<UserTravel> userTravelDetails) {
		try {
			if(userTravelDetails != null) {
				// Always delete 
				_notificationDAOImpl.deleteTravelDetailsByUserId(new BigInteger(userTravelDetails.get(0).getUserId()));
				_notificationDAOImpl.addNewTravelDetails(userTravelDetails);
			}	
		} catch (Exception e) {
			System.out.println("Exception in addNewTravelDetails " + e.getMessage());
		}		 
	}

	/* (non-Javadoc)
	 * @see com.wiseboys.mmhackathon.service.NotificationService#sendNotification(com.wiseboys.mmhackathon.beans.UserTravel)
	 */
	@Override
	public void sendNotification(NotificaitonMessage message) {
		try{
			FCMNotificationSender notificationSender = FCMNotificationSender.getInstance();
			if(notificationSender != null) {
				notificationSender.sendNotification(message);
			}	
		}catch(Exception exception) {
			System.out.println("sendNotification exception" + exception.getMessage());
		}		
	}

	/* (non-Javadoc)
	 * @see com.wiseboys.mmhackathon.service.NotificationService#postNotificationDetails(com.wiseboys.mmhackathon.beans.UserNotification)
	 */
	@Override
	public UserNotification postNotificationDetails(UserNotification notification) {
		if(notification != null) {
			return _notificationDAOImpl.postNotificationDetails(notification);
		} else {
			return notification;
		}
			
	}

	/* (non-Javadoc)
	 * @see com.wiseboys.mmhackathon.service.NotificationService#getUserNotification(java.lang.String)
	 */
	@Override
	public UserNotification getUserNotification(String userId) {
		NotificationDAOImpl notificationDAOImpl = new NotificationDAOImpl();
		return notificationDAOImpl.getUserNotification(userId);
	}
}
