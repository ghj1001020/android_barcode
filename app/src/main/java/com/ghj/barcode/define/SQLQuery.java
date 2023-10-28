package com.ghj.barcode.define;

public interface SQLQuery {

    String CREATE_HISTORY_TABLE = "CREATE TABLE IF NOT EXISTS TBL_HISTORY (         " +
                                  " H_ID    INTEGER     PRIMARY KEY AUTOINCREMENT , " +
                                  " H_DATE  VARCHAR(14) NOT NULL, " +
                                  " H_VALUE TEXT        NOT NULL  " +
                                  ");";

    String DROP_HISTORY_TABLE = "DROP TABLE IF EXISTS TBL_HISTORY";

    String INSERT_HISTORY = "INSERT INTO TBL_HISTORY (H_DATE, H_VALUE) " +
                            "                 VALUES (?, ?)";

    String SELECT_HISTORY = "SELECT   H_DATE, H_VALUE " +
                            "FROM     TBL_HISTORY     " +
                            "ORDER BY H_ID DESC";
}
