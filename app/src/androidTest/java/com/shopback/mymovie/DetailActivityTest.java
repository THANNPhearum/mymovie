package com.shopback.mymovie;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.shopback.mymovie.intents.DetailIntent;
import com.shopback.mymovie.mvp.model.entries.Movie;
import com.shopback.mymovie.ui.activities.DetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> mRule =
            new ActivityTestRule<>(DetailActivity.class, true, false);

    @Test
    public void checkDetailScreen() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        startDetailScreen(appContext, getTestMovie());
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Tulip Fever")).check(matches(isDisplayed()));
    }

    private void startDetailScreen(Context context, Movie movie) {
        DetailIntent intent = new DetailIntent(context, getTestMovie());
        mRule.launchActivity(intent);
    }

    private Movie getTestMovie() {
        Movie movie = new Movie();
        movie.setId(257785);
        movie.setTitle("Tulip Fever");
        return movie;
    }

}
