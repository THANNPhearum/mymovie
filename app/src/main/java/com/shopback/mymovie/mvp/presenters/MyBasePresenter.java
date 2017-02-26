package com.shopback.mymovie.mvp.presenters;

import com.shopback.mymovie.mvp.views.BaseView;

import nz.bradcampbell.compartment.BasePresenter;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */

public class MyBasePresenter<V extends BaseView> extends BasePresenter<V> {

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void bindView(V view) {
        mCompositeSubscription = new CompositeSubscription();
        super.bindView(view);
    }

    @Override
    public void unbindView() {
        mCompositeSubscription.unsubscribe();
        super.unbindView();
    }

    protected void bindSubscriberWithLifeCycle(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }
}
