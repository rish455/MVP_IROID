package com.iroid.mvpiroid.api;

import com.google.gson.JsonObject;
import com.iroid.mvpiroid.pojo.BaseResponse;
import com.iroid.mvpiroid.pojo.top_rated_movies.MoviesResp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Rishad
 * @since 16/03/2019
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("customer/login")
    Call<BaseResponse> login(@Field("mobile") String name,
                             @Field("password") String password,
                             @Field("device_token") String device_token);

    @GET("movie/top_rated")
    Call<MoviesResp> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );


}