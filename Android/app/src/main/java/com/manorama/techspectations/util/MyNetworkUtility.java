package com.manorama.techspectations.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Riyas.
 * @since 02 Sep 2015.
 */
public class MyNetworkUtility {

    public static  boolean checkInternetConnection(Context ctx) {
        try {


            if (ctx != null) {
                ConnectivityManager connMgr = (ConnectivityManager) ctx
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data

                    return true;
                } else {
                    // display error
                    return false;
                }

            } else {
                return false;
            }
        }catch (Exception e)
        {
            return false;
        }

    }
}
