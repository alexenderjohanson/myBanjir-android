package com.magic.mybanjir.Provider;

import com.magic.mybanjir.models.RiverLevel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by admin on 12/26/14.
 */
public interface RiverLevelProvider {

    @GET("/api/riverlevel/{state}")
    void getRiverLevel(@Path("state") String state, Callback<List<RiverLevel>> callback);
}
