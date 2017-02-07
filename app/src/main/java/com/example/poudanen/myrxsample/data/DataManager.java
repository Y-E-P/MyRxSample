package com.example.poudanen.myrxsample.data;

import com.example.poudanen.myrxsample.data.model.User;
import com.example.poudanen.myrxsample.data.model.UserCredentials;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by poudanen on 07.02.17.
 */

public class DataManager implements IDataManager {

    public final String TAG = DataManager.class.toString();
    public IKeysHelper iKeysHelper;


    private GitHubApi gitHubApi;
    private String token;

    @Inject
    public DataManager(IKeysHelper iKeysHelper, GitHubApi gitHubApi) {
        this.iKeysHelper = iKeysHelper;
        this.gitHubApi = gitHubApi;
    }

    @Override
    public IKeysHelper getKeyHelper() {
        return iKeysHelper;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public UserCredentials getUserCredentials() {
        return iKeysHelper.getUserCredentials();
    }

    @Override
    public void setUserCredentials(UserCredentials userCredentials) {
        iKeysHelper.saveUserCredentials(userCredentials);
    }

    @Override
    public Observable<User> getUser(String login, String password) {
        return gitHubApi.getUser();
    }
}
