package com.phearumthann.mymovie.mvp.presenters;

import com.phearumthann.mymovie.dagger.scope.DetailScope;
import com.phearumthann.mymovie.mvp.model.MovieModel;
import com.phearumthann.mymovie.mvp.model.entries.MovieDetail;
import com.phearumthann.mymovie.mvp.views.DetailView;
import com.phearumthann.mymovie.network.BaseNetworkSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */
@DetailScope
public class DetailPresenter extends MyBasePresenter<DetailView> {
    private final MovieModel mMovieModel;

    @Inject
    public DetailPresenter(MovieModel movieModel) {
        mMovieModel = movieModel;
    }

    public void loadMovieById(long id) {
        final DetailView view = getView();
        bindSubscriberWithLifeCycle(mMovieModel.getMovieDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseNetworkSubscriber<MovieDetail>(view.getContextFromView()) {
                    @Override
                    public void onNext(MovieDetail movieDetail) {
                        super.onNext(movieDetail);
                        view.onGetMovieDetailSuccess(movieDetail);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.onGetMovieDetailFailure();
                    }
                }));
    }


}
