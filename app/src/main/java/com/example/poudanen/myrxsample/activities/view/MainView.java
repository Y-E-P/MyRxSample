package com.example.poudanen.myrxsample.activities.view;

import com.example.poudanen.myrxsample.activities.presenter.MainPresenter;

/**
 * Created by Yuriy on 31.01.2017.
 */

public interface MainView {

    void updateProgressBar(int percent);

    void showError(String error);

    void onSuccess();

    void buttonActivation(boolean state);

    void showCredentials(String user, String login);

    MainPresenter getPresenter();

}
