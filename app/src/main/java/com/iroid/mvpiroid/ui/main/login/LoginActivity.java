package com.iroid.mvpiroid.ui.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iroid.mvpiroid.R;
import com.iroid.mvpiroid.ui.base.BaseActivity;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginViewInterface {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private LoginPresenterInterface loginPresenterInterface;

    private ArrayList<Boat> mBoatDataSet = new ArrayList<>();
    private Boat mCurrentBoat;

    private TextView tvChooseBoat;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        loginPresenterInterface = new LoginPresenter(this);
        callGetBoatListService();

    }

    private void initViews() {
        findViewById(R.id.tvForgotPassword).setOnClickListener(this);
        findViewById(R.id.btLogin).setOnClickListener(this);
        tvChooseBoat = findViewById(R.id.tvChooseBoat);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvChooseBoat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvForgotPassword:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
                case R.id.btLogin:
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    if (validate(email, password, mCurrentBoat != null ? mCurrentBoat.getBoatId() : ""))
                        callLoginService(email, password, mCurrentBoat.getBoatId());
                        //startActivity(new Intent(this, HomeActivity.class));
                break;
                case R.id.tvChooseBoat:
                    CommonUtils.showBoatFragmentDialog(this, mBoatDataSet);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginSuccess(String message) {
        Log.i(TAG, "onLoginSuccess: " + message);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onLoginFailed(String message) {
        Log.e(TAG, "onLoginFailed: " + message);
        CommonUtils.showErrorSnackbar(this, message);
    }

    @Override
    public void onGetBoats(ArrayList<Boat> boatDataSet) {
        mBoatDataSet = boatDataSet;
    }

    @Override
    public void onFailed(String message) {
        Log.e(TAG, "onFailed: " + message);
        CommonUtils.showErrorSnackbar(this, message);
    }

    @Override
    public void onServerError(String message) {

    }

    @Override
    public void onNoInternet(String message) {

    }

    @Override
    public void dismissProgressIndicator() {
        dismissProgress();
    }

    @Override
    public void showProgressIndicator() {
        showProgress();
    }

    private void callGetBoatListService(){
        loginPresenterInterface.getBoats();
    }

    private void callLoginService(String email, String password, String boatId){
        loginPresenterInterface.login(email, password, boatId);
    }

    private boolean validate(String email, String password, String boatId) {
        if (TextUtils.isEmpty(email)) {
            CommonUtils.showErrorSnackbar(this, getString(R.string.please_enter_username));
            return false;
        } else if (TextUtils.isEmpty(password)) {
            CommonUtils.showErrorSnackbar(this, getString(R.string.enter_your_password));
            return false;
        } else if (TextUtils.isEmpty(boatId)){
            CommonUtils.showErrorSnackbar(this, getString(R.string.Please_choose_a_boat));
            return false;
        }
        return true;
    }
}
