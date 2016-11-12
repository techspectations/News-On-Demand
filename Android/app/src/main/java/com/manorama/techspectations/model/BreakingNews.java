package com.manorama.techspectations.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.manorama.techspectations.util.Constants;

/**
 * Created by Godwin Joseph on 10-11-2016 15:33 for Techspectations application.
 */

public class BreakingNews extends NewsHeader implements Parcelable {

    private String newsUrl = Constants.STRING_INITIALIZE_VALUE;
    private String newsProvider = Constants.STRING_INITIALIZE_VALUE;
    private String newsThumbnailUrl = Constants.STRING_INITIALIZE_VALUE;
    private String newsMobileImageUrl = Constants.STRING_INITIALIZE_VALUE;
    private String newsWebImageUrl = Constants.STRING_INITIALIZE_VALUE;
    private String newsVideoUrl = Constants.STRING_INITIALIZE_VALUE;

    public BreakingNews() {
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsThumbnailUrl() {
        return newsThumbnailUrl;
    }

    public void setNewsThumbnailUrl(String newsThumbnailUrl) {
        this.newsThumbnailUrl = newsThumbnailUrl;
    }

    public String getNewsMobileImageUrl() {
        return newsMobileImageUrl;
    }

    public void setNewsMobileImageUrl(String newsMobileImageUrl) {
        this.newsMobileImageUrl = newsMobileImageUrl;
    }

    public String getNewsWebImageUrl() {
        return newsWebImageUrl;
    }

    public void setNewsWebImageUrl(String newsWebImageUrl) {
        this.newsWebImageUrl = newsWebImageUrl;
    }

    public String getNewsVideoUrl() {
        return newsVideoUrl;
    }

    public void setNewsVideoUrl(String newsVideoUrl) {
        this.newsVideoUrl = newsVideoUrl;
    }

    public String getNewsProvider() {
        return newsProvider;
    }

    public void setNewsProvider(String newsProvider) {
        this.newsProvider = newsProvider;
    }

    protected BreakingNews(Parcel in) {
        super(in);
        newsUrl = in.readString();
        newsProvider = in.readString();
        newsThumbnailUrl = in.readString();
        newsMobileImageUrl = in.readString();
        newsWebImageUrl = in.readString();
        newsVideoUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsUrl);
        dest.writeString(newsProvider);
        dest.writeString(newsThumbnailUrl);
        dest.writeString(newsMobileImageUrl);
        dest.writeString(newsWebImageUrl);
        dest.writeString(newsVideoUrl);

        super.writeToParcel(dest, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BreakingNews> CREATOR = new Parcelable.Creator<BreakingNews>() {
        @Override
        public BreakingNews createFromParcel(Parcel in) {
            return new BreakingNews(in);
        }

        @Override
        public BreakingNews[] newArray(int size) {
            return new BreakingNews[size];
        }
    };


}