package com.shopback.mymovie.mvp.views;

import com.shopback.mymovie.mvp.model.entries.MovieDetail;

/**
 * Created by PhearumThann on 2/26/2017.
 * phearumandroid@gmail.com
 */

public interface DetailView extends BaseView {
    void onGetMovieDetailSuccess(MovieDetail movieDetail);

    void onGetMovieDetailFailure();
}