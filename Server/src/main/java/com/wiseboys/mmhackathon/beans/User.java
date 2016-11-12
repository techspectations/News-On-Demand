/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Containing user details
 * @author Irudaya Raj
 */
@Entity
@Table(name="tbl_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private BigInteger id; // Auto generated value in database
	private String displayName = "";
	private String emailAddress = "";
	private Integer age;
	private String socialNetworkName = "";
	private String socialNetworkId = "";
	private String socialNetworkToken = "";
	private String gender = "";
	private String facebookLocationId = "";
	private String location = "";
	private String apiRequestToken = "";
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	public User() {}
	
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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return the socialNetworkName
	 */
	public String getSocialNetworkName() {
		return socialNetworkName;
	}
	/**
	 * @param socialNetworkName the socialNetworkName to set
	 */
	public void setSocialNetworkName(String socialNetworkName) {
		this.socialNetworkName = socialNetworkName;
	}
	/**
	 * @return the socialNetworkId
	 */
	public String getSocialNetworkId() {
		return socialNetworkId;
	}
	/**
	 * @param socialNetworkId the socialNetworkId to set
	 */
	public void setSocialNetworkId(String socialNetworkId) {
		this.socialNetworkId = socialNetworkId;
	}
	/**
	 * @return the socialNetworkToken
	 */
	public String getSocialNetworkToken() {
		return socialNetworkToken;
	}
	/**
	 * @param socialNetworkToken the socialNetworkToken to set
	 */
	public void setSocialNetworkToken(String socialNetworkToken) {
		this.socialNetworkToken = socialNetworkToken;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the facebookLocationId
	 */
	public String getFacebookLocationId() {
		return facebookLocationId;
	}
	/**
	 * @param facebookLocationId the facebookLocationId to set
	 */
	public void setFacebookLocationId(String facebookLocationId) {
		this.facebookLocationId = facebookLocationId;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the apiRequestToken
	 */
	public String getApiRequestToken() {
		return apiRequestToken;
	}
	/**
	 * @param apiRequestToken the apiRequestToken to set
	 */
	public void setApiRequestToken(String apiRequestToken) {
		this.apiRequestToken = apiRequestToken;
	}
	/**
	 * @return the createdTime
	 */
	public Timestamp getCreatedTime() {
		return createdDate;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Timestamp createdTime) {
		this.createdDate = createdTime;
	}
	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}		
}
