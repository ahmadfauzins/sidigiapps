package com.fauzi.sidigiapps.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fauzi.sidigiapps.Model.User;
import com.google.gson.Gson;


public class PrefUtil {

    public static final String USER_SESSION = "user_session";

    public static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putUser(Context context, String key, User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        putString(context, key, json);
    }

    public static void updateUser(Context context, String key, User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        updateString(context, key, json);
    }

    public static User getUser(Context context, String key) {
        Gson gson = new Gson();
        String json = getString(context, key);
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    public static void updateString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getSharedPreference(context).getString(key, null);
    }

    public static void clear(Context context) {
        getSharedPreference(context).edit().clear().apply();
    }

}

