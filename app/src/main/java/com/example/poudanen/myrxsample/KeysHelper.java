package com.example.poudanen.myrxsample;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.poudanen.myrxsample.di_sample.ApplicationContext;
import com.example.poudanen.myrxsample.model.UserCredentials;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by poudanen on 18.01.17.
 */

public class KeysHelper {
    private static KeysHelper sharedPreference;
    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String NAME_KEY = "NAME";
    public static final String PASSWORD_KEY = "PASSWORD";

    private Context context;

    @Inject
    public KeysHelper(@ApplicationContext Context context) {
        this.context = context;
    }

    public Flowable<UserCredentials> getUserObj() {
        UserCredentials userCredentials = new UserCredentials(getValue(NAME_KEY), getValue(PASSWORD_KEY));
        return Flowable.just(userCredentials);
    }

    public UserCredentials getUserCredentials() {
        UserCredentials userCredentials = new UserCredentials(getValue(NAME_KEY), getValue(PASSWORD_KEY));
        return userCredentials;
    }


    public boolean saveUserCredentials(UserCredentials userCredentials) {
        return save(userCredentials.getName(), NAME_KEY) && save(userCredentials.getPassword(), PASSWORD_KEY);
    }

    public boolean save(String text, String Key) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(Key, text); //3
        return editor.commit(); //4
    }

    public String getValue(String Key) {
        SharedPreferences settings;
        String text = "";
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(Key, "");
        return text;
    }

    public boolean clearSharedPreference() {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        return editor.commit();
    }

    public boolean removeValue(String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(value);
        return editor.commit();
    }
}
