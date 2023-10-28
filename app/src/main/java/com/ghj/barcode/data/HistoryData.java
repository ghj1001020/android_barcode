package com.ghj.barcode.data;

import java.io.Serializable;

public class HistoryData implements Serializable {

    String date;
    String value;

    public HistoryData(String date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
