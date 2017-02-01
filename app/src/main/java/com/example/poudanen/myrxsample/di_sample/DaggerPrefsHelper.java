package com.example.poudanen.myrxsample.di_sample;

import com.example.poudanen.myrxsample.BaseApplications;
import com.example.poudanen.myrxsample.KeysHelper;
import com.example.poudanen.myrxsample.model.UserCredentials;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by poudanen on 01.02.17.
 */

@Module
@Singleton
public class DaggerPrefsHelper {
    @Named("preference")
    @Provides
    public UserCredentials provideCredentialsPrefs() {
        return KeysHelper.getInstance().getUserCredentials(BaseApplications.getContext());
    }

    @Named("object")
    @Provides
    public UserCredentials provideCredentialsObj() {
        return new UserCredentials("Vasia", "Pupkin");
    }


    @Provides
    public boolean addUser(UserCredentials userCredentials) {
        return KeysHelper.getInstance().saveUserCredentials(BaseApplications.getContext(), userCredentials);
    }
}
