package com.manorama.techspectations.notification_management;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.manorama.techspectations.R;
import com.manorama.techspectations.ui.SplashScreenActivity;


public class NotificationCreator {
    static String TAG = "NotificationCreator";

    static int NOTIFICATION_ID = 65532;

    static Bitmap bitmap;

    public static void setBigImage(Bitmap b) {
        bitmap = b;
    }

    /**
     * Removing notification from notification bar
     *
     * @param ctx
     */
    public static void cancelNotification(Context ctx) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) ctx
                .getSystemService(ns);
        nMgr.cancel(NOTIFICATION_ID);
    }


    /**
     * Notifying the status of share ,means accepted or rejected by the shared
     * user
     *
     * @param c {@link Context}
     */
    public static void showNotification(Context c, String message) {


        if (TextUtils.isEmpty(message))
            message = "You have a new notification";

        Intent intent = new Intent(c, SplashScreenActivity.class);
        //   intent.putExtra(MyConstants.INTENT_EXTRA_NOTIFICATION_MSG, message);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        // Bundle bd = new Bundle();

        // intent.putExtras(bd);

        PendingIntent contentIntent = PendingIntent.getActivity(c,
                NOTIFICATION_ID, intent, 0);

        Uri uri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager mNotificationManager = (NotificationManager) c
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap bitmapDrawable = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bitmapDrawable = ((BitmapDrawable) c.getDrawable(R.mipmap.nd_icon)).getBitmap();
        }


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(c)
                .setSmallIcon(R.mipmap.nd_notification)

                .setContentTitle(c.getString(R.string.app_name))
                .setTicker(message).setTicker(message)
                .setColor(ContextCompat.getColor(c, R.color.colorPrimary))
                .setStyle(
                        new NotificationCompat.BigTextStyle().bigText(message)).setColor(ContextCompat.getColor(c, R.color.colorPrimaryDark))
                .setContentText(message).setAutoCancel(true);

        if (bitmapDrawable != null) {
            mBuilder.setLargeIcon(bitmapDrawable);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && bitmap != null) {
            mBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText(message)).setColor(ContextCompat.getColor(c,R.color.colorPrimary));
        }

        mBuilder.setSound(uri);


        mBuilder.setColor(ContextCompat.getColor(c, R.color.colorPrimary));

        mBuilder.setContentIntent(contentIntent);

        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

        bitmap = null;
    }


}

