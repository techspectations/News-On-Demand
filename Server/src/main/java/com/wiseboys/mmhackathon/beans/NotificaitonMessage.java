/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

/**
 * Bean class that contains all message details
 * @author Irudaya Raj
 * Nov 12, 2016 2016
 */
public class NotificaitonMessage {
	private String message;
	private String resourceUrl;
	private String fcmUrl;
	private String fcmToken;
	private String androidFCMToken;
	
	public NotificaitonMessage(String message, String resourceUrl, String fcmUrl, String fcmToken, String androidFCMToken) {
		this.message = message;
		this.resourceUrl = resourceUrl;
		this.fcmUrl = fcmUrl;
		this.fcmToken = fcmToken;
		this.androidFCMToken = androidFCMToken;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the resourceUrl
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}
	/**
	 * @param resourceUrl the resourceUrl to set
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	/**
	 * @return the fcmUrl
	 */
	public String getFcmUrl() {
		return fcmUrl;
	}
	/**
	 * @param fcmUrl the fcmUrl to set
	 */
	public void setFcmUrl(String fcmUrl) {
		this.fcmUrl = fcmUrl;
	}
	/**
	 * @return the fcmToken
	 */
	public String getFcmToken() {
		return fcmToken;
	}
	/**
	 * @param fcmToken the fcmToken to set
	 */
	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
	/**
	 * @return the androidFCMToken
	 */
	public String getAndroidFCMToken() {
		return androidFCMToken;
	}
	/**
	 * @param androidFCMToken the androidFCMToken to set
	 */
	public void setAndroidFCMToken(String androidFCMToken) {
		this.androidFCMToken = androidFCMToken;
	}
	
	
	
}
