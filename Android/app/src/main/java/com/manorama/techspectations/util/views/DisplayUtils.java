package com.manorama.techspectations.util.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.manorama.techspectations.facebook.MyApplication;
import com.manorama.techspectations.util.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Godwin Joseph on 10-11-2016 12:22 for Techspectations application.
 */

public class DisplayUtils {

    final static String TAG = "DisplayUtils";
    public static ProgressDialog pd_progress;
    public static Toast mToast;
    static TimerTask timerTask;
    static Timer timer;
    private static boolean isShowing = false;

    public static void showLoader(final Context ctx, final String msg) {
        isShowing = true;
        try {
            final Activity activity = (Activity) ctx;

            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoader(ctx);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if (pd_progress == null)
                                    pd_progress = getProgressDiaLogger(activity, msg, true);


                                pd_progress.show();
                                isShowing = true;

                                setTimerToCancelProgress(ctx);

                            }


                        }, 10);
                    }
                });

            }
        } catch (WindowManager.BadTokenException e) {

        } catch (Exception e) {
        }
    }

    public static boolean isLoaderShowing() {
        boolean isShowing = false;
        if (pd_progress == null)
            isShowing = false;
        else
            isShowing = pd_progress.isShowing();
        return isShowing;
    }

    public static void showLoader(final Context ctx, final String msg, final boolean isCancelable) {
        isShowing = true;
        try {
            final Activity activity = (Activity) ctx;

            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoader(ctx);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pd_progress = getProgressDiaLogger(activity, msg, isCancelable);
                                pd_progress.show();
                                isShowing = true;

                                setTimerToCancelProgress(ctx);
                            }
                        }, 10);
                    }
                });

            }
        } catch (WindowManager.BadTokenException e) {

        } catch (Exception e) {

        }

    }


    private static void setTimerToCancelProgress(final Context ctx) {

        if (timerTask != null) {
            timerTask.cancel();
            timer.cancel();
            timer = null;

            timerTask = null;
        }
        Logger.i(TAG, "SHOW LOADER||SHOW LOADER||SHOW LOADER||" + isShowing + ":" + pd_progress.isShowing());

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                dismissLoader(ctx);
                Logger.w(TAG, "Progress diaLogger automatically stoped after 1 min.");
            }
        };

        timer.schedule(timerTask, 60 * 1000);


        pd_progress.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface diaLoggerInterface) {
                isShowing = false;
            }
        });
    }

    public static ProgressDialog getProgressDiaLogger(Activity ctx, String msg, boolean isCancelable) {
        pd_progress = new ProgressDialog(ctx);
        pd_progress.setMessage(msg);
        pd_progress.setCancelable(isCancelable);
        pd_progress.setCanceledOnTouchOutside(false);

        return pd_progress;

    }


    public static void dismissLoader(Context ctx) {

        if (pd_progress != null)
            Logger.d(TAG, "DISMISS LOADER||DISMISS LOADER||DISMISS LOADER||111111111");

        try {
            Activity activity = (Activity) ctx;

            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Logger.d(TAG, "DISMISS LOADER||DISMISS LOADER||DISMISS LOADER||2222222");

                        try {
                            Logger.d(TAG, "DISMISS LOADER||DISMISS LOADER||DISMISS LOADER||333333");

                            dismissLoader1();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }

        } catch (WindowManager.BadTokenException e) {

        } catch (Exception e) {
            Logger.i(TAG, ">>>>>>>>>>>>inside the catch block" + e);
        }

    }

    public static void dismissLoader1() throws Exception {

        try {

            Logger.d(TAG, "DISMISS LOADER||DISMISS LOADER||DISMISS LOADER||444444");

            if (pd_progress != null)
                Logger.d(TAG, "DISMISS LOADER||DISMISS LOADER||DISMISS LOADER||555555" + isShowing + ":" + pd_progress.isShowing());
            if (pd_progress != null) {

                pd_progress.cancel();
                pd_progress.dismiss();
                pd_progress.setCanceledOnTouchOutside(true);
                pd_progress = null;
                isShowing = false;
            }
        } catch (Exception e) {
            Logger.e(TAG, "DISMISS LOADER||DISMISS LOADER||DISMISS LOADER||66666" + e);
            throw e;
        }
    }

    public static void showToast(final String msg) {
        dismissToast();
        mToast = Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_LONG);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void showToast(final Context ctx, final int stringResId) {
        if (ctx != null) {
            dismissToast();
            mToast = Toast.makeText(ctx, stringResId, Toast.LENGTH_LONG);
            mToast.show();
        }
    }

    public static void dismissToast() {
        if (mToast != null)
            mToast.cancel();
    }

    public static ProgressDialog getProgress() {
        return pd_progress;
    }


}
