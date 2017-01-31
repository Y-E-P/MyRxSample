package com.example.poudanen.myrxsample.activities.presenter;

/**
 * Created by Yuriy on 31.01.2017.
 */

public interface MainPresenter {

    void OnCreate();

    void OnDestroy();

    void OnPause();

    void verifyCredentials(String login, String password);

    void getUser();

    void saveData(String login,String password);
}
