package com.phearumthann.mymovie.dagger.module;

import com.phearumthann.mymovie.BuildConfig;
import com.phearumthann.mymovie.MyMovieApp;
import com.phearumthann.mymovie.dagger.scope.AppScope;
import com.phearumthann.mymovie.network.MovieService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
@Module
public class RestModule {
    //Total Network Request/Response Size for Retrofit ~ 10MB
    private static final int CACHE_SIZE = 10485760;

    @Provides
    @AppScope
    public OkHttpClient provideOkHttpClient(final MyMovieApp app) {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(logging);
        }
        httpClient.cache(new Cache(app.getCacheDir(), CACHE_SIZE));
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                final CacheControl.Builder builder = new CacheControl.Builder();
                if (MyMovieApp.isNetworkAvailable(app)) {
                    request.newBuilder().cacheControl(builder
                            .maxAge(1, TimeUnit.MINUTES)
                            .build());
                } else {
                    request.newBuilder().cacheControl(builder
                            .onlyIfCached()
                            .maxStale(365, TimeUnit.DAYS)
                            .build());
                }
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    @Provides
    @AppScope
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.BASE_URL);
        builder.client(client);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder;
    }

    @Provides
    @AppScope
    public MovieService provideMovieService(Retrofit.Builder builder) {
        return builder.build().create(MovieService.class);
    }
}
