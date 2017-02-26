package com.shopback.mymovie.ui.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopback.mymovie.MyMovieApp;
import com.shopback.mymovie.R;
import com.shopback.mymovie.adapters.MovieAdapter;
import com.shopback.mymovie.dagger.component.DaggerHomeComponent;
import com.shopback.mymovie.dagger.component.HomeComponent;
import com.shopback.mymovie.databinding.FragmentHomeBinding;
import com.shopback.mymovie.intents.DetailIntent;
import com.shopback.mymovie.mvp.model.entries.Movie;
import com.shopback.mymovie.mvp.presenters.HomePresenter;
import com.shopback.mymovie.mvp.views.MovieView;

import java.util.List;

/**
 * Created by PhearumThann on 2/22/2017.
 * phearumandroid@gmail.com
 */

public class MovieFragment extends BasePresenterControllerFragment<HomeComponent, HomePresenter>
        implements MovieView, MovieAdapter.Callback {
    private FragmentHomeBinding mMoviesBinding;
    private MovieAdapter mMovieAdapter;
    private Callback mCallback;
    private Parcelable mRecyclerViewState;
    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mRecyclerViewState = mMoviesBinding.movies.getLayoutManager().onSaveInstanceState();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMoviesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mMoviesBinding.getRoot();
    }

    @Override
    protected HomeComponent onCreateNonConfigurationComponent() {
        return DaggerHomeComponent.builder()
                .appComponent(MyMovieApp.getAppComponent(getActivity())).build();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = getContext();
        mMovieAdapter = new MovieAdapter(context);
        mMovieAdapter.setCallback(this);
        mLayoutManager = getLayoutManager();
        mMoviesBinding.movies.setLayoutManager(mLayoutManager);
        mMoviesBinding.movies.setAdapter(mMovieAdapter);

        mMoviesBinding.swipeContainer.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getPresenter().resetPage();
                        mMovieAdapter.clear();
                        getPresenter().loadRemoteDataMovies();
                    }
                });


        showProgress();
        getPresenter().loadRemoteDataMovies();
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(getSpanCount(), StaggeredGridLayoutManager.VERTICAL);
    }

    private int getSpanCount() {
        return getResources().getInteger(R.integer.grid_columns);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mRecyclerViewState != null) {
            mLayoutManager.onRestoreInstanceState(mRecyclerViewState);
        }
        mLayoutManager.setSpanCount(getSpanCount());
        mMoviesBinding.movies.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onGetMoviesSuccess(List<Movie> movies, boolean hasMore) {
        hideProgress();
        mMoviesBinding.swipeContainer.setRefreshing(false);
        mMovieAdapter.setMoreDataAvailable(hasMore);
        if (mMovieAdapter.isEmpty()) {
            mCallback.onGetHighLightsMovies(movies);
        }
        mMovieAdapter.addAll(movies);
    }

    @Override
    public void onGetMoviesFailure() {
        hideProgress();
        mMoviesBinding.swipeContainer.setRefreshing(false);
    }

    @Override
    public Context getContextFromView() {
        return getActivity();
    }

    @Override
    public void onItemClicked(Movie movie) {
        startActivity(new DetailIntent(getContext(), movie));
    }

    @Override
    public void onLoadMoreItem() {
        showProgress();
        getPresenter().increasePage();
        getPresenter().loadRemoteDataMovies();
    }

    public interface Callback {
        void onGetHighLightsMovies(List<Movie> movies);
    }
}
