package com.shopback.mymovie.mvp.presenters;

import com.shopback.mymovie.dagger.scope.HomeScope;
import com.shopback.mymovie.mvp.model.MovieModel;
import com.shopback.mymovie.mvp.model.entries.ResponseMovie;
import com.shopback.mymovie.mvp.views.MovieView;
import com.shopback.mymovie.network.BaseNetworkSubscriber;
import com.shopback.mymovie.utils.Logging;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
@HomeScope
public class HomePresenter extends MyBasePresenter<MovieView> {
    private static final String TAG = HomePresenter.class.getSimpleName();
    private final MovieModel mMovieModel;
    private int mPage = 1;

    @Inject
    public HomePresenter(MovieModel movieModel) {
        mMovieModel = movieModel;
    }

    public void loadRemoteDataMovies() {
        final MovieView view = getView();
        bindSubscriberWithLifeCycle(mMovieModel.getRemoteDataMovies(mPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseNetworkSubscriber<ResponseMovie>(view.getContextFromView()) {
                    @Override
                    public void onNext(ResponseMovie responseMovie) {
                        super.onNext(responseMovie);
                        Logging.d(TAG, "Total: " + responseMovie.getTotalPage()
                                + ", Result: " + responseMovie.getTotalPage());
                        final boolean hasMore = mPage <= responseMovie.getTotalPage();
                        view.onGetMoviesSuccess(responseMovie.getMovies(), hasMore);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.onGetMoviesFailure();

                    }
                })
        );
    }

    public void increasePage() {
        mPage++;
    }

    public void resetPage() {
        mPage = 1;
    }


}
