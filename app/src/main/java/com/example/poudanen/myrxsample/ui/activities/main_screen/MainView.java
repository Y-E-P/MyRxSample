package com.example.poudanen.myrxsample.ui.activities.main_screen;

import com.example.poudanen.myrxsample.ui.activities.base.IBaseView;

/**
 * Created by Yuriy on 31.01.2017.
 */

public interface MainView extends IBaseView {

    void showError(String error);

    void onSuccess();

    void buttonActivation(boolean state);

    void showCredentials(String user, String login);

    void showSomeText(String someText);

    void openSecondScreen();

}
