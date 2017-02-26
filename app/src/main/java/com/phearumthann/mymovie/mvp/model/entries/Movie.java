package com.phearumthann.mymovie.mvp.model.entries;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
public class Movie {

    @SerializedName("id")
    private long mId;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("popularity")
    private double mPopularity;


    public Movie() {
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static Movie fromJson(String json) {
        return new Gson().fromJson(json, Movie.class);
    }

    public static String toJson(Movie movie) {
        return new Gson().toJson(movie);
    }
}
