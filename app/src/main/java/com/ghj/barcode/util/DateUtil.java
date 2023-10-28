package com.ghj.barcode.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    // Date Format으로 날짜반환
    public static String Today(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
        return sdf.format(new Date());
    }
}
