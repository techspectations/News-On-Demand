/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Containing user travel details imported from Android Calender
 * @author Irudaya Raj
 */
@Entity
@Table(name="tbl_user_notification_details")
public class UserNotification implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private BigInteger id; // Auto generated value in database
	private String userId = "";
	private String notificationToken;

	public UserNotification() {}

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the notificationToken
	 */
	public String getNotificationToken() {
		return notificationToken;
	}

	/**
	 * @param notificationToken the notificationToken to set
	 */
	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
