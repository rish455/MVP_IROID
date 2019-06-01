package com.iroid.mvpiroid.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Rishad
 * @since 16/03/2019
 */

public abstract class BaseFragment
        extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootVIew = inflater.inflate(getLayoutId(), container, false);
        initViews(rootVIew);
        return rootVIew;
    }

    public abstract void initViews(View rootView);

    public abstract
    @LayoutRes
    int getLayoutId();
}
