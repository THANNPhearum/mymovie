package com.shopback.mymovie.intents;

import android.content.Context;
import android.content.Intent;

import com.shopback.mymovie.ui.activities.BookingActivity;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public class BookingIntent extends Intent {
    private static final String EXTRA_URL = "extra_url";

    public BookingIntent(Context context, String url) {
        super(context, BookingActivity.class);
        putExtra(EXTRA_URL, url);
    }

    public BookingIntent(Intent intent) {
        super(intent);
    }

    public String getUrl() {
        return getStringExtra(EXTRA_URL);
    }

}
