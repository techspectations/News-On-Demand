/**
 * 
 */
package com.wiseboys.mmhackathon.serviceimpl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiseboys.mmhackathon.beans.UserTravel;
import com.wiseboys.mmhackathon.daoimpl.NotificationDAOImpl;
import com.wiseboys.mmhackathon.service.NotificationService;

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
}
