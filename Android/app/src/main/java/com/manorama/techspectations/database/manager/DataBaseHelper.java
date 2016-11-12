package com.manorama.techspectations.database.manager;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.manorama.techspectations.database.DatabaseProvider;
import com.manorama.techspectations.facebook.MyApplication;

/**
 * Created by Godwin Joseph on 12-11-2016 10:20 for Techspectations application.
 */

public class DataBaseHelper {
    public long getNumberOfRowsInDatabase(String tableName, String where) {
        DatabaseProvider.DbHelper mDbHelper = new DatabaseProvider.DbHelper(MyApplication.getAppContext());
        SQLiteDatabase mSqldb = mDbHelper.getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(mSqldb, tableName, where);
        mSqldb.close();
        return cnt;
    }
}
