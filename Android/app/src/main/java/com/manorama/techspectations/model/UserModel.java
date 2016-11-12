package com.manorama.techspectations.model;

import java.util.ArrayList;

/**
 * Created by rajkl on 11/10/2016.
 */

public class UserModel {

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSocialNetworkName() {
        return socialNetworkName;
    }

    public void setSocialNetworkName(String socialNetworkName) {
        this.socialNetworkName = socialNetworkName;
    }

    public String getSocialNetworkId() {
        return socialNetworkId;
    }

    public void setSocialNetworkId(String socialNetworkId) {
        this.socialNetworkId = socialNetworkId;
    }

    public String getSocialNetworkToken() {
        return socialNetworkToken;
    }

    public void setSocialNetworkToken(String socialNetworkToken) {
        this.socialNetworkToken = socialNetworkToken;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFacebookLocationId() {
        return facebookLocationId;
    }

    public void setFacebookLocationId(String facebookLocationId) {
        this.facebookLocationId = facebookLocationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLikeItem() {
        return likeItem;
    }

    public void setLikeItem(String likeItem) {
        this.likeItem = likeItem;
    }

    int userId;
    String displayName;
    String emailAddress;
    int age;
    String socialNetworkName;
    String socialNetworkId;
    String socialNetworkToken;
    String gender;
    String facebookLocationId;
    String location;
    String likeItem;
    String profilePicUrl;


}
