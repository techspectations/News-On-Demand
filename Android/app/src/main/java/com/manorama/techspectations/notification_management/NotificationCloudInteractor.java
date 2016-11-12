package com.manorama.techspectations.notification_management;

import android.content.Context;
import android.text.TextUtils;

import com.cloudconnection.CloudAPICallback;
import com.cloudconnection.CloudConnectHttpMethod;
import com.google.firebase.iid.FirebaseInstanceId;
import com.manorama.techspectations.util.Common;
import com.manorama.techspectations.util.Logger;
import com.manorama.techspectations.util.TechSpectationPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Utility class to sync the FCM token to server
 * Created by Riyas on 012 : 12-11-2016.
 */
public class NotificationCloudInteractor {

    Context mContext;
    final String TAG="NotificationCloudInteractor";
    String userId;

    public NotificationCloudInteractor(Context mContext) {
        this.mContext = mContext;
        this.userId= TechSpectationPreference.getInstance().getStringPrefValue(TechSpectationPreference.USER_ID);
    }
    /**
     * Sync fcm token to server once soon after the fcm has generated token

     */
    public void syncFcmToken()
    {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        if(TextUtils.isEmpty(refreshedToken)||mContext==null||TextUtils.isEmpty(userId))
            return;

        CloudAPICallback apiCallback=new CloudAPICallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Logger.d(TAG,":"+jsonObject.toString());

            }

            @Override
            public void onSuccess(JSONArray jsonArray) {
               Logger.d(TAG,":"+jsonArray.toString());

            }

            @Override
            public void onFailure(int i, String s) {
                com.manorama.techspectations.util.Logger.d(TAG,":"+s+":"+i);

            }
        };

        JSONObject json=new JSONObject();
        try {
            json.put("userId",userId);
            json.put("notificationToken",refreshedToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CloudConnectHttpMethod httpMethod=new CloudConnectHttpMethod(mContext);
        httpMethod.setRequestType(CloudConnectHttpMethod.POST_METHOD);
        httpMethod.setEntityString(json.toString());
        httpMethod.setUrl(Common.AppConstants.BASE_URL+"notifications");
        httpMethod.setApiCallBack(apiCallback);
        httpMethod.execute();


    }
}
