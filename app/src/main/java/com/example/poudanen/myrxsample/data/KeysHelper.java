package com.example.poudanen.myrxsample.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.poudanen.myrxsample.di_sample.ApplicationContext;
import com.example.poudanen.myrxsample.data.model.UserCredentials;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by poudanen on 18.01.17.
 */

public class KeysHelper implements IKeysHelper {
    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String NAME_KEY = "NAME";
    public static final String PASSWORD_KEY = "PASSWORD";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @Inject
    public KeysHelper(@ApplicationContext Context context) {
        this.settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    @Override
    public Flowable<UserCredentials> getUserObj() {
        UserCredentials userCredentials = new UserCredentials(getValue(NAME_KEY), getValue(PASSWORD_KEY));
        return Flowable.just(userCredentials);
    }

    @Override
    public UserCredentials getUserCredentials() {
        UserCredentials userCredentials = new UserCredentials(getValue(NAME_KEY), getValue(PASSWORD_KEY));
        return userCredentials;
    }

    @Override
    public boolean saveUserCredentials(UserCredentials userCredentials) {
        return save(userCredentials.getName(), NAME_KEY) && save(userCredentials.getPassword(), PASSWORD_KEY);
    }

    @Override
    public boolean save(String text, String Key) {
        editor.putString(Key, text);
        return editor.commit();
    }

    @Override
    public String getValue(String Key) {
        String text = "";
        text = settings.getString(Key, "");
        return text;
    }

    @Override
    public boolean clearSharedPreference() {
        editor.clear();
        return editor.commit();
    }

    @Override
    public boolean removeValue(String value) {
        editor.remove(value);
        return editor.commit();
    }

}
