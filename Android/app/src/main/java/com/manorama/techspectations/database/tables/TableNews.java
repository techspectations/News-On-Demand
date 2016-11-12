package com.manorama.techspectations.database.tables;

import android.net.Uri;

import com.manorama.techspectations.util.Constants;

/**
 * Created by Godwin Joseph on 12-11-2016 08:46 for Techspectations application.
 */

public class TableNews {
    public static final int TABLE_INDEX = Constants.DatabaseConstants.TABLE_NEWS_INDEX;
    public static final String TABLE_NAME = Constants.DatabaseConstants.TABLE_NEWS;

    public static final String ROW_INDEX = "ROW_INDEX";
    public static final String NEWS_ID = "NEWS_ID";
    public static final String NEWS_ARTICLE_ID = "NEWS_ARTICLE_ID";
    public static final String NEWS_HEADING = "NEWS_HEADING";
    public static final String NEWS_ARTICLE_URI = "NEWS_ARTICLE_URI";

    public static final String NEWS_THUMBNAIL_URI = "NEWS_THUMBNAIL_URI";
    public static final String NEWS_MOBILE_IMAGE_URI = "NEWS_MOBILE_IMAGE_URI";
    public static final String NEWS_WEB_IMAGE_URI = "NEWS_WEB_IMAGE_URI";

    public static final String NEWS_OTHER_IMAGES = "NEWS_OTHER_IMAGES";
    public static final String NEWS_VIDEO = "NEWS_VIDEO";

    public static final String NEWS_ARTICLE_SECTION = "NEWS_ARTICLE_SECTION";

    public static final String NEWS_UPDATED_TIME = "NEWS_UPDATED_TIME";
    public static final String NEWS_PROVIDER = "NEWS_PROVIDER";

    public static final String NEWS_AVERAGE_RATING = "NEWS_AVERAGE_RATING";
    public static final String NEWS_CONTENT = "NEWS_CONTENT";

    public static final String NEWS_AUTHOR = "NEWS_AUTHOR";
    public static final String NEWS_AUTHOR_EMAIL = "NEWS_AUTHOR_EMAIL";
    public static final String NEWS_AUTHOR_IMAGE_URI = "NEWS_AUTHOR_IMAGE_URI";
    public static final String NEWS_AUTHOR_DESIGNATION = "NEWS_AUTHOR_DESIGNATION";

    public static final String NEWS_IS_OFFLINE = "NEWS_IS_OFFLINE";

    public static final Uri CONTENT_URI = Uri.parse("content://" + Constants.DatabaseConstants.AUTHORITY + "/" + TABLE_NAME);
    public static final String SQL_CREATE_TABLE = String.format("create table %s ("
                    + "%s integer PRIMARY KEY AUTOINCREMENT,%s text,%s text,%s text," +
                    "%s text,%s text,%s text,%s text," +
                    "%s text,%s text,%s text," +
                    "%s text,%s text,%s text," +
                    "%s text,%s text,%s text,%s text," +
                    "%s text,%s text)",
            TABLE_NAME,
            ROW_INDEX, NEWS_ID, NEWS_ARTICLE_ID, NEWS_HEADING,
            NEWS_ARTICLE_URI, NEWS_THUMBNAIL_URI, NEWS_MOBILE_IMAGE_URI, NEWS_WEB_IMAGE_URI,
            NEWS_OTHER_IMAGES, NEWS_VIDEO, NEWS_ARTICLE_SECTION,
            NEWS_UPDATED_TIME, NEWS_PROVIDER, NEWS_AVERAGE_RATING,
            NEWS_CONTENT, NEWS_AUTHOR, NEWS_AUTHOR_EMAIL, NEWS_AUTHOR_IMAGE_URI,
            NEWS_AUTHOR_DESIGNATION, NEWS_IS_OFFLINE);

}
