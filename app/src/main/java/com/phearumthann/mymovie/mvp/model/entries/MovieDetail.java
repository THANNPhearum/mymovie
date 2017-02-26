package com.phearumthann.mymovie.mvp.model.entries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public class MovieDetail extends Movie {

    @SerializedName("runtime")
    private int mDuration;

    @SerializedName("original_language")
    private String mOriginalLanguage;

    @SerializedName("genres")
    private List<Genre> mGenres;

    @SerializedName("overview")
    private String mOverview;


    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        this.mGenres = genres;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.mOriginalLanguage = originalLanguage;
    }
}
