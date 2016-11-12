package com.cloudconnection.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Godwin Joseph on 03-02-2016.
 */
public class Utility {
    public static boolean validateUrl(String inputUrl) {
        try {
            URL url = new URL(inputUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public static String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd,MMM yyyy hh:mm:ss a");
        return format.format(date);
    }
    public static boolean checkInternetConnection(Context ctx) {
        try {
            if (ctx != null) {
                ConnectivityManager connMgr = (ConnectivityManager) ctx
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
