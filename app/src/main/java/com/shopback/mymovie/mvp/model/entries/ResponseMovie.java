package com.shopback.mymovie.mvp.model.entries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PhearumThann on 2/23/17.
 * phearumandroid@gmail.com
 */

public class ResponseMovie {
    @SerializedName("page")
    private int mPage;
    @SerializedName("results")
    private List<Movie> mMovies;
    @SerializedName("total_results")
    private int mTotalResult;
    @SerializedName("total_pages")
    private int mTotalPage;

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        this.mPage = page;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        this.mMovies = movies;
    }

    public int getTotalResult() {
        return mTotalResult;
    }

    public void setTotalResult(int totalResult) {
        this.mTotalResult = totalResult;
    }

    public int getTotalPage() {
        return mTotalPage;
    }

    public void setTotalPage(int totalPage) {
        this.mTotalPage = totalPage;
    }
}
