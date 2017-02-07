package com.example.poudanen.myrxsample.di_sample.components;

import android.app.Application;
import android.content.Context;

import com.example.poudanen.myrxsample.BaseApplications;
import com.example.poudanen.myrxsample.data.DataManager;
import com.example.poudanen.myrxsample.di_sample.ApplicationContext;
import com.example.poudanen.myrxsample.di_sample.modules.AppModule;
import com.example.poudanen.myrxsample.di_sample.modules.RetroModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yuriy on 07.02.2017.
 */
@Singleton
@Component(modules = {AppModule.class, RetroModule.class})
public interface AppComponent {

    void inject(BaseApplications myApplication);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

}
