package com.magic.mybanjir.Provider;

import com.magic.mybanjir.models.Item;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by admin on 12/26/14.
 */
public interface NewsProvider {

    @GET("/api/items")
    void getNews(Callback<List<Item>> callback);
}
