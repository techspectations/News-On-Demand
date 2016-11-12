/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Scheduler job to send notification
 * 
 * @author Irudaya Raj Nov 12, 2016 2016
 */
public class NotificationJob {

	NotificationJob() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		System.out.println("Invoked on " + dateFormat.format(System.currentTimeMillis()));
	}

}
