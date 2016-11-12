package com.manorama.techspectations.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.manorama.techspectations.util.Constants;

/**
 * Created by Godwin Joseph on 10-11-2016 15:49 for Techspectations application.
 */

public class NewsHeader extends BaseModel implements Parcelable {
    private String newsId = Constants.STRING_INITIALIZE_VALUE;
    private String newsHeading = Constants.STRING_INITIALIZE_VALUE;


    public NewsHeader() {
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    protected NewsHeader(Parcel in) {
        super(in);
        newsId = in.readString();
        newsHeading = in.readString();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsId);
        dest.writeString(newsHeading);
        super.writeToParcel(dest, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsHeader> CREATOR = new Parcelable.Creator<NewsHeader>() {
        @Override
        public NewsHeader createFromParcel(Parcel in) {
            return new NewsHeader(in);
        }

        @Override
        public NewsHeader[] newArray(int size) {
            return new NewsHeader[size];
        }
    };
}