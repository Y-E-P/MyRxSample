package com.example.poudanen.myrxsample.ui.activities.main_screen;

import com.example.poudanen.myrxsample.data.model.UserCredentials;

/**
 * Created by Yuriy on 31.01.2017.
 */

public interface IntercatorInterface {

    UserCredentials getUserCredentials();

    boolean saveUserCredentials(UserCredentials userCredentials);

    boolean saveUserToken(String token);

}
