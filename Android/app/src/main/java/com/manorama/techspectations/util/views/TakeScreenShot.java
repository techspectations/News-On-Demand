package com.manorama.techspectations.util.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Akbar Rasheed on 11/12/2016.
 */

public class TakeScreenShot {

    public synchronized String getScreenShotImageFilePath(Context c) {
        try {

            File file = new File(Environment.getExternalStorageDirectory(),
                    ".news/.SreenShotShare/");
            if (!file.exists()) {
                if (!file.mkdirs()) {
//                    Log.e("getGalleryImageFilePath :: ",
//                            "Problem creating Image folder");
                    return null;
                }
            }

            String s = "ss.jpg";

            File f = new File(file.getAbsolutePath(), s);
            Log.i("getGalleryImageFilePath", f.getAbsolutePath() + "");

            return f.getAbsolutePath();

        } catch (Exception e) {
            Log.e("TAGhere", "" + e);
        }
        return null;
    }


    public String takeScreenshot(Context ctx) {

        try {
            // image naming and path to include sd card appending name you
            // choose for file
            String mPath =getScreenShotImageFilePath(ctx);

            // create bitmap screen capture
            Activity activity = (Activity) ctx;
            View v1 = activity.getWindow().getDecorView();// .getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmapOrg = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            int width = bitmapOrg.getWidth();
            int height = bitmapOrg.getHeight();
            int newWidth = width;
            int newHeight = height;

            // calculate the scale - in this case = 0.4f
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            // createa matrix for the manipulation
            Matrix matrix = new Matrix();
            // resize the bit map
            matrix.postScale(scaleWidth, scaleHeight);

            // recreate the new Bitmap
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 50, width,
                    height - 50, matrix, true);
            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality,
                    outputStream);
            outputStream.flush();
            outputStream.close();
            return imageFile.getAbsolutePath();

        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
            return null;
        }
    }
}
