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
    String newsReportedTime = Constants.STRING_INITIALIZE_VALUE;

    long totalViews = Constants.INTEGER_INITIALIZE_VALUE;
    ArrayList<String> tags = new ArrayList<>();

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

    public News() {
    }

    protected News(Parcel in) {
        super(in);
        newsReportedPlace = in.readString();
        news = in.readString();
        newsEditorName = in.readString();
        newsReportedTime = in.readString();
        totalViews = in.readLong();
        if (in.readByte() == 0x01) {
            tags = new ArrayList<String>();
            in.readList(tags, String.class.getClassLoader());
        } else {
            tags = null;
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
        dest.writeLong(totalViews);
        if (tags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tags);
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