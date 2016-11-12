package com.manorama.techspectations.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.manorama.techspectations.util.Constants;

import java.util.ArrayList;

/**
 * Created by Godwin Joseph on 10-11-2016 14:32 for Techspectations application.
 */

public class News extends BreakingNews implements Parcelable {

    String newsReportedPlace = Constants.STRING_INITIALIZE_VALUE;
    String news = Constants.STRING_INITIALIZE_VALUE;
    String newsEditorName = Constants.STRING_INITIALIZE_VALUE;
    String newsEditorEmail = Constants.STRING_INITIALIZE_VALUE;
    String newsEditorImageUri = Constants.STRING_INITIALIZE_VALUE;
    String newsEditorDesignation = Constants.STRING_INITIALIZE_VALUE;

    String newsReportedTime = Constants.STRING_INITIALIZE_VALUE;

    int isOfflineSaved = Constants.INTEGER_INITIALIZE_VALUE;
    String newsSection = Constants.STRING_INITIALIZE_VALUE;

    long totalViews = Constants.INTEGER_INITIALIZE_VALUE;
    ArrayList<String> tags = new ArrayList<>();
    ArrayList<String> relatedArticles = new ArrayList<>();

    public String getNewsReportedPlace() {
        return newsReportedPlace;
    }

    public void setNewsReportedPlace(String newsReportedPlace) {
        this.newsReportedPlace = newsReportedPlace;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNewsEditorName() {
        return newsEditorName;
    }

    public void setNewsEditorName(String newsEditorName) {
        this.newsEditorName = newsEditorName;
    }

    public String getNewsReportedTime() {
        return newsReportedTime;
    }

    public void setNewsReportedTime(String newsReportedTime) {
        this.newsReportedTime = newsReportedTime;
    }

    public long getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(long totalViews) {
        this.totalViews = totalViews;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getNewsEditorEmail() {
        return newsEditorEmail;
    }

    public void setNewsEditorEmail(String newsEditorEmail) {
        this.newsEditorEmail = newsEditorEmail;
    }

    public String getNewsEditorImageUri() {
        return newsEditorImageUri;
    }

    public void setNewsEditorImageUri(String newsEditorImageUri) {
        this.newsEditorImageUri = newsEditorImageUri;
    }

    public String getNewsEditorDesignation() {
        return newsEditorDesignation;
    }

    public void setNewsEditorDesignation(String newsEditorDesignation) {
        this.newsEditorDesignation = newsEditorDesignation;
    }

    public int getIsOfflineSaved() {
        return isOfflineSaved;
    }

    public void setIsOfflineSaved(int isOfflineSaved) {
        this.isOfflineSaved = isOfflineSaved;
    }

    public String getNewsSection() {
        return newsSection;
    }

    public void setNewsSection(String newsSection) {
        this.newsSection = newsSection;
    }

    public ArrayList<String> getRelatedArticles() {
        return relatedArticles;
    }

    public void setRelatedArticles(ArrayList<String> relatedArticles) {
        this.relatedArticles = relatedArticles;
    }

    public News() {
    }

    protected News(Parcel in) {
        super(in);
        newsReportedPlace = in.readString();
        news = in.readString();
        newsEditorName = in.readString();
        newsReportedTime = in.readString();

        newsEditorDesignation = in.readString();
        newsEditorEmail = in.readString();
        newsEditorImageUri = in.readString();
        newsEditorName = in.readString();
        newsSection = in.readString();
        isOfflineSaved = in.readInt();

        totalViews = in.readLong();
        if (in.readByte() == 0x01) {
            tags = new ArrayList<String>();
            in.readList(tags, String.class.getClassLoader());
        } else {
            tags = null;
        }
        if (in.readByte() == 0x01) {
            relatedArticles = new ArrayList<String>();
            in.readList(relatedArticles, String.class.getClassLoader());
        } else {
            relatedArticles = null;
        }
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsReportedPlace);
        dest.writeString(news);
        dest.writeString(newsEditorName);
        dest.writeString(newsReportedTime);

        dest.writeString(newsEditorDesignation);
        dest.writeString(newsEditorEmail);
        dest.writeString(newsEditorImageUri);
        dest.writeString(newsEditorName);
        dest.writeString(newsSection);
        dest.writeInt(isOfflineSaved);

        dest.writeLong(totalViews);
        if (tags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tags);
        }
        if (relatedArticles == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(relatedArticles);
        }
        super.writeToParcel(dest, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}