package com.manorama.techspectations.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.manorama.techspectations.util.Constants;

/**
 * Created by Godwin Joseph on 10-11-2016 15:49 for Techspectations application.
 */

public class NewsHeader extends BaseModel implements Parcelable {
    private long newsId = Constants.INTEGER_INITIALIZE_VALUE;
    private String newsArticleId = Constants.STRING_INITIALIZE_VALUE;
    private String newsHeading = Constants.STRING_INITIALIZE_VALUE;

    public NewsHeader() {
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsArticleId() {
        return newsArticleId;
    }

    public void setNewsArticleId(String newsArticleId) {
        this.newsArticleId = newsArticleId;
    }

    protected NewsHeader(Parcel in) {
        super(in);
        newsId = in.readLong();
        newsHeading = in.readString();
        newsArticleId = in.readString();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(newsId);
        dest.writeString(newsHeading);
        dest.writeString(newsArticleId);
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