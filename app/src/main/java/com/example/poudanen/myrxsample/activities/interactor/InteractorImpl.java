package com.example.poudanen.myrxsample.activities.interactor;

import com.example.poudanen.myrxsample.KeysHelper;
import com.example.poudanen.myrxsample.model.UserCredentials;

import javax.inject.Inject;

/**
 * Created by Yuriy on 31.01.2017.
 */

public class InteractorImpl implements IntercatorInterface {

    private KeysHelper keysHelper;

    @Inject
    public InteractorImpl(KeysHelper keysHelper) {
        this.keysHelper = keysHelper;
    }

    @Override
    public UserCredentials getUserCredentials() {
        return keysHelper.getUserCredentials();
    }

    @Override
    public boolean saveUserCredentials(UserCredentials userCredentials) {
        return keysHelper.saveUserCredentials(userCredentials);
    }
}
