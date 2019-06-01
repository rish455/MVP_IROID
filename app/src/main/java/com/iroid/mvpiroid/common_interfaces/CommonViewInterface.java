package com.iroid.mvpiroid.common_interfaces;

/**
 * @author Rishad
 * @since 16/03/2019
 */

public interface CommonViewInterface {
    void onFailed(String message);

    void onServerError(String message);

    void onNoInternet(String message);

    void dismissProgressIndicator();

    void showProgressIndicator();
}
