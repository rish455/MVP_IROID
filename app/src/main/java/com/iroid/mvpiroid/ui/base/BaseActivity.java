package com.iroid.mvpiroid.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.iroid.mvpiroid.ui.dialog_fragments.ProgressDialogFragment;
import com.iroid.mvpiroid.utils.NetworkUtils;

/**
 * @author Rishad
 * @since 16/03/2019
 */

public abstract class BaseActivity
        extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private ProgressDialogFragment mProgressDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public abstract void initViews();

    public abstract
    @LayoutRes
    int getLayoutId();

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void showProgress() {
        if (mProgressDialogFragment == null) {
            mProgressDialogFragment = ProgressDialogFragment.newInstance();
            mProgressDialogFragment.show(getSupportFragmentManager(), "progress_dialog");
        }
    }

    public void dismissProgress() {
        try {
            if (mProgressDialogFragment != null && !isFinishing()) {
                mProgressDialogFragment.dismiss();
                mProgressDialogFragment = null;
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}

