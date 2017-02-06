package com.example.poudanen.myrxsample.di_sample.modules;

import android.app.Application;
import android.content.Context;

import com.example.poudanen.myrxsample.KeysHelper;
import com.example.poudanen.myrxsample.di_sample.ApplicationContext;
import com.example.poudanen.myrxsample.model.UserCredentials;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yuriy on 07.02.2017.
 */
@Module
@Singleton
public class AppModule {

    protected final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Named("preference")
    @Provides
    public KeysHelper provideCredentialsPrefs(KeysHelper keysHelper) {
        return keysHelper;
    }

    @Named("object")
    @Provides
    public UserCredentials provideCredentialsObj() {
        return new UserCredentials("Vasia", "Pupkin");
    }
}
