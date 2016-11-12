package com.manorama.techspectations.util;

import com.manorama.techspectations.database.tables.TableNews;
import com.manorama.techspectations.database.tables.TableNewsTagMapping;

/**
 * Created by Godwin Joseph on 10-11-2016 14:33 for Techspectations application.
 */

public class Constants {
    public static final int INTEGER_INITIALIZE_VALUE = -1;
    public static final String STRING_INITIALIZE_VALUE = "";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss a";

    public static class IntentConstants {
        public static final String BREAKING_NEWS_OBJECT = "BREAKING_NEWS_OBJECT";
        public static final String NEWS_OBJECT = "NEWS_OBJECT";
        public static final String ACTION_CONNECTIVITY = "ACTION_CONNECTIVITY";
        public static final String ACTION_DATA_UPDATED_BROADCAST = "ACTION_DATA_UPDATED_BROADCAST";
        public static final String NEWS_POSITION = "NEWS_POSITION";
        public static final String ARTICLE_ID = "ARTICLE_ID";
    }

    public static class DatabaseConstants {
        public static final String AUTHORITY = "com.manorama.techspectations.DataBaseProvider";

        public static final String DB_NAME = "TECHSPECTATION.db";

        public static final int DB_VERSION = 3;
        /**
         * TABLE NAMES
         */
        public static final String TABLE_NEWS = "TABLE_NEWS";
        public static final String TABLE_NEWS_TAG_MAPPING = "TABLE_TAG_MAPPING";

        /**
         * VERY IMPORTANT TO UPDATE THIS COUNT.
         */
        public static final int TABLE_COUNT = 2;

        public static final int TABLE_NEWS_INDEX = 100;
        public static final int TABLE_NEWS_TAG_MAPPING_INDEX = 101;

        public static String getTableName(int index) {
            switch (index) {
                case TABLE_NEWS_INDEX:
                    return TableNews.TABLE_NAME;
                case TABLE_NEWS_TAG_MAPPING_INDEX:
                    return TableNewsTagMapping.TABLE_NAME;
                default:
                    return null;
            }
        }
    }
}
