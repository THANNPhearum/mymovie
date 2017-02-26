package com.phearumthann.mymovie.utils;

import android.text.TextUtils;

import com.phearumthann.mymovie.BuildConfig;
import com.phearumthann.mymovie.mvp.model.entries.Genre;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public final class Util {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String BASE_IMAGE_URL_FORMAT = "%s%s/%s";
    private static final String DURATION_FORMAT = "%dh:%02dmm";
    private static final String POPULARITY_FORMAT = "%.1f";


    private Util() {
        //not called
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(new Date());
    }

    public static String getImageUrl(String imageSize, String path) {
        return String.format(BASE_IMAGE_URL_FORMAT, BuildConfig.IMAGE_URL, imageSize, path);
    }

    public static String getGenresText(List<Genre> genres) {
        String genreText = "";
        if (genres != null) {
            for (int i = 0, size = genres.size(); i < size; i++) {
                genreText = genreText.concat(genres.get(i).getName());
                if (i < size - 1) {
                    genreText = genreText.concat(", ");
                }
            }
        }
        return genreText;
    }

    public static String getRuntimeText(int time) {
        int hours = time / 60;
        int minutes = time % 60;
        return String.format(Locale.getDefault(), DURATION_FORMAT, hours, minutes);
    }

    public static String getPopularity(double popularity) {
        return String.format(Locale.getDefault(), POPULARITY_FORMAT, popularity);
    }

    public static String getPosterImage(String poster, String backdrop, boolean isCheckPosterFirst) {
        if (isCheckPosterFirst) {
            if (TextUtils.isEmpty(poster)) {
                return backdrop;
            } else {
                return poster;
            }
        } else {
            if (TextUtils.isEmpty(backdrop)) {
                return poster;
            } else {
                return backdrop;
            }
        }
    }
}
