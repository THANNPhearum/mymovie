package com.shopback.mymovie.mvp.model.entries;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PhearumThann on 2/24/2017.
 * phearumandroid@gmail.com
 */

public class Genre {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
