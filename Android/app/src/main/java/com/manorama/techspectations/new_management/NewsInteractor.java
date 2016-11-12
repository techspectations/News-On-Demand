package com.manorama.techspectations.new_management;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.cloudconnection.CloudAPICallback;
import com.cloudconnection.CloudConnectHttpMethod;
import com.google.gson.Gson;
import com.manorama.techspectations.database.manager.DatabaseManager;
import com.manorama.techspectations.database.tables.TableNews;
import com.manorama.techspectations.interfaces.NewsInteractorListener;
import com.manorama.techspectations.model.ArticleResponse;
import com.manorama.techspectations.model.ManoramaArticle;
import com.manorama.techspectations.model.ManoramaArticleDetails;
import com.manorama.techspectations.util.Common;
import com.manorama.techspectations.util.TechSpectationPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Albi on 11/11/2016.
 */

public class NewsInteractor {

    Context mContext;
    NewsInteractorListener mListener;
    DatabaseManager dbManager;

    private static final String TAG = "NewsInteractor";

    public NewsInteractor(Context context, NewsInteractorListener listener){

        this.mContext = context;
        this.mListener = listener;
        dbManager = DatabaseManager.getInstance();
    }

    public void getAllBreakingNews(){

        CloudAPICallback callback = new CloudAPICallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {

                Gson gson = new Gson();
                ArticleResponse articleList = gson.fromJson(jsonObject.toString(), ArticleResponse.class);

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

                mListener.onGetBreakingNewsFailed(i, s);
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

                if(articleList.articles != null && articleList.articles.size() > 0) {

                    dbManager.clearArticle();
                    for (ManoramaArticle article : articleList.articles) {

                        dbManager.addOrUpdateArticle(article);
                    }
                    // Log.e(TAG, article.getArticleID());
                }

                getAllBreakingNews();
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
        String userId = TechSpectationPreference.getInstance().getStringPrefValue(Common.PreferenceStaticValues.USER_ID);

        String url = Common.AppConstants.BASE_URL + "articles/" + userId;
        if(TextUtils.isEmpty(userId))
            url = Common.AppConstants.BASE_URL + "latestnews";


        HashMap<String, String> header = new HashMap<>();
        header.put("accept", "application/json");
        header.put("content-type", "application/json");

        httpMethod.setHeaderMap(header);
        httpMethod.setUrl(url);
        httpMethod.setRequestType(CloudConnectHttpMethod.GET_METHOD);
        httpMethod.execute();
    }

    public void getDetailsOfNews(String articleId){

        CloudAPICallback callback = new CloudAPICallback() {
        @Override
        public void onSuccess(JSONObject jsonObject) {

            Gson gson = new Gson();
            ManoramaArticleDetails articleDetails = gson.fromJson(jsonObject.toString(), ManoramaArticleDetails.class);
            dbManager.addArticleDetails(articleDetails);
            mListener.onGetNewsDetailSuccess(dbManager.getNews(articleDetails.getArticleID()));
        }

        @Override
        public void onSuccess(JSONArray jsonArray) {

        }

        @Override
        public void onFailure(int i, String s) {

            Log.e(TAG, s);
            mListener.onGetNewsDetailFailed(i, s);
        }
    };

        CloudConnectHttpMethod httpMethod = new CloudConnectHttpMethod(mContext, callback);
        String url = Common.AppConstants.BASE_URL + "article/" + articleId;

        HashMap<String, String> header = new HashMap<>();
        header.put("accept", "application/json");
        header.put("content-type", "application/json");

        httpMethod.setHeaderMap(header);
        httpMethod.setUrl(url);
        httpMethod.setRequestType(CloudConnectHttpMethod.GET_METHOD);
        httpMethod.execute();

    }
}
