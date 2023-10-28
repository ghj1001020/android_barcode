package com.ghj.barcode.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ghj.barcode.define.SQLQuery;

public class YJSQLiteHelper extends SQLiteOpenHelper {

    public YJSQLiteHelper(@Nullable Context context) {
        super(context, YJSQLite.DB_NAME, null, YJSQLite.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery.CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLQuery.DROP_HISTORY_TABLE);
        onCreate(db);
    }
}
