package com.manorama.techspectations.notification_management;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        RemoteMessage.Notification notification = remoteMessage.getNotification();

        Map<String, String> dataMap = remoteMessage.getData();

        if (dataMap != null && dataMap.size() > 0) {
            String message = (new ArrayList<String>(dataMap.values())).get(0);

            //   String message=dataMap.get("0");
            Log.d(TAG, "From: " + message);

            //  message=dataMap.get("1");
            Log.d(TAG, "Notification Message Body: " + message);

            //Calling method to generate notification
            sendNotification(message);
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     */
    private void sendNotification(String msg) {

        Log.i(TAG, TAG + "SendNotification" + msg);

        try {

            JSONObject jObj = new JSONObject(msg);

            String message = jObj.optString("message");
            String imageUrl = jObj.optString("imageUrl");


            if (TextUtils.isEmpty(message))
                return;


            if (!TextUtils.isEmpty(imageUrl)) {

                boolean isValid = Patterns.WEB_URL.matcher(imageUrl).matches();
                if (isValid)
                    new GeneratePictureStyleNotification(this, jObj).execute();
            } else {
                NotificationCreator.setBigImage(null);
                NotificationCreator.showNotification(this, jObj.optString("message"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}