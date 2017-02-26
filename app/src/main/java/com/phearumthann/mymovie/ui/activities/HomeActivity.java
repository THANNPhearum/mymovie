package com.phearumthann.mymovie.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.phearumthann.mymovie.MyMovieApp;
import com.phearumthann.mymovie.R;
import com.phearumthann.mymovie.intents.DetailIntent;
import com.phearumthann.mymovie.mvp.model.entries.Movie;
import com.phearumthann.mymovie.ui.fragments.MovieFragment;
import com.phearumthann.mymovie.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */

public class HomeActivity extends AbstractActivity implements BaseSliderView.OnSliderClickListener,
        MovieFragment.Callback {
    private static final String EXTRA_INDEX = "extra_index";
    private static final int MAX_HIGHLIGHTS_MOVIES = 10;
    private static final int SLIDE_DURATION = 4000;
    private List<Movie> mMoviesSlide = new ArrayList<>();
    private String mImageSize;
    private SliderLayout mSliderLayout;
    private Picasso mPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPicasso = MyMovieApp.getAppComponent(this).picasso();
        mImageSize = getString(R.string.image_size);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new MovieFragment()).commit();

        mSliderLayout = (SliderLayout) findViewById(R.id.slider);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        int index = slider.getBundle().getInt(EXTRA_INDEX);
        try {
            final Movie movie = mMoviesSlide.get(index);
            if (movie != null) {
                startActivity(new DetailIntent(this, movie));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void initSlider(SliderLayout sliderLayout, List<Movie> movies) {
        sliderLayout.removeAllSliders();
        for (int i = 0, size = movies.size(); i < size; i++) {
            final Movie movie = movies.get(i);
            final TextSliderView textSliderView = new TextSliderView(this);
            String imagePath = movie.getBackdropPath();
            if (TextUtils.isEmpty(imagePath)) {
                imagePath = movie.getPosterPath();
            }
            textSliderView.image(Util.getImageUrl(mImageSize, imagePath));
            textSliderView.setScaleType(BaseSliderView.ScaleType.CenterCrop);
            textSliderView.description(movie.getTitle());
            textSliderView.setPicasso(mPicasso);
            textSliderView.setOnSliderClickListener(this);

            final Bundle bundle = new Bundle();
            bundle.putInt(EXTRA_INDEX, i);
            textSliderView.bundle(bundle);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setDuration(SLIDE_DURATION);
        sliderLayout.setVisibility(View.VISIBLE);
    }

    @DebugLog
    @Override
    public void onGetHighLightsMovies(List<Movie> movies) {
        mMoviesSlide.clear();
        if (movies.size() > MAX_HIGHLIGHTS_MOVIES) {
            mMoviesSlide.addAll(new ArrayList<>(movies.subList(0, MAX_HIGHLIGHTS_MOVIES)));
        } else {
            mMoviesSlide.addAll(movies);
        }
        initSlider(mSliderLayout, mMoviesSlide);
    }
}
