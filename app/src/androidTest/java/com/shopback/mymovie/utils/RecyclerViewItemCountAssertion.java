package com.shopback.mymovie.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public class RecyclerViewItemCountAssertion implements ViewAssertion {
    private final int mExpectedCount;

    public RecyclerViewItemCountAssertion(int mExpectedCount) {
        this.mExpectedCount = mExpectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount(), is(mExpectedCount));
    }
}
