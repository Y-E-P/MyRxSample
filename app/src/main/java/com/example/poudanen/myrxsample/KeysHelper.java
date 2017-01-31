package com.example.poudanen.myrxsample;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.poudanen.myrxsample.model.UserCredentials;

import io.reactivex.Flowable;

/**
 * Created by poudanen on 18.01.17.
 */

public class KeysHelper {
    private static KeysHelper sharedPreference;
    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String NAME_KEY = "NAME";
    public static final String PASSWORD_KEY = "PASSWORD";


    public static KeysHelper getInstance() {
        if (sharedPreference == null) {
            sharedPreference = new KeysHelper();
        }
        return sharedPreference;
    }


    public KeysHelper() {
        super();
    }

    public Flowable<UserCredentials> getUserObj(Context context) {
        UserCredentials userCredentials = new UserCredentials(getValue(context, NAME_KEY), getValue(context, PASSWORD_KEY));
        return Flowable.just(userCredentials);
    }

    public UserCredentials getUserCredentials(Context context) {
        UserCredentials userCredentials = new UserCredentials(getValue(context, NAME_KEY), getValue(context, PASSWORD_KEY));
        return userCredentials;
    }


    public boolean saveUserCredentials(Context context, UserCredentials userCredentials) {
        return save(context, userCredentials.getName(), NAME_KEY) && save(context, userCredentials.getPassword(), PASSWORD_KEY);
    }

    public boolean save(Context context, String text, String Key) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(Key, text); //3
        return editor.commit(); //4
    }

    public String getValue(Context context, String Key) {
        SharedPreferences settings;
        String text = "";
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(Key, "");
        return text;
    }

    public boolean clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        return editor.commit();
    }

    public boolean removeValue(Context context, String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(value);
        return editor.commit();
    }
}
