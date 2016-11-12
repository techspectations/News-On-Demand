package com.manorama.techspectations.new_management;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.manorama.techspectations.database.tables.TableNews;
import com.manorama.techspectations.model.ManoramaArticle;
import com.manorama.techspectations.model.News;

import java.util.ArrayList;

/**
 * Created by Albi on 11/12/2016.
 */

public class NewsDbManagement {

    Context mContext;
    ContentResolver mContentResolver;
    public NewsDbManagement(Context context) {

        this.mContext = context;
        this.mContentResolver = mContext.getContentResolver();
    }

    public void addOrUpdateArticleDetails(ManoramaArticle article){

        if(isArticleExists(article.getArticleID())){
            updateArticle(article);
        }else{
            addArticleDetails(article);
        }
    }

    private void addArticleDetails(ManoramaArticle article){

        ContentValues cv = getContentValues(article);
        mContentResolver.insert(TableNews.CONTENT_URI, cv);
    }

    private void updateArticle(ManoramaArticle article){

        String where = TableNews.NEWS_ARTICLE_ID + " = " + article.getArticleID();
        ContentValues cv = getContentValues(article);
        mContentResolver.update(TableNews.CONTENT_URI, cv, where, null);
    }

    private boolean isArticleExists(String articleId){

        String where = TableNews.NEWS_ARTICLE_ID + " = " + articleId;
        boolean exists = false;

        Cursor curs = mContentResolver.query(TableNews.CONTENT_URI, null, where, null,null);
        if(curs != null && curs.getCount() > 0)
            exists = true;
        return exists;
    }

    private ContentValues getContentValues(ManoramaArticle article){

        ContentValues cv = new ContentValues();
        cv.put(TableNews.NEWS_ARTICLE_ID, article.getArticleID());
        cv.put(TableNews.NEWS_HEADING, article.getTitle());
        cv.put(TableNews.NEWS_ARTICLE_URI, article.getArticleURL());
        cv.put(TableNews.NEWS_WEB_IMAGE_URI, article.getImgWeb());
        cv.put(TableNews.NEWS_MOBILE_IMAGE_URI, article.getImgMob());
        cv.put(TableNews.NEWS_THUMBNAIL_URI, article.getThumbnail());
        cv.put(TableNews.NEWS_ARTICLE_SECTION, article.getArticleSection());
        return cv;
    }

}
