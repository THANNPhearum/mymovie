package com.phearumthann.mymovie.mvp.model;

import com.phearumthann.mymovie.BuildConfig;
import com.phearumthann.mymovie.dagger.scope.AppScope;
import com.phearumthann.mymovie.mvp.model.entries.MovieDetail;
import com.phearumthann.mymovie.mvp.model.entries.ResponseMovie;
import com.phearumthann.mymovie.network.MovieService;
import com.phearumthann.mymovie.utils.Util;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
@AppScope
public class MovieModel {
    private static final String ORDER_DATE_DESC = "release_date.desc";
    private final MovieService mMovieService;

    @Inject
    public MovieModel(MovieService movieService) {
        mMovieService = movieService;
    }

    @DebugLog
    public Observable<ResponseMovie> getRemoteDataMovies(int page) {
        return mMovieService.getMovies(BuildConfig.API_KEY, Util.getCurrentDate(), ORDER_DATE_DESC, page)
                .subscribeOn(Schedulers.io());
    }


    @DebugLog
    public Observable<MovieDetail> getMovieDetail(long id) {
        return mMovieService.getMovieDetail(id, BuildConfig.API_KEY).subscribeOn(Schedulers.io());
    }
}
