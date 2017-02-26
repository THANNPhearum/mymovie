package com.phearumthann.mymovie.network;

import com.phearumthann.mymovie.mvp.model.entries.MovieDetail;
import com.phearumthann.mymovie.mvp.model.entries.ResponseMovie;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */

public interface MovieService {
    @GET("discover/movie")
    Observable<ResponseMovie> getMovies(@Query("api_key") String key,
                                        @Query("primary_release_date.lte") String date,
                                        @Query("sort_by") String sort,
                                        @Query("page") int page);

    @GET("movie/{id}")
    Observable<MovieDetail> getMovieDetail(@Path("id") long id, @Query("api_key") String key);
}
