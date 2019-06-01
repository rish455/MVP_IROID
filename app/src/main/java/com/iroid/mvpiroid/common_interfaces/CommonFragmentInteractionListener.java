package com.iroid.mvpiroid.common_interfaces;

public interface CommonFragmentInteractionListener {

    void replaceFragment(String tag);

    void startActivity(String which);

    void showProgressIndicator();

    void hideProgressIndicator();

}