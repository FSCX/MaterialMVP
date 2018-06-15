package com.fsc.newsnets.utils;

import android.util.Log;

/**
 * 日志打印工具类
 */
public class LogUtils {
    private static final boolean DEBUG = true;

    public static void v(String tag,String message){
        if(DEBUG){
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }
    public static void w(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }
    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }
    public static void e(String tag, String message,Exception e) {
        if (DEBUG) {
            Log.i(tag, message,e);
        }
    }
}
