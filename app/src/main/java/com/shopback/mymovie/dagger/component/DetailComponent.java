package com.shopback.mymovie.dagger.component;

import com.shopback.mymovie.dagger.scope.DetailScope;
import com.shopback.mymovie.mvp.presenters.DetailPresenter;
import com.shopback.mymovie.ui.fragments.DetailFragment;

import dagger.Component;
import nz.bradcampbell.compartment.HasPresenter;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
@DetailScope
@Component(dependencies = AppComponent.class)
public interface DetailComponent extends HasPresenter<DetailPresenter> {
    void inject(DetailFragment fragment);
}