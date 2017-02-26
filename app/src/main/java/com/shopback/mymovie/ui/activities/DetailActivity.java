package com.shopback.mymovie.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.shopback.mymovie.R;
import com.shopback.mymovie.intents.DetailIntent;
import com.shopback.mymovie.mvp.model.entries.Movie;
import com.shopback.mymovie.ui.fragments.DetailFragment;

/**
 * Created by PhearumThann on 2/21/17.
 * phearumandroid@gmail.com
 */

public class DetailActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DetailIntent detailIntent = new DetailIntent(getIntent());
        final Movie movie = detailIntent.getMovie();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, DetailFragment.newInstance(movie))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
