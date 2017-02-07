package com.example.poudanen.myrxsample.ui.activities.main_screen;

import com.example.poudanen.myrxsample.ui.activities.base.IBasePresenter;

/**
 * Created by Yuriy on 31.01.2017.
 */

public interface MainPresenter<V extends MainView> extends IBasePresenter<V> {

    void OnCreate();

    void OnDestroy();

    void OnPause();

    void verifyCredentials(String login, String password);

    void getUser();

    void saveData(String login, String password);
}
