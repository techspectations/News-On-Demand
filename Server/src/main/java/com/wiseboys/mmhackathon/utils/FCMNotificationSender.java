/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import com.wiseboys.mmhackathon.beans.NotificaitonMessage;

/**
 * Sending FCM notification
 * @author Irudaya Raj
 * Nov 12, 2016 2016
 */
public class FCMNotificationSender {
	
	private static FCMNotificationSender instance= null;
	private static Object mutex= new Object();
	private FCMNotificationSender(){
	}

	/**
	 * Thread safe single for notification sender
	 * @return
	 */
	public static FCMNotificationSender getInstance(){
		if(instance==null){
			synchronized (mutex){
				if(instance==null) instance= new FCMNotificationSender();
			}
		}
		return instance;
	}

	/**
	 * Send notification
	 * @param notifcatioMessage
	 */
	public void sendNotification(NotificaitonMessage notifcatioMessage) {
		System.out.println("INVOKING FCM SERVER");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(notifcatioMessage.getFcmUrl());
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key="+notifcatioMessage.getFcmToken());

		JSONObject message = new JSONObject();
		try {
			message.put("to", notifcatioMessage.getAndroidFCMToken());
			message.put("priority", "high");

			JSONObject notification = new JSONObject();
			notification.put("title", notifcatioMessage.getMessage());
			notification.put("body", notifcatioMessage.getResourceUrl());

			message.put("notification", notification);

			post.setEntity(new StringEntity(message.toString(), "UTF-8"));
			HttpResponse response = client.execute(post);
			if(response != null)
				System.out.println("RESPONSE FROM FCM==>" + response.toString());
		} catch (JSONException e) {
			System.out.println("JSONException" + e.getLocalizedMessage());
		} catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException" + e.getLocalizedMessage());
		} catch (IOException e) {
			System.out.println("IOException" + e.getLocalizedMessage());
		}
		
		System.out.println(message);
	}
}
