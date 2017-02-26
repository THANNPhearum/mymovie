package com.shopback.mymovie.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopback.mymovie.MyMovieApp;
import com.shopback.mymovie.R;
import com.shopback.mymovie.dagger.component.DaggerDetailComponent;
import com.shopback.mymovie.dagger.component.DetailComponent;
import com.shopback.mymovie.databinding.FragmentDetailBinding;
import com.shopback.mymovie.intents.BookingIntent;
import com.shopback.mymovie.mvp.model.entries.Movie;
import com.shopback.mymovie.mvp.model.entries.MovieDetail;
import com.shopback.mymovie.mvp.presenters.DetailPresenter;
import com.shopback.mymovie.mvp.views.DetailView;
import com.shopback.mymovie.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.Locale;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public class DetailFragment extends BasePresenterControllerFragment<DetailComponent, DetailPresenter>
        implements DetailView {
    private static final String EXTRA_MOVIE = "extra_movie";

    private static final String BOOKING_MOVIE_URL = "http://www.cathaycineplexes.com.sg/";
    private FragmentDetailBinding mDetailBinding;
    private Movie mMovie;
    private Picasso mPicasso;
    private String mImageSize;

    public static DetailFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putString(EXTRA_MOVIE, Movie.toJson(movie));
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPicasso = MyMovieApp.getAppComponent(getContext()).picasso();
        mMovie = Movie.fromJson(getArguments().getString(EXTRA_MOVIE));
        mImageSize = getString(R.string.image_size);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        return mDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        showProgress();
        getPresenter().loadMovieById(mMovie.getId());
        mDetailBinding.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new BookingIntent(getContext(), BOOKING_MOVIE_URL));
            }
        });
    }

    @Override
    protected DetailComponent onCreateNonConfigurationComponent() {
        return DaggerDetailComponent.builder()
                .appComponent(MyMovieApp.getAppComponent(getActivity())).build();
    }

    @Override
    public void onGetMovieDetailSuccess(MovieDetail movieDetail) {
        hideProgress();

        mDetailBinding.collapsingToolbar.setTitle(movieDetail.getTitle());
        mDetailBinding.collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(getContext(),
                android.R.color.transparent));

        final String posterUrl = getImagePath(mMovie, movieDetail, true);
        mPicasso.load(Util.getImageUrl(mImageSize, posterUrl)).into(mDetailBinding.poster);

        final String backDropUrl = getImagePath(mMovie, movieDetail, false);
        mPicasso.load(Util.getImageUrl(mImageSize, backDropUrl)).into(mDetailBinding.backdrop);

        mDetailBinding.title.setText(movieDetail.getTitle());

        String genres = Util.getGenresText(movieDetail.getGenres());
        if (!TextUtils.isEmpty(genres)) {
            mDetailBinding.genres.setText(genres);
            mDetailBinding.genres.setVisibility(View.VISIBLE);
        }

        if (movieDetail.getDuration() > 0) {
            mDetailBinding.duration.setText(Util.getRuntimeText(movieDetail.getDuration()));
            mDetailBinding.duration.setVisibility(View.VISIBLE);
        }

        final Locale locale = new Locale(movieDetail.getOriginalLanguage(), "");
        if (!TextUtils.isEmpty(locale.getDisplayLanguage())) {
            mDetailBinding.language.setText(locale.getDisplayLanguage());
            mDetailBinding.language.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(movieDetail.getOverview())) {
            mDetailBinding.overview.setText(movieDetail.getOverview());
            mDetailBinding.overviewLayout.setVisibility(View.VISIBLE);
        }
        mDetailBinding.popularity.setText(Util.getPopularity(mMovie.getPopularity()));
    }

    //Sometime MovieDetail does not return poster and backdrop path; Use them from Movie instead
    private String getImagePath(Movie movie, MovieDetail movieDetail, boolean isCheckPosterFirst) {
        String posterUrl = Util.getPosterImage(movieDetail.getPosterPath(),
                movieDetail.getBackdropPath(), isCheckPosterFirst);
        if (TextUtils.isEmpty(posterUrl)) {
            posterUrl = Util.getPosterImage(movie.getPosterPath(),
                    movie.getBackdropPath(), isCheckPosterFirst);
        }
        return posterUrl;
    }

    private void initToolbar() {
        final AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
        mDetailBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        compatActivity.setSupportActionBar(mDetailBinding.toolbar);
        mDetailBinding.collapsingToolbar.setTitle(mMovie.getTitle());
    }

    @Override
    public void onGetMovieDetailFailure() {
        hideProgress();
    }

    @Override
    public Context getContextFromView() {
        return getActivity();
    }
}
