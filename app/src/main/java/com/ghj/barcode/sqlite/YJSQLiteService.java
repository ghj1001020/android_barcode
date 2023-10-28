package com.ghj.barcode.sqlite;

import android.database.Cursor;

import com.ghj.barcode.BarcodeApp;
import com.ghj.barcode.data.HistoryData;
import com.ghj.barcode.define.SQLQuery;
import com.ghj.barcode.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class YJSQLiteService {

    public static void InsertHistory(String value) {
        if(BarcodeApp.getContext() == null) return;

        String[] params = new String[]{DateUtil.Today("yyyy-MM-dd HH:mm:ss"), value};
        YJSQLite.getInstance(BarcodeApp.getContext()).execSQL(SQLQuery.INSERT_HISTORY, params);
        YJSQLite.getInstance(BarcodeApp.getContext()).close();
    }

    public static List<HistoryData> SelectHistory() {
        List<HistoryData> results = new ArrayList<>();
        if(BarcodeApp.getContext() == null) return results;

        YJSQLite.getInstance(BarcodeApp.getContext()).select(SQLQuery.SELECT_HISTORY, null, new ISQLiteListener() {
            @Override
            public void Select(Cursor cursor) {
                while (cursor.moveToNext()) {
                    String date = cursor.getString(0);
                    String value = cursor.getString(1);
                    results.add(new HistoryData(date, value));
                }
            }
        });
        return results;
    }
}
