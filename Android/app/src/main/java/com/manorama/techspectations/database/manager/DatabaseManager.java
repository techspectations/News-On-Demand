package com.manorama.techspectations.database.manager;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.manorama.techspectations.database.tables.TableNews;
import com.manorama.techspectations.database.tables.TableNewsTagMapping;
import com.manorama.techspectations.facebook.MyApplication;
import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.model.ManoramaArticle;
import com.manorama.techspectations.model.ManoramaArticleDetails;
import com.manorama.techspectations.model.ManoramaAuthor;
import com.manorama.techspectations.model.News;
import com.manorama.techspectations.model.NewsHeader;

import java.util.ArrayList;

/**
 * Created by Godwin Joseph on 12-11-2016 09:45 for Techspectations application.
 */

public class DatabaseManager extends DataBaseHelper {
    public static DatabaseManager databaseManager = new DatabaseManager();
    ContentResolver mContentResolver;

    public static DatabaseManager getInstance() {
        return databaseManager;
    }

    private DatabaseManager() {
        mContentResolver = MyApplication.getAppContext().getContentResolver();
    }

    /**
     * Add Or update news based on article id
     */
    public void addOrUpdateNews() {

    }

    /**
     * Add news to db
     */
    public void addNews() {

    }

    /**
     * Update news in db
     */
    public void updateNews() {

    }

    public ContentValues getContentValuesFromNews() {
        ContentValues cv = new ContentValues();
        return cv;
    }

    public ArrayList<BreakingNews> getBreakingNews() {
        ArrayList<BreakingNews> breakingNewses = new ArrayList<>();
        String where = null;
        Cursor cursor = mContentResolver.query(TableNews.CONTENT_URI, null, where, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                breakingNewses.add(getNewsFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return breakingNewses;
    }

    public BreakingNews getBreakingNews(String articleId) {
        String where = TableNews.NEWS_ARTICLE_ID + "'" + articleId + "'";
        BreakingNews breakingNewse = null;
        Cursor cursor = mContentResolver.query(TableNews.CONTENT_URI, null, where, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                breakingNewse = getNewsFromCursor(cursor);
            } while (cursor.moveToNext());
        }
        return breakingNewse;
    }

    public void updateFollowing(String articleId) {
        String where = TableNews.NEWS_ARTICLE_ID + "'" + articleId + "'";
        ContentValues cv = new ContentValues();
        cv.put(TableNews.NEWS_IS_OFFLINE, 1);
        mContentResolver.update(TableNews.CONTENT_URI, cv, where, null);
    }

    public ArrayList<News> getSubscribedArticles() {
        ArrayList<News> newses = new ArrayList<>();
        String where = null;
        Cursor cursor = mContentResolver.query(TableNews.CONTENT_URI, null, where, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                newses.add(getNewsFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return newses;
    }

    public ArrayList<News> getNews() {
        ArrayList<News> newses = new ArrayList<>();
        String where = null;
        Cursor cursor = mContentResolver.query(TableNews.CONTENT_URI, null, where, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                newses.add(getNewsFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return newses;
    }

    public ArrayList<NewsHeader> getNewsHeaders() {
        ArrayList<NewsHeader> newses = new ArrayList<>();
        String where = null;
        Cursor cursor = mContentResolver.query(TableNews.CONTENT_URI, null, where, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                newses.add(getNewsFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return newses;
    }

    public News getNews(String articleId) {
        String where = TableNews.NEWS_ARTICLE_ID + " = '" + articleId + "'";
        News news = null;
        Cursor cursor = mContentResolver.query(TableNews.CONTENT_URI, null, where, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                news = getNewsFromCursor(cursor);
            } while (cursor.moveToNext());
        }
        return news;
    }

    private News getNewsFromCursor(Cursor cursor) {
        News news = new News();
        news.setNewsArticleId(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_ARTICLE_ID)));
        news.setNewsId(cursor.getLong(cursor.getColumnIndex(TableNews.NEWS_ID)));

        news.setNewsHeading(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_HEADING)));
        news.setNews(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_CONTENT)));

        news.setNewsMobileImageUrl(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_MOBILE_IMAGE_URI)));

        news.setNewsThumbnailUrl(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_THUMBNAIL_URI)));
        news.setNewsWebImageUrl(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_WEB_IMAGE_URI)));
        news.setNewsEditorName(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_WEB_IMAGE_URI)));

        news.setNewsEditorName(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_AUTHOR)));
        news.setNewsEditorImageUri(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_AUTHOR_IMAGE_URI)));
        news.setNewsEditorEmail(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_AUTHOR_EMAIL)));
        news.setNewsEditorDesignation(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_AUTHOR_DESIGNATION)));

        news.setTotalViews(cursor.getLong(cursor.getColumnIndex(TableNews.NEWS_AVERAGE_RATING)));
        news.setNewsReportedTime(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_UPDATED_TIME)));
        news.setIsOfflineSaved(cursor.getInt(cursor.getColumnIndex(TableNews.NEWS_IS_OFFLINE)));
        news.setNewsSection(cursor.getString(cursor.getColumnIndex(TableNews.NEWS_ARTICLE_SECTION)));

        news.setTags(getTags(news.getNewsArticleId()));
        news.setRelatedArticles(getRelatedArticles(news.getNewsArticleId()));
        return news;
    }

    public void addOrUpdateArticle(ManoramaArticle article) {

        if(isArticleExists(article.getArticleID())){

            updateArticle(article);
        }else
             addArticle(article);
    }

    public void clearArticle() {

        mContentResolver.delete(TableNews.CONTENT_URI, null, null);
    }


    private void addArticle(ManoramaArticle article) {

        ContentValues cv = getContentValues(article);
        mContentResolver.insert(TableNews.CONTENT_URI, cv);
    }

    private void updateArticle(ManoramaArticle article) {

        String where = TableNews.NEWS_ARTICLE_ID + " = '" + article.getArticleID() + "'";
        ContentValues cv = getContentValues(article);
        mContentResolver.update(TableNews.CONTENT_URI, cv, where, null);
    }

    private boolean isArticleExists(String articleId) {

        String where = TableNews.NEWS_ARTICLE_ID + " = '" + articleId + "'";
        boolean exists = false;

        Cursor curs = mContentResolver.query(TableNews.CONTENT_URI, null, where, null, null);
        if (curs != null && curs.getCount() > 0)
            exists = true;
        return exists;
    }

    private ContentValues getContentValues(ManoramaArticle article) {
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

    private ArrayList<String> getTags(String articleId) {
        String where = TableNewsTagMapping.NEWS_ARTICLE_ID + "='" + articleId + "'";
        Cursor cursor = mContentResolver.query(TableNewsTagMapping.CONTENT_URI, null, where, null, null);
        ArrayList<String> tags = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String tag = cursor.getString(cursor.getColumnIndex(TableNewsTagMapping.NEWS_TAG));
                tags.add(tag);
            } while (cursor.moveToNext());
        }
        return tags;
    }

    private ArrayList<String> getRelatedArticles(String articleId) {
        String where = TableNewsTagMapping.NEWS_ARTICLE_ID + "='" + articleId + "'";
        Cursor cursor = mContentResolver.query(TableNewsTagMapping.CONTENT_URI, null, where, null, null);
        ArrayList<String> relatedArticles = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String relatedArticle = cursor.getString(cursor.getColumnIndex(TableNewsTagMapping.NEWS_TAG));
                relatedArticles.add(relatedArticle);
            } while (cursor.moveToNext());
        }
        return relatedArticles;
    }

    public void addArticleDetails(ManoramaArticleDetails details){

        ContentValues cv = getContentValuesOfArticleDetails(details);
        String where = TableNews.NEWS_ARTICLE_ID + " = '" + details.getArticleID() + "'";
        mContentResolver.update(TableNews.CONTENT_URI, cv, where, null);
    }

    private ContentValues getContentValuesOfArticleDetails(ManoramaArticleDetails details){

        ContentValues cv = new ContentValues();
        //cv.put(TableNews.NEWS_ARTICLE_ID, details.getArticleID());
        cv.put(TableNews.NEWS_HEADING, details.getTitle());
        cv.put(TableNews.NEWS_ARTICLE_URI, details.getArticleURL());
        cv.put(TableNews.NEWS_WEB_IMAGE_URI, details.getImgWeb());
        cv.put(TableNews.NEWS_MOBILE_IMAGE_URI, details.getImgMob());
        cv.put(TableNews.NEWS_THUMBNAIL_URI, details.getThumbnail());
        cv.put(TableNews.NEWS_CONTENT, details.getContent());
        cv.put(TableNews.NEWS_UPDATED_TIME, details.getLastModified());

        ManoramaAuthor author = details.getAuthorDetails();

        if(author != null) {
            cv.put(TableNews.NEWS_AUTHOR, author.getAuthorname());
            cv.put(TableNews.NEWS_AUTHOR_DESIGNATION, author.getAuthorDesignation());
            cv.put(TableNews.NEWS_AUTHOR_EMAIL, author.getAuthoremail());
            cv.put(TableNews.NEWS_AUTHOR_IMAGE_URI, author.getAuthorimage());
        }
        return cv;
    }
}
