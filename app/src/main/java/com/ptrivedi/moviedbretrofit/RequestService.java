package com.ptrivedi.moviedbretrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by pooja on 7/7/2017.
 */
public interface RequestService {

    @GET("search/movie?")
    Call<Bean> getKey(@QueryMap Map<String,String> params);


}
