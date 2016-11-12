package com.manorama.techspectations.new_management;

import android.content.Context;
import android.util.Log;

import com.cloudconnection.CloudAPICallback;
import com.cloudconnection.CloudConnectHttpMethod;
import com.google.gson.Gson;
import com.manorama.techspectations.database.manager.DatabaseManager;
import com.manorama.techspectations.database.tables.TableNews;
import com.manorama.techspectations.interfaces.NewsInteractorListener;
import com.manorama.techspectations.model.ArticleResponse;
import com.manorama.techspectations.model.ManoramaArticle;
import com.manorama.techspectations.util.Common;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Albi on 11/11/2016.
 */

public class NewsInteractor {

    Context mContext;
    NewsInteractorListener mListener;
    private static final String TAG = "NewsInteractor";

    public NewsInteractor(Context context, NewsInteractorListener listener){

        this.mContext = context;
        this.mListener = listener;
    }

    public void getAllBreakingNews(){

        CloudAPICallback callback = new CloudAPICallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {

            }

            @Override
            public void onSuccess(JSONArray jsonArray) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        };

        CloudConnectHttpMethod httpMethod = new CloudConnectHttpMethod(mContext, callback);
        String url = Common.AppConstants.BASE_URL + "articles";

        HashMap<String, String> header = new HashMap<>();
        header.put("accept", "application/json");
        header.put("content-type", "application/json");

        httpMethod.setHeaderMap(header);
        httpMethod.setUrl(url);
        httpMethod.setRequestType(CloudConnectHttpMethod.GET_METHOD);
        httpMethod.execute();
    }

    public void getAllNewBasedOnUserData(){

        CloudAPICallback callback = new CloudAPICallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {

                Log.e(TAG, jsonObject.toString());

                Gson gson = new Gson();
                ArticleResponse articleList = gson.fromJson(jsonObject.toString(), ArticleResponse.class);
                DatabaseManager dbManager = DatabaseManager.getInstance();

                if(articleList.articles != null && articleList.articles.size() > 0) {

                    dbManager.clearArticle();
                    for (ManoramaArticle article : articleList.articles) {

                        dbManager.addOrUpdateArticle(article);
                    }
                    // Log.e(TAG, article.getArticleID());
                }

                mListener.onGetBreakingNewsSuccess(dbManager.getBreakingNews());
            }

            @Override
            public void onSuccess(JSONArray jsonArray) {

            }

            @Override
            public void onFailure(int i, String s) {

                Log.e(TAG, s);
                mListener.onGetBreakingNewsFailed(i, s);
            }
        };

        CloudConnectHttpMethod httpMethod = new CloudConnectHttpMethod(mContext, callback);
        long userId = 0;

        String url = Common.AppConstants.BASE_URL + "articles/" + userId;
        if(userId <= 0)
            url = Common.AppConstants.BASE_URL + "latestnews";


        HashMap<String, String> header = new HashMap<>();
        header.put("accept", "application/json");
        header.put("content-type", "application/json");

        httpMethod.setHeaderMap(header);
        httpMethod.setUrl(url);
        httpMethod.setRequestType(CloudConnectHttpMethod.GET_METHOD);
        httpMethod.execute();
    }
}
