package com.iroid.mvpiroid.api;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Rishad
 * @since 16/03/2019
 */

public class ApiClient {

    public static final String BASE_URL = "http://18.217.32.254/"; // local
    //public static final String BASE_URL = "http://18.188.160.47/"; // demo
    //public static final String BASE_URL = "http:live.com/"; // live


    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder httpClient = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL + "api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient().build())
                    .build();
        }
        return retrofit;
    }


    private static OkHttpClient.Builder getHttpClient() {
        if (httpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30000, TimeUnit.SECONDS);
            httpClient.readTimeout(30000, TimeUnit.SECONDS).build();
            httpClient.addInterceptor(logging);
        }
        return httpClient;
    }
}
