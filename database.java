package com.example.tlqkf.timer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tlqkf on 2016-10-25.
 */

public class database {
    private static final String DATABASE_NAME = "first_DB";
    private static final String DATABASE_TABLE = "first_table";
    private static final int DATABASE_VERSION = 2;
    private final Context mCtx;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static final String DATABASE_CREATE="CREATE TABLE " +DATABASE_TABLE+ "" +
            "(    ID    INTEGER PRIMARY    KEY AUTOINCREMENT,    HOUR INTEGER,    MINUTE INTEGER,    SECOND INTEGER,    POINT_SECOND INTEGER)";

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_CREATE);
            onCreate(db);
        }
    }

    public void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }

    public database(Context ctx) {
        this.mCtx = ctx;
    }

    public long insert(int hour, int minute, int second, int point_second) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("HOUR", hour);
        insertValues.put("MINUTE", minute);
        insertValues.put("SECOND", second);
        insertValues.put("POINT_SECOND", point_second);
        return mDb.insert(DATABASE_TABLE,null, insertValues);
    }

    public Cursor AllRows() {
        return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
}