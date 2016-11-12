/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manorama.techspectations.ads;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.manorama.techspectations.BuildConfig;
import com.manorama.techspectations.R;


/**
 * An abstract activity which deals with recovering from errors which may occur during API
 * initialization, but can be corrected through user action.
 */
public abstract class YouTubeFailureRecoveryActivity extends YouTubeBaseActivity implements
    YouTubePlayer.OnInitializedListener {

  private static final int RECOVERY_DIALOG_REQUEST = 1;

  @Override
  public void onInitializationFailure(YouTubePlayer.Provider provider,
                                      YouTubeInitializationResult errorReason) {
    if (errorReason.isUserRecoverableError()) {
      errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
    } else {
      String errorMessage = getString(R.string.error_player);
      AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
      builder1.setMessage(errorMessage);
      builder1.setCancelable(true);

      builder1.setPositiveButton(
              android.R.string.ok,
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  dialog.cancel();
                  finish();
                }
              });


      AlertDialog mAlertDialog = builder1.create();
      mAlertDialog.show();
      Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == RECOVERY_DIALOG_REQUEST) {
      // Retry initialization if user performed a recovery action
      getYouTubePlayerProvider().initialize(BuildConfig.YOUTUBE_KEY, this);
    }
  }

  protected abstract YouTubePlayer.Provider getYouTubePlayerProvider();

}
