package com.phearumthann.mymovie.network;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.phearumthann.mymovie.MyMovieApp;
import com.phearumthann.mymovie.R;

import java.io.IOException;

import hugo.weaving.DebugLog;
import rx.Subscriber;

/**
 * Created by PhearumThann on 2/22/2017.
 * phearumandroid@gmail.com
 */

public class BaseNetworkSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private Snackbar mMessageBar;

    public BaseNetworkSubscriber(Context context) {
        mContext = context;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof IOException) {
            onNetworkError();
        }
    }

    @Override
    public void onNext(T t) {
        if (!MyMovieApp.isNetworkAvailable(mContext)) {
            onNetworkError();
        }
    }

    @DebugLog
    private void onNetworkError() {
        displayMessage(mContext.getString(R.string.no_network_connection), Snackbar.LENGTH_LONG);
    }


    private void displayMessage(final String message, final int duration) {
        if (mContext instanceof Activity) {
            final Activity activity = (Activity) mContext;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final View parentLayout = activity.findViewById(android.R.id.content);
                    mMessageBar = Snackbar.make(parentLayout, message, duration);
                    mMessageBar.show();
                }
            });
        }
    }
}
