package com.iroid.mvpiroid.ui.main.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.iroid.mvpiroid.R;
import com.iroid.mvpiroid.ui.base.BaseActivity;
import com.iroid.mvpiroid.utils.CommonUtils;

public class LoginActivity
        extends BaseActivity
        implements View.OnClickListener , LoginViewInterface{

    private EditText etMobileNo, etPassword;

    private LoginPresenter mPresenter;

    public static Intent start(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginPresenter(this, this);
    }

    @Override
    public void initViews() {
        etMobileNo = findViewById(R.id.etMobileNo);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.tvForgotPassword).setOnClickListener(this);
        findViewById(R.id.btLogin).setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin :
                /*startActivity(DashboardActivity.start(this));
                finish();*/
                if (mPresenter != null)
                    mPresenter.login(etMobileNo.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.tvForgotPassword :
                //startActivity(ForgotPasswordActivity.start(this));
                break;
        }
    }

    @Override
    public void onLoginSuccess(String message) {
        //startActivity(DashboardActivity.start(this));
        finish();
    }

    @Override
    public void onFailed(String message) {
        CommonUtils.showToast(this, message);
    }

    @Override
    public void onServerError(String message) {
        CommonUtils.showToast(this, message);
    }

    @Override
    public void onNoInternet(String message) {
        CommonUtils.showToast(this, message);
    }

    @Override
    public void dismissProgressIndicator() {
        dismissProgress();
    }

    @Override
    public void showProgressIndicator() {
        showProgress();
    }
}
