package com.manorama.techspectations.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Godwin Joseph on 12-11-2016 11:38 for Techspectations application.
 */

public class ConnectivityReceiver extends BroadcastReceiver {
    String TAG = "ConnectivityReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isUserLoggedIn = TechSpectationPreference.getInstance().getBooleanPrefValue(TechSpectationPreference.IS_USER_LOGGED);
        if (isUserLoggedIn) {
            Intent intent1 = new Intent(Constants.IntentConstants.ACTION_CONNECTIVITY);
            intent1.setAction(Constants.IntentConstants.ACTION_CONNECTIVITY);
            context.sendBroadcast(intent1);
        }
    }
}
