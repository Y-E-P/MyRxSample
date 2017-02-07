package com.example.poudanen.myrxsample.ui.activities.main_screen;

import com.example.poudanen.myrxsample.data.KeysHelper;
import com.example.poudanen.myrxsample.data.model.UserCredentials;

import javax.inject.Inject;

import static com.example.poudanen.myrxsample.data.IKeysHelper.TOKEN;

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

    @Override
    public boolean saveUserToken(String token) {
        return keysHelper.save(token, TOKEN);
    }
}
