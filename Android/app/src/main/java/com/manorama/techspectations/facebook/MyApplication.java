package com.manorama.techspectations.facebook;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.manorama.techspectations.network_task.BackGroundNewsFetchService;
import com.manorama.techspectations.util.CalenderEvents;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by Belal on 5/3/2015.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Fresco.initialize(this);
        Stetho.initializeWithDefaults(this);
        printHashKey();
        BackGroundNewsFetchService.getInstance().startTimer();
        //syncUserCalenderEvents();
    }

    private void syncUserCalenderEvents() {

        ArrayList<String> eventNames = CalenderEvents.readCalendarEvent(this);

        ArrayList<String> eventStartTime = CalenderEvents.startDates;

    }

    public static Context getAppContext() {
        return mContext;
    }

    public void printHashKey() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "net.simplifiedcoding.androidlogin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
