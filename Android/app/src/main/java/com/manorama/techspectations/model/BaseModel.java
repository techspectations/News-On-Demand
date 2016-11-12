package com.manorama.techspectations.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.manorama.techspectations.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Godwin Joseph on 10-11-2016 18:47 for Techspectations application.
 */

public class BaseModel implements Parcelable {
    long timeStamp = Constants.INTEGER_INITIALIZE_VALUE;

    public BaseModel() {
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyy HH:mm");
        return sdf.format(new Date(timeStamp));
    }

    protected BaseModel(Parcel in) {
        timeStamp = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timeStamp);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BaseModel> CREATOR = new Parcelable.Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel in) {
            return new BaseModel(in);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };
}