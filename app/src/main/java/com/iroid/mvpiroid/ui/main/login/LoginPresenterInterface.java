package com.iroid.mvpiroid.ui.main.login;

public interface LoginPresenterInterface {
    void getBoats();
    void login(String email, String password, String boatId);
}
