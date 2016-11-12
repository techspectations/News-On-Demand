package com.manorama.techspectations.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.manorama.techspectations.database.tables.TableNews;
import com.manorama.techspectations.database.tables.TableNewsTagMapping;
import com.manorama.techspectations.util.Constants;

/**
 * Created by Godwin Joseph on 12-11-2016 09:30 for Techspectations application.
 */

public class DatabaseProvider extends ContentProvider {
    private DbHelper mDbHelper;
    private static final UriMatcher uriMatcher;
    private SQLiteDatabase mSqldb;
    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Constants.DatabaseConstants.AUTHORITY, TableNews.TABLE_NAME,
                TableNews.TABLE_INDEX);
        uriMatcher.addURI(Constants.DatabaseConstants.AUTHORITY, TableNewsTagMapping.TABLE_NAME,
                TableNewsTagMapping.TABLE_INDEX);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDbHelper = new DbHelper(context);
        /*
         * Create a write able database which will trigger its creation if it
		 * doesn't already exist.
		 */
        mSqldb = mDbHelper.getWritableDatabase();
        return (mSqldb == null) ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String tableName;
        Cursor cursor = null;

        mSqldb = mDbHelper.getReadableDatabase();
        tableName = uri.getLastPathSegment();
        queryBuilder.setTables(tableName);
        cursor = queryBuilder.query(mSqldb, projection, selection,
                selectionArgs, null, null, sortOrder);
        if (cursor != null)
            cursor.setNotificationUri(getContext().getContentResolver(),
                    uri);

        return cursor;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        mSqldb = mDbHelper.getWritableDatabase();
        long rowID = -1;
        Uri retUri = null;
        String tableName = uri.getPathSegments().get(0);

        rowID = mSqldb.insert(tableName, "", contentValues);
        // ---if added successfully---
        if (rowID > 0) {
            retUri = ContentUris.withAppendedId(uri,
                    rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return retUri;
        }
//         throw new SQLException("Failed to insert row into " + uri);

        return retUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        mSqldb = mDbHelper.getWritableDatabase();
        String tableName = uri.getPathSegments().get(0);
        count = mSqldb.delete(tableName, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        mSqldb = mDbHelper.getWritableDatabase();
        String tableName = uri.getPathSegments().get(0);
        count = mSqldb.update(tableName, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    // Helper Class to operate on DB
    public static class DbHelper extends SQLiteOpenHelper {
        @SuppressWarnings("unused")
        private Context mContext;

        public DbHelper(Context context) {
            super(context, Constants.DatabaseConstants.DB_NAME, null, Constants.DatabaseConstants.DB_VERSION);
            this.mContext = context;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            for (int tableIndex = Constants.DatabaseConstants.TABLE_NEWS_INDEX; tableIndex < (Constants.DatabaseConstants.TABLE_NEWS_INDEX + Constants.DatabaseConstants.TABLE_COUNT); tableIndex++) {
                db.execSQL("drop table if exists " + Constants.DatabaseConstants.getTableName(tableIndex));
            }
            onCreate(db);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*for (int tableIndex = DBConstants.TABLE_USERS; tableIndex < (DBConstants.TABLE_USERS + DBConstants.TABLE_COUNT); tableIndex++) {
                db.execSQL(DBConstants.getCreateTableQuery(tableIndex));
            }*/
            db.execSQL(TableNews.SQL_CREATE_TABLE);
            db.execSQL(TableNewsTagMapping.SQL_CREATE_TABLE);
        }
    }
}