package com.manorama.techspectations.database.tables;

import android.net.Uri;

import com.manorama.techspectations.util.Constants;

/**
 * Created by Godwin Joseph on 12-11-2016 09:21 for Techspectations application.
 */

public class TableNewsTagMapping {
    public static final int TABLE_INDEX = Constants.DatabaseConstants.TABLE_NEWS_TAG_MAPPING_INDEX;
    public static final String TABLE_NAME = Constants.DatabaseConstants.TABLE_NEWS_TAG_MAPPING;

    public static final String ROW_INDEX = "ROW_INDEX";
    public static final String NEWS_ARTICLE_ID = "NEWS_ARTICLE_ID";
    public static final String NEWS_TAG = "NEWS_TAG";
    public static final String NEWS_RELATED_ARTICLE_ID = "NEWS_RELATED_ARTICLE_ID";

    public static final Uri CONTENT_URI = Uri.parse("content://" + Constants.DatabaseConstants.AUTHORITY + "/" + TABLE_NAME);
    public static final String SQL_CREATE_TABLE = String.format("create table %s ("
                    + "%s integer PRIMARY KEY AUTOINCREMENT,%s text,%s text,%s text)",
            TABLE_NAME,
            ROW_INDEX, NEWS_ARTICLE_ID, NEWS_TAG, NEWS_RELATED_ARTICLE_ID);
}
