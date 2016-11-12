package com.manorama.techspectations.interfaces;

import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.model.News;

import java.util.ArrayList;

/**
 * Created by Albi on 11/11/2016.
 */

public interface NewsInteractorListener {

    void onGetBreakingNewsSuccess(ArrayList<BreakingNews> breakingNewsList);

    void onGetBreakingNewsFailed(int errorCode, String errorMsg);

    void onGetNewsDetailSuccess(News newsDetails);
    void onGetNewsDetailFailed(int errorCode, String errorMsg);
}
