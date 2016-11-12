package com.manorama.techspectations.interfaces;

import com.manorama.techspectations.model.BreakingNews;

import java.util.ArrayList;

/**
 * Created by Albi on 11/11/2016.
 */

public interface NewsInteractorListener {

    void onGetBreakingNewsSuccess(ArrayList<BreakingNews> breakingNewsList);

    void onGetBreakingNewsFailed(int errorCode, String errorMsg);

}
