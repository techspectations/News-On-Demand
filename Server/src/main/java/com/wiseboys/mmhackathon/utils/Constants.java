/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

/**
 * Common values used in this project
 * @author rajkl
 */
public class Constants {
	
	// All constant values related to REST API controller classes
	public final static String NEWS_API_VERSION = "1.0";
	public final static String NEWS_API_BASE_PATH = "/news/api/" + NEWS_API_VERSION;	
	public final static String RESPONSE_DATA_TYPE = "application/json";
	public final static String REQUEST_DATA_TYPE = "application/json";
	
	public final static String ANDROID_EVENT_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss a";
	public final static int NOTIFICATION_HOUR_DIFFRENCE = 1;
	
	public final static String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	public final static String FCM_API_KEY = "AIzaSyCz2NnJIrjJUBThFH4GO_hfPOdyOHqkSmI";
}
