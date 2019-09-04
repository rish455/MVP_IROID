package com.iroid.mvpiroid.ui.main.login;

import com.ewaantech.jalboot.common_interfaces.CommonViewInterface;
import com.ewaantech.jalboot.pojo.boat.Boat;

import java.util.ArrayList;

public interface LoginViewInterface extends CommonViewInterface {
    void onLoginSuccess(String message);
    void onLoginFailed(String message);

    void onGetBoats(ArrayList<Boat> boatDataSet);
}
