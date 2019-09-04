package com.iroid.mvpiroid.ui.main.login;

import android.support.annotation.NonNull;

import com.ewaantech.jalboot.api.ApiClient;
import com.ewaantech.jalboot.api.ApiInterface;
import com.ewaantech.jalboot.app_prefs.GlobalPreferManager;
import com.ewaantech.jalboot.pojo.boat.BoatData;
import com.ewaantech.jalboot.utils.Constants;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginPresenterInterface {

    private LoginViewInterface loginViewInterface;
    private ApiInterface apiService;

    LoginPresenter(LoginViewInterface loginViewInterface) {
        this.loginViewInterface = loginViewInterface;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getBoats() {
        //loginViewInterface.showProgressIndicator();
        Call<BoatData> call = apiService.getBoats();
        call.enqueue(new Callback<BoatData>() {
            @Override
            public void onResponse(@NonNull Call<BoatData> call, @NonNull Response<BoatData> response) {

                if (response.isSuccessful()) {
                    BoatData boatData = response.body();
                    if (boatData != null && boatData.getStatus())
                        loginViewInterface.onGetBoats(boatData.getData());
                    else
                        loginViewInterface.onFailed(Constants.ERROR_MESSAGE_SERVER);

                } else
                    loginViewInterface.onFailed(Constants.ERROR_MESSAGE_SERVER);

                //loginViewInterface.dismissProgressIndicator();

            }

            @Override
            public void onFailure(@NonNull Call<BoatData> call, @NonNull Throwable t) {
                loginViewInterface.onServerError(Constants.ERROR_MESSAGE_SERVER);
                //loginViewInterface.dismissProgressIndicator();
            }
        });
    }

    @Override
    public void login(String email, String password, String boatId) {
        loginViewInterface.showProgressIndicator();
        Call<JsonObject> call = apiService.login(email, password, boatId);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {

                if (response.isSuccessful() && response.body() != null ) {
                    JsonObject jsonObject = response.body();
                    if (jsonObject.get(Constants.STATUS).getAsBoolean()) {
                        JsonObject data = jsonObject.get(Constants.DATA).getAsJsonObject();
                    } else
                        loginViewInterface.onLoginFailed(jsonObject.get(Constants.MESSAGE).getAsString());

                } else
                    loginViewInterface.onFailed(Constants.ERROR_MESSAGE_SERVER);

                loginViewInterface.dismissProgressIndicator();
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                loginViewInterface.onServerError(Constants.ERROR_MESSAGE_SERVER);
                loginViewInterface.dismissProgressIndicator();
            }
        });
    }
}
