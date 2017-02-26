package com.shopback.mymovie;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.shopback.mymovie.dagger.component.AppComponent;
import com.shopback.mymovie.dagger.component.DaggerAppComponent;
import com.shopback.mymovie.dagger.module.ApplicationModule;
import com.shopback.mymovie.dagger.module.RestModule;

/**
 * Created by PhearumThann on 2/20/2017.
 * phearumandroid@gmail.com
 */

public class MyMovieApp extends Application {

    private AppComponent mComponent;

    public static AppComponent getAppComponent(Context context) {
        MyMovieApp app = (MyMovieApp) context.getApplicationContext();
        if (app.mComponent == null) {
            app.mComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(app))
                    .restModule(new RestModule())
                    .build();
        }
        return app.mComponent;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
