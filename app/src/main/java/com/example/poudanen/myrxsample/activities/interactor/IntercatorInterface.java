package com.example.poudanen.myrxsample.activities.interactor;

import com.example.poudanen.myrxsample.model.UserCredentials;

/**
 * Created by Yuriy on 31.01.2017.
 */

public interface IntercatorInterface {

    UserCredentials getUserCredentials();

    boolean saveUserCredentials(UserCredentials userCredentials);

}
