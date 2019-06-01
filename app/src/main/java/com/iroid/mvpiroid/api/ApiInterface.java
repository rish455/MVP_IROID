package com.iroid.mvpiroid.api;

import com.google.gson.JsonObject;

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
    @POST("login")
    Call<JsonObject> login(@Field("email") String name,
                           @Field("password") String password);

    @GET("notifications/")
    Call<JsonObject> getNotifications(@Query("boat_id") String boat);


}