package com.iroid.mvpiroid.ui.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseDialogFragment
        extends DialogFragment {

    private static final String TAG = BaseDialogFragment.class.getSimpleName();

    protected View rootView;
    private Context context;

    @NonNull
    @Override
    @SuppressLint("InflateParams")
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.e(TAG, "onCreateDialog: ");
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        return setUpDialog(view);
    }

    private AlertDialog setUpDialog(View rootView){
        initViews(rootView);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(rootView);
        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        context = getActivity();
        rootView = inflater.inflate(getLayoutId(), container, false);

        if (rootView == null)
            return super.onCreateView(inflater, container, savedInstanceState);

        initViews(rootView);
        return rootView;
    }*/

    protected View inflate(@LayoutRes int id) {
        return inflate(id, null);
    }

    protected View inflate(@LayoutRes int id, @Nullable ViewGroup root) {
        return LayoutInflater.from(context).inflate(id, root);
    }

    protected <T extends View> T findView(int resId) {
        return (T) rootView.findViewById(resId);
    }

    protected void setOnClickListener(@IdRes int id, View.OnClickListener onClicklistener) {
        findView(id).setOnClickListener(onClicklistener);
    }

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void initViews(View rootView);


}