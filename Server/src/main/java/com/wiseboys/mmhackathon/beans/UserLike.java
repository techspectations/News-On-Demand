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
 * Entity class to hold user likes
 * @author Irudaya Raj
 * Nov 10, 2016 2016
 */
@Entity
@Table(name="tbl_user_likes")
public class UserLike implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private BigInteger id; // Auto generated value in database
	private BigInteger userId;
	private String likeItem = "";
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
	public BigInteger getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	/**
	 * @return the likeItem
	 */
	public String getLikeItem() {
		return likeItem;
	}
	/**
	 * @param likeItem the likeItem to set
	 */
	public void setLikeItem(String likeItem) {
		this.likeItem = likeItem;
	}	
	
}
