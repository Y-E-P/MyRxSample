package com.example.poudanen.myrxsample.data;

import com.example.poudanen.myrxsample.data.model.UserCredentials;

import io.reactivex.Flowable;

/**
 * Created by poudanen on 07.02.17.
 */

public interface IKeysHelper {
    public static final String TOKEN = "token";

    Flowable<UserCredentials> getUserObj();

    UserCredentials getUserCredentials();

    boolean saveUserCredentials(UserCredentials userCredentials);

    boolean save(String text, String Key);

    String getValue(String Key);

    boolean clearSharedPreference();

    boolean removeValue(String value);

}
