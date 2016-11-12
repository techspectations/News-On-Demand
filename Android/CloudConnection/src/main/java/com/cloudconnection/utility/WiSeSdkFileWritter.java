package com.cloudconnection.utility;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utility class to write the action happening with in the sdk to sd card.
 *
 * @author Riyas.
 * @since 16 Sep 2015.
 */
public class WiSeSdkFileWritter {

    public static final String TAG = "WiSe Cloud SDK : WriteDataToFile";

    private final static String mFileName = "wise_api_calls.txt";

    private final static String mFolderName = "WiSe_SDK_State";


    public static void writeToFile(String sBody) {

        if (!Logger.IS_DEBUG_ENABLED)
            return;

        try {
            File root = new File(Environment.getExternalStorageDirectory(),
                    mFolderName);
            if (!root.exists()) {
                root.mkdirs();
            }
            File file = new File(root, mFileName);

            if (file.length() > 10 * 1024 * 1024) {
                file.delete();
                root = new File(Environment.getExternalStorageDirectory(),
                        mFolderName);
                if (!root.exists()) {
                    root.mkdirs();
                }
                file = new File(root, mFileName);


            }
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(sBody.getBytes());
            fos.write("\r\n".getBytes()); //Enter the string here
            fos.flush();


            fos.close();


        } catch (IOException e) {
            e.printStackTrace();

            Logger.e(TAG, "ERROR||>>>" + e.toString().toUpperCase());

        } catch (Exception e) {

        }


    }


    public static void writeToFile(String fileName, String sBody) {
        if (!Logger.IS_DEBUG_ENABLED)
            return;
        try {
            File root = new File(Environment.getExternalStorageDirectory(),
                    mFolderName);
            if (!root.exists()) {
                root.mkdirs();
            }
            File file = new File(root, fileName);


            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(sBody.getBytes());
            fos.write("\r\n".getBytes()); //Enter the string here
            fos.flush();


            fos.close();


        } catch (IOException e) {
            e.printStackTrace();

            Logger.e(TAG, "ERROR||>>>" + e.toString().toUpperCase());

        }


    }

}
