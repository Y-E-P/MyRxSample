package com.example.poudanen.myrxsample.di_sample.modules;

import android.app.Application;
import android.content.Context;

import com.example.poudanen.myrxsample.data.DataManager;
import com.example.poudanen.myrxsample.data.IDataManager;
import com.example.poudanen.myrxsample.data.IKeysHelper;
import com.example.poudanen.myrxsample.data.KeysHelper;
import com.example.poudanen.myrxsample.di_sample.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yuriy on 07.02.2017.
 */
@Module
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

    @Provides
    @Singleton
    public IKeysHelper provideCredentialsPrefs(KeysHelper keysHelper) {
        return keysHelper;
    }

    @Provides
    @Singleton
    public IDataManager provideDataManager(DataManager dataManager) {
        return dataManager;
    }
}
