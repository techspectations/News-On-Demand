package com.manorama.techspectations.notification_management;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 0001 :: 01-May-2016.
 */
public class GeneratePictureStyleNotification extends AsyncTask<String, Void, Bitmap> {

    private Context mContext;
    private String title, message, imageUrl;

    JSONObject jsonObject;

    public GeneratePictureStyleNotification(Context context, JSONObject message) {
        super();
        this.mContext = context;
        this.jsonObject=message;
        this.title = title;
        this.message = message.optString("message");
        this.imageUrl = message.optString("imageUrl");
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        InputStream in;
        try {
            URL url = new URL(this.imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            in = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(in);
            return myBitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        NotificationCreator.setBigImage(result);
        NotificationCreator.showNotification(mContext,jsonObject.optString("message"));



    }
}