package com.ghj.barcode.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.Objects;

public class YJSQLite {
    public static String DB_NAME = "qr_barcode.db";
    public static int DB_VERSION = 1;

    private static YJSQLite self = null;

    private YJSQLiteHelper helper;
    private SQLiteDatabase readDB;
    private SQLiteDatabase writeDB;

    private YJSQLite(Context context) {
        if(helper == null) {
            helper = new YJSQLiteHelper(context);
            readDB = helper.getReadableDatabase();
            writeDB = helper.getWritableDatabase();
        }
    }

    public static YJSQLite getInstance(Context context) {
        synchronized (YJSQLite.class) {
            if(self == null) {
                self = new YJSQLite(context);
            }
        }
        return self;
    }

    public void close() {
        if(readDB != null) {
            readDB.close();
        }
        if(writeDB != null) {
            writeDB.close();
        }
        if(helper != null) {
            helper.close();
            helper = null;
        }
        self = null;
    }

    public void select(String sql, String[] params, ISQLiteListener listener) {
        synchronized (this) {
            if(readDB == null) return;

            try {
                Cursor cursor = readDB.rawQuery(sql, params);
                if(listener != null) {
                    listener.Select(cursor);
                }
                cursor.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean execSQL(String sql, Object[] params) {
        synchronized (this) {
            if (writeDB == null) return false;

            writeDB.beginTransaction();
            try {
                if(params == null) {
                    writeDB.execSQL(sql);
                }
                else {
                    writeDB.execSQL(sql, params);
                }
                writeDB.setTransactionSuccessful();
                return true;
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            finally {
                writeDB.endTransaction();
            }
        }
    }
}
