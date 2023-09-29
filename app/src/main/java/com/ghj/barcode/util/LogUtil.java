package com.ghj.barcode.util;

import android.text.TextUtils;
import android.util.Log;
import com.ghj.barcode.BuildConfig;

public class LogUtil {
    private static final int MAX_MSG = 1500;
    private static final int LEVEL_DEBUG = 0;
    private static final int LEVEL_ERROR = 1;

    public static void d(String msg) {
        printLog(LEVEL_DEBUG, "MPLog", msg);
    }

    public static void d(String tag, String msg) {
        printLog(LEVEL_DEBUG, tag, msg);
    }

    public static void e(String msg) {
        printLog(LEVEL_ERROR, "MPLog", msg);
    }

    public static void e(String tag, String msg) {
        printLog(LEVEL_ERROR, tag, msg);
    }

    private static void printLog(int level, String tag, String message) {
        if(!BuildConfig.DEBUG) {
            return;
        }

        if(TextUtils.isEmpty(message)) {
            return;
        }

        String className = Thread.currentThread().getStackTrace()[4].getClassName();
        if( !TextUtils.isEmpty(className) ) {
            className = className.substring(className.lastIndexOf(".")+1);
        }
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[4].getLineNumber();
        String head = "[" + className + " > " + methodName + "() Line:" + lineNumber + "] >> ";

        // 글자수 제한
        int length = message.length() / MAX_MSG + 1;
        log(level, tag, "===" + tag + "==========================================================================");
        for(int i=0; i< length; i++) {
            // 바디
            int lastIdx = Math.min((i + 1) * MAX_MSG, message.length());
            String body = message.substring(i*MAX_MSG, lastIdx);

            log(level, tag, head+body);
        }
        log(level, tag, "================================================================================");
    }

    // 출력
    private static void log(int level, String tag, String message) {
        switch(level) {
            case LEVEL_DEBUG:
                Log.d(tag, message);
                break;

            case LEVEL_ERROR:
                Log.e(tag, message);
                break;
        }
    }
}
