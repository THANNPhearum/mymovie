package com.phearumthann.mymovie.utils;

import android.util.Log;

import com.phearumthann.mymovie.BuildConfig;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public final class Logging {
    private Logging() {
        //not called
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
}
