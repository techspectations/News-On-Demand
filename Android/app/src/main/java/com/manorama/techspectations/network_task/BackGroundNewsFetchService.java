package com.manorama.techspectations.network_task;

import android.content.Intent;

import com.manorama.techspectations.facebook.MyApplication;
import com.manorama.techspectations.interfaces.NewsInteractorListener;
import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.model.News;
import com.manorama.techspectations.new_management.NewsInteractor;
import com.manorama.techspectations.util.Constants;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Godwin Joseph on 11-11-2016 20:50 for Techspectations application.
 */

public class BackGroundNewsFetchService implements NewsInteractorListener {
    private static BackGroundNewsFetchService service = new BackGroundNewsFetchService();

    public static BackGroundNewsFetchService getInstance() {
        return service;
    }

    private BackGroundNewsFetchService() {
    }

    Timer mTimer = null;

    public void startTimer() {
        stopTimer();
        mTimer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                NewsInteractor interactor = new NewsInteractor(MyApplication.getAppContext(), BackGroundNewsFetchService.this);
                //interactor.getAllBreakingNews();
                interactor.getAllNewBasedOnUserData();
            }
        };
        mTimer.schedule(task, 0, 30 * 1000);
    }

    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onGetBreakingNewsSuccess(ArrayList<BreakingNews> breakingNewsList) {
        Intent intent = new Intent();
        intent.setAction(Constants.IntentConstants.ACTION_DATA_UPDATED_BROADCAST);
        MyApplication.getAppContext().sendBroadcast(intent);
    }

    @Override
    public void onGetBreakingNewsFailed(int errorCode, String errorMsg) {

    }

    @Override
    public void onGetNewsDetailSuccess(News newsDetails) {

    }

    @Override
    public void onGetNewsDetailFailed(int errorCode, String errorMsg) {

    }
}
