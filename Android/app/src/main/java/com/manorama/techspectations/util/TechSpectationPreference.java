package com.manorama.techspectations.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.manorama.techspectations.facebook.MyApplication;

/**
 * Created by Albi on 11/10/2016.
 */

public class TechSpectationPreference {
    private static final String SHARED_PREF_NAME = "MyPref";

    public static String USER_ID = "USER_ID";
    public static String USER_NAME = "USER_NAME";
    public static String USER_DISPLAY_NAME = "USER_DISPLAY_NAME";
    public static String USER_PROFILE_PIC_URI = "USER_PROFILE_PIC_URI";

    public static String SERVER_TOKEN = "SERVER_TOKEN";
    public static String SOCIAL_NETWORK_ID = "SOCIAL_NETWORK_ID";
    public static String SOCIAL_NETWORK_TOKEN = "SOCIAL_NETWORK_TOKEN";


    Context mContext;
    private static TechSpectationPreference mPref = new TechSpectationPreference(MyApplication.getAppContext());

    public static TechSpectationPreference getInstance() {
        return mPref;
    }

    private TechSpectationPreference(Context context) {
        this.mContext = context;
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    public void setDoublePrefValue(String key, double value) {

        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
        return;
    }

    public double getDouble(final String key) {

        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return Double.longBitsToDouble(pref.getLong(key, Double.doubleToLongBits(0)));
    }

    /**
     * Getting boolean key value from shared preference
     *
     * @param key
     * @return
     */
    public boolean getBooleanPrefValue(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return pref.getBoolean(key, false);
    }

    /**
     * Getting boolean key value from shared preference
     *
     * @param key
     * @return
     */
    public boolean getBooleanPrefValue2(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return pref.getBoolean(key, true);
    }


    /**
     * Getting boolean key value from shared preference
     *
     * @param key
     * @return
     */
    public long getLongPrefValue(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return pref.getLong(key, -1);
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    public void setBooleanPrefValue(String key, boolean value) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(key, value);
        editor.commit();

    }

    /**
     * Getting float key value from shared preference
     *
     * @param key
     * @return
     */
    public float getFloatPrefValue(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return pref.getFloat(key, (float) 0.000000);
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    public void setFloatPrefValue(String key, float value) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putFloat(key, value);
        editor.commit();

    }


    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    public void setLongPrefValue(String key, long value) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putLong(key, value);
        editor.commit();

    }

    /**
     * Getting boolean key value from shared preference
     *
     * @param key
     * @return
     */
    public String getStringPrefValue(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(key, null);
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    public void setStringPrefValue(String key, String value) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key, value);
        editor.commit();

    }

    /**
     * Getting integer key value from shared preference
     *
     * @param key
     * @return
     */
    public int getIntegerPrefValue(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return pref.getInt(key, -1);
    }

    /**
     * Setting a integer key value to Shared preference
     *
     * @param key
     * @param value
     */
    public void setIntegerPrefValue(String key, int value) {
        SharedPreferences pref = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(key, value);
        editor.commit();

    }
}
