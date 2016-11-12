package com.manorama.techspectations.ads;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.manorama.techspectations.BuildConfig;
import com.manorama.techspectations.R;


/**
 * Created by Admin on 021 : 21-09-2016.
 */
public class VideoViewActivity extends YouTubeFailureRecoveryActivity implements
        YouTubePlayer.OnInitializedListener {


    private static final int RECOVERY_DIALOG_REQUEST = 1;

    String youtubeId;

    private YouTubePlayerView youTubeView;
    YouTubePlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_view);


        youtubeId ="jwNoAw5sRNs";
        if (TextUtils.isEmpty(youtubeId))
            finish();


        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        // Initializing video player with developer key
        youTubeView.initialize(BuildConfig.YOUTUBE_KEY, this);

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        findViewById(R.id.btn_ok).setOnClickListener(onClickListener);
        findViewById(R.id.btn_cancel).setOnClickListener(onClickListener);




    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer != null)
            mPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayer != null)
            mPlayer.play();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPlayer != null)
            mPlayer.cueVideo(youtubeId);

    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        mPlayer = player;

        mPlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {
                findViewById(R.id.ll_played).setVisibility(View.GONE);
                findViewById(R.id.ll_addDesc).setVisibility(View.VISIBLE);

            }

            @Override
            public void onVideoEnded() {
                findViewById(R.id.ll_played).setVisibility(View.VISIBLE);
                findViewById(R.id.ll_addDesc).setVisibility(View.GONE);

            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        });

        if (!wasRestored) {

            player.loadVideo(youtubeId);

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }



}
