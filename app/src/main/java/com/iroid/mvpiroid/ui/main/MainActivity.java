package com.iroid.mvpiroid.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iroid.mvpiroid.R;
import com.iroid.mvpiroid.ui.base.BaseActivity;

public class MainActivity
        extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
