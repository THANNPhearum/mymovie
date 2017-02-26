package com.shopback.mymovie.ui.fragments;

import com.shopback.mymovie.ui.dialogs.ProgressDialog;

import nz.bradcampbell.compartment.HasPresenter;
import nz.bradcampbell.compartment.Presenter;
import nz.bradcampbell.compartment.PresenterControllerFragment;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */

public abstract class BasePresenterControllerFragment<C extends HasPresenter<P>, P extends Presenter>
        extends PresenterControllerFragment<C, P> {

    protected void showProgress() {
        ProgressDialog.show(getFragmentManager());
    }

    protected void hideProgress() {
        ProgressDialog.hide(getFragmentManager());
    }
}