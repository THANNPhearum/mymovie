package com.shopback.mymovie.dagger.component;

import com.shopback.mymovie.dagger.scope.HomeScope;
import com.shopback.mymovie.mvp.presenters.HomePresenter;
import com.shopback.mymovie.ui.fragments.MovieFragment;

import dagger.Component;
import nz.bradcampbell.compartment.HasPresenter;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
@HomeScope
@Component(dependencies = AppComponent.class)
public interface HomeComponent extends HasPresenter<HomePresenter> {
    void inject(MovieFragment fragment);
}