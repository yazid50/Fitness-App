package com.rifianduo.fitnessapp;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public class prefUser {
    private static final String PREF_USER = "UserPref";
    public static final String username = "Username";
    public static final String email = "Email";
    public static final String isUserLogin = "IsUserLogin";

    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static prefUser instance;

    public prefUser(@NotNull Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE);
    }

    public static prefUser getInstance(Context context) {
        if (instance == null) {
            instance = new prefUser(context);
        }
        return instance;
    }

    public void putString(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putBoolean(String key, Boolean value) {
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).apply();
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void clearPrefUser() {
        editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
