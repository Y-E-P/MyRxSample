package com.example.poudanen.myrxsample.activities.interactor;

import android.content.Context;

import com.example.poudanen.myrxsample.KeysHelper;
import com.example.poudanen.myrxsample.model.UserCredentials;

/**
 * Created by Yuriy on 31.01.2017.
 */

public class InteractorImpl implements IntercatorInterface {

    private Context context;

    public InteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public UserCredentials getUserCredentials() {
        return KeysHelper.getInstance().getUserCredentials(context);
    }

    @Override
    public boolean saveUserCredentials(UserCredentials userCredentials) {
        return KeysHelper.getInstance().saveUserCredentials(context, userCredentials);
    }
}
