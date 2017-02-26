package com.phearumthann.mymovie.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phearumthann.mymovie.MyMovieApp;
import com.phearumthann.mymovie.R;

import com.phearumthann.mymovie.databinding.ViewMovieBinding;
import com.phearumthann.mymovie.mvp.model.entries.Movie;
import com.phearumthann.mymovie.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhearumThann on 12/13/2016.
 * phearumandroid@gmail.com
 */

public class MovieAdapter extends RecyclerView.Adapter<BindingViewHolder<ViewMovieBinding>> {

    private List<Movie> mMovies = new ArrayList<>();
    private Picasso mPicasso;
    private Callback mCallback;
    private String mImageSize;
    private boolean mIsLoading = false;
    private boolean mIsMoreDataAvailable = true;

    public MovieAdapter(Context context) {
        mPicasso = MyMovieApp.getAppComponent(context).picasso();
        mImageSize = context.getString(R.string.image_size);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public BindingViewHolder<ViewMovieBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.view_movie, parent, false);
        return new BindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ViewMovieBinding> holder, int position) {
        if (mCallback != null && shouldLoadMore(position)) {
            mIsLoading = true;
            mCallback.onLoadMoreItem();
        }

        ViewMovieBinding binding = holder.getBinding();
        final Movie movie = mMovies.get(position);
        binding.title.setText(movie.getTitle());
        binding.popularity.setText(Util.getPopularity(movie.getPopularity()));
        binding.thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.onItemClicked(movie);
                }
            }
        });
        final String posterUrl = Util.getPosterImage(movie.getPosterPath(),
                movie.getBackdropPath(), true);
        mPicasso.load(Util.getImageUrl(mImageSize, posterUrl)).into(binding.thumb);
    }

    private boolean shouldLoadMore(int position) {
        return position >= getItemCount() - 1 && mIsMoreDataAvailable && !mIsLoading;
    }

    public void clear() {
        mMovies.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Movie> list) {
        mMovies.addAll(list);
        notifyDataSetChanged();
        mIsLoading = false;
    }

    public boolean isEmpty() {
        return mMovies.size() == 0;
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        mIsMoreDataAvailable = moreDataAvailable;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public interface Callback {
        void onItemClicked(Movie movie);

        void onLoadMoreItem();
    }
}
