package com.shopback.mymovie.dagger.component;

import com.shopback.mymovie.dagger.module.ApplicationModule;
import com.shopback.mymovie.dagger.module.RestModule;
import com.shopback.mymovie.dagger.scope.AppScope;
import com.shopback.mymovie.mvp.model.MovieModel;
import com.shopback.mymovie.network.MovieService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */
@AppScope
@Component(modules = {ApplicationModule.class, RestModule.class})
public interface AppComponent {

    Picasso picasso();

    MovieService movieService();

    MovieModel movieModel();
}
