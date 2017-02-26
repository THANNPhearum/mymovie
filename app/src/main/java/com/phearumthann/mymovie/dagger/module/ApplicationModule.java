package com.phearumthann.mymovie.dagger.module;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.phearumthann.mymovie.MyMovieApp;
import com.phearumthann.mymovie.dagger.scope.AppScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */

@Module
public class ApplicationModule {
    //Total Cache Image Size for Picasso ~ 50MB
    private static final int CACHE_IMAGE_SIZE = 52428800;
    private final MyMovieApp mApp;

    public ApplicationModule(MyMovieApp app) {
        this.mApp = app;
    }

    @Provides
    @AppScope
    public MyMovieApp provideMyMovieApp() {
        return mApp;
    }


    @Provides
    @AppScope
    Picasso providePicasso() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(new Cache(mApp.getCacheDir(), CACHE_IMAGE_SIZE));
        return new Picasso.Builder(mApp)
                .downloader(new OkHttp3Downloader(httpClient.build()))
                .build();
    }
}
