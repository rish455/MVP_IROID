package com.iroid.mvpiroid;

import android.app.Application;

import com.iroid.mvpiroid.utils.PreferenceUtils;

/**
 * @author Rishad
 * @since 16/03/2019
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.newInstance(getApplicationContext());

    }

}
