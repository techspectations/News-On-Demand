package com.manorama.techspectations.util;

import android.content.Context;
import android.media.AudioManager;

import com.manorama.techspectations.facebook.MyApplication;

/**
 * Created by Godwin Joseph on 10-11-2016 12:18 for Techspectations application.
 */

public class AppUtils {
    public static int getCurrentMediaVolume() {
        AudioManager audio = (AudioManager) MyApplication.getAppContext().getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        return currentVolume;
    }
}
