package com.iroid.mvpiroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class PreferenceUtils {

    private static SharedPreferences sInstance;

    public static void newInstance(Context context) {
        sInstance = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static void setArray(String tag, ArrayList<String> mArray) {
        JSONArray array = new JSONArray(mArray);
        String json = array.toString();
        Editor editor = sInstance.edit();
        editor.putString(tag, json);
        editor.apply();
    }

    private static ArrayList<String> getArray(String tag) {
        ArrayList<String> array = new ArrayList<String>();


        String json = sInstance.getString(tag, "");

        try {

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {

                array.add(jsonArray.getString(i));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    private static boolean getBoolean(String key, boolean defaultValue) {
        return sInstance.getBoolean(key, defaultValue);
    }

    private static void setBoolean(String key, boolean value) {
        Editor editor = sInstance.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private static String getString(String key, String defaultValue) {
        return sInstance.getString(key, defaultValue);
    }

    private static void setString(String key, String value) {

        Log.e(key, value);

        Editor editor = sInstance.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private static int getInt(String key, int defaultValue) {
        return sInstance.getInt(key, defaultValue);
    }

    private static void setInt(String key, int value) {
        Editor editor = sInstance.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private static void setFloat(String key, float value) {
        Editor editor = sInstance.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    private static float getFloat(String key, float defaultValue) {
        return sInstance.getFloat(key, defaultValue);
    }

    public static boolean clear() {
        try {
            sInstance.edit().clear().apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isLoggedIn() {
        return getBoolean(Keys.IS_LOGIN, false);
    }

    public static void setIsLoggedIn(boolean value) {
        setBoolean(Keys.IS_LOGIN, value);
    }

    private static class Keys {

        private static final String IS_LOGIN = "is_login";


    }


}