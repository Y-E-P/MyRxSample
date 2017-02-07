package com.example.poudanen.myrxsample.data;

import com.example.poudanen.myrxsample.data.model.User;
import com.example.poudanen.myrxsample.data.model.UserCredentials;

import io.reactivex.Observable;

/**
 * Created by poudanen on 07.02.17.
 */

public interface IDataManager {

    IKeysHelper getKeyHelper();

    String getToken();

    void setToken(String token);

    UserCredentials getUserCredentials();

    void setUserCredentials(UserCredentials userCredentials);

    Observable<User> getUser(String login,String password);
}
