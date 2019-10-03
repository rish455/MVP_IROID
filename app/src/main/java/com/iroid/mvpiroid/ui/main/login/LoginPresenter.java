package com.iroid.mvpiroid.ui.main.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.iroid.mvpiroid.api.ApiClient;
import com.iroid.mvpiroid.api.ApiInterface;
import com.iroid.mvpiroid.pojo.BaseResponse;
import com.iroid.mvpiroid.utils.Constants;
import com.iroid.mvpiroid.utils.NetworkUtils;
import com.iroid.mvpiroid.utils.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginPresenter {

    private LoginViewInterface loginViewInterface;
    private ApiInterface apiService;
    private Context mContext;

    LoginPresenter(LoginViewInterface loginViewInterface, Context context) {
        this.mContext = context;
        this.loginViewInterface = loginViewInterface;
        this.apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    void login(String mobileNo, String password) {

        if (!isDataValid(mobileNo, password))
            return;

        loginViewInterface.showProgressIndicator();
        Call<BaseResponse> call = apiService.login(mobileNo, password, "");
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse> call, @NonNull Response<BaseResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    BaseResponse resp = response.body();
                    if (resp.isStatus()) {
                        PreferenceUtils.setIsLoggedIn(true);
                        loginViewInterface.onLoginSuccess("Login Success");
                    } else
                        loginViewInterface.onFailed(resp.getMessage());

                } else
                    loginViewInterface.onFailed(Constants.ERROR_MESSAGE_SERVER);

                loginViewInterface.dismissProgressIndicator();
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse> call, @NonNull Throwable t) {
                if (!NetworkUtils.isNetworkConnected(mContext)) {
                    loginViewInterface.onNoInternet(Constants.ERROR_MESSAGE_NO_INTERNET);
                } else loginViewInterface.onServerError(Constants.ERROR_MESSAGE_SERVER);
                loginViewInterface.dismissProgressIndicator();
            }
        });
    }

    private boolean isDataValid(String mob, String pass) {
        boolean isValid = true;
        if (TextUtils.isEmpty(mob)) {
            isValid = false;
            loginViewInterface.onFailed("Please enter mobile no!");
        } else if (TextUtils.isEmpty(pass)) {
            isValid = false;
            loginViewInterface.onFailed("Please enter password!");
        }

        return isValid;
    }
}
