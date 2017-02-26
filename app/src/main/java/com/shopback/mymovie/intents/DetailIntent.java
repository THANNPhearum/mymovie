package com.shopback.mymovie.intents;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.shopback.mymovie.mvp.model.entries.Movie;
import com.shopback.mymovie.ui.activities.DetailActivity;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public class DetailIntent extends Intent {
    private static final String EXTRA_MOVIE = "extra_movie";

    public DetailIntent(Context context, @NonNull Movie movie) {
        super(context, DetailActivity.class);
        putExtra(EXTRA_MOVIE, Movie.toJson(movie));
    }

    public DetailIntent(Intent intent) {
        super(intent);
    }

    @Nullable
    public Movie getMovie() {
        final String json = getStringExtra(EXTRA_MOVIE);
        return Movie.fromJson(json);
    }
}
