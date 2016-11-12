package com.manorama.techspectations.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.manorama.techspectations.R;
import com.manorama.techspectations.database.manager.DatabaseManager;
import com.manorama.techspectations.interfaces.OnUiUpdatedListener;
import com.manorama.techspectations.model.NewsHeader;
import com.manorama.techspectations.util.AppUtils;
import com.manorama.techspectations.util.Constants;
import com.manorama.techspectations.util.Logger;
import com.manorama.techspectations.util.views.DisplayUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Godwin Joseph on 09-11-2016 21:13 for Techspectations application.
 */

public abstract class BaseActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = "BaseActivity";
    protected Context mContext;
    protected Toolbar mToolbar;
    boolean showBreakingNews = true;

    TextToSpeech textToSpeech;
    String utteranceId = "utteranceId";

    TextView tvBreakingNews;
    ImageView ivSpeckNews;
    OnUiUpdatedListener onUiUpdatedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = initializeContext();
        if (mContext == null)
            throw new IllegalArgumentException("BASE ACTIVITY CONTEXT IS NULL : PLEASE INITIALIZE CONTEXT");
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        View baseView = getLayoutInflater().inflate(R.layout.activity_base, null);
        View childView = getLayoutInflater().inflate(layoutResID, null);
        FrameLayout container = (FrameLayout) baseView.findViewById(R.id.fl_base_container);
        tvBreakingNews = (TextView) baseView.findViewById(R.id.tv_breaking_news);
        ivSpeckNews = (ImageView) baseView.findViewById(R.id.iv_speck_news);
//        setMarqueeSpeed(tvBreakingNews, 15.0f);
        tvBreakingNews.setSelected(true);

        container.removeView(childView);
        container.addView(childView);

        if (showBreakingNews) {
            setContentView(baseView);
        } else {
            super.setContentView(layoutResID);
        }
        initializeWidgets();
        registerListeners();
        initializeObjects();
    }

    protected Toolbar setupToolbar(String title, boolean showNavigation) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showNavigation);
        getSupportActionBar().setTitle(title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        return mToolbar;
    }

    protected abstract Context initializeContext();

    protected abstract void initializeWidgets();

    protected abstract void registerListeners();

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(uiUpdateReciever, new IntentFilter(Constants.IntentConstants.ACTION_DATA_UPDATED_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(uiUpdateReciever);
    }

    BroadcastReceiver uiUpdateReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (onUiUpdatedListener != null) {
                onUiUpdatedListener.onUiUpdated();
                showBreakingNewsBottom();
            }
        }
    };

    private void initializeObjects() {
        textToSpeech = new TextToSpeech(mContext, this);
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {

            }

            @Override
            public void onDone(String s) {
                speakOut(true);
            }

            @Override
            public void onError(String s) {

            }
        });
        ivSpeckNews.setTag(1);
        ivSpeckNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((Integer) ivSpeckNews.getTag() == 1) {
                    ivSpeckNews.setTag(2);
                    ivSpeckNews.setImageResource(R.drawable.pause_white);
                    speakOut(true);
                } else {
                    ivSpeckNews.setTag(1);
                    ivSpeckNews.setImageResource(R.drawable.play_white);
                    speakOut(false);
                }
            }
        });
    }

    private void showBreakingNewsBottom() {
        ArrayList<NewsHeader> newsHeaders = DatabaseManager.getInstance().getNewsHeaders();
        if (newsHeaders.size() > 10) {
            newsHeaders = new ArrayList<>(newsHeaders.subList(0, 10));
        }
        if (newsHeaders.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (NewsHeader header : newsHeaders) {
                if (header.getNewsHeading() != null) {
                    builder.append(header.getNewsHeading());
                    builder.append(" | ");
                }
            }
            if (builder.toString().length() > 0) {
                tvBreakingNews.setText(builder.toString());
            }
        }
    }

    private void speakOut(boolean start) {
        if (start) {
            checkCurrentVolume();

            if (tvBreakingNews != null) {
                String news = tvBreakingNews.getText().toString();
                news = news.replace("  ", " ");
                news = news.replace("|", ".");

                if (news != null) {
                    textToSpeech.setSpeechRate(0.8f);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(news, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
                    } else {
                        textToSpeech.speak(news, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        } else {
            textToSpeech.stop();
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Log.e(TAG, "Language NOT SUPPORTED ");
            } else {
                Log.e(TAG, "SUCCESS SUCCESS");
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void checkCurrentVolume() {
        if (AppUtils.getCurrentMediaVolume() < 7) {
            DisplayUtils.showToast(getString(R.string.warning_low_volume));
        }
    }

    public void setMarqueeSpeed(TextView tv, float speed) {
        if (tv != null) {
            try {
                Field f = null;
                if (tv instanceof AppCompatTextView) {
                    f = tv.getClass().getSuperclass().getDeclaredField("mMarquee");
                } else {
                    f = tv.getClass().getDeclaredField("mMarquee");
                }
                if (f != null) {
                    f.setAccessible(true);
                    Object marquee = f.get(tv);
                    if (marquee != null) {
                        String scrollSpeedFieldName = "mScrollUnit";
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            scrollSpeedFieldName = "mPixelsPerSecond";
                        }
                        Field mf = marquee.getClass().getDeclaredField(scrollSpeedFieldName);
                        mf.setAccessible(true);
                        mf.setFloat(marquee, speed);
                    }
                } else {
                    Logger.e("Marquee", "mMarquee object is null.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public OnUiUpdatedListener getOnUiUpdatedListener() {
        return onUiUpdatedListener;
    }

    public void setOnUiUpdatedListener(OnUiUpdatedListener onUiUpdatedListener) {
        this.onUiUpdatedListener = onUiUpdatedListener;
    }
}
