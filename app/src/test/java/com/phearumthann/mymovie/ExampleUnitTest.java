package com.phearumthann.mymovie;

import com.phearumthann.mymovie.mvp.model.entries.Genre;
import com.phearumthann.mymovie.utils.Util;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {


    @Test
    public void displayGenre() {
        List<Genre> genres = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName("Action");
        genres.add(genre);

        genre = new Genre();
        genre.setName("Comedy");
        genres.add(genre);

        genre = new Genre();
        genre.setName("Adventure");
        genres.add(genre);

        String textExpected = "Action, Comedy, Adventure";
        Assert.assertEquals(textExpected, Util.getGenresText(genres));
    }
}