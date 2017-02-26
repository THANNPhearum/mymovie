package com.phearumthann.mymovie.dagger.component;

import com.phearumthann.mymovie.dagger.module.ApplicationModule;
import com.phearumthann.mymovie.dagger.module.RestModule;
import com.phearumthann.mymovie.dagger.scope.AppScope;
import com.phearumthann.mymovie.mvp.model.MovieModel;
import com.phearumthann.mymovie.network.MovieService;
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
