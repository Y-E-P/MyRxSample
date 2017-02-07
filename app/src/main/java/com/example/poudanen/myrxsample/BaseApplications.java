package com.example.poudanen.myrxsample;

import android.app.Application;
import android.content.Context;

import com.example.poudanen.myrxsample.di_sample.components.AppComponent;
import com.example.poudanen.myrxsample.di_sample.components.DaggerAppComponent;
import com.example.poudanen.myrxsample.di_sample.modules.AppModule;
import com.example.poudanen.myrxsample.di_sample.modules.RetroModule;

/**
 * Created by poudanen on 01.02.17.
 */

public class BaseApplications extends Application {
    private AppComponent appComponent;

    public static BaseApplications get(Context context) {
        return (BaseApplications) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .retroModule(new RetroModule("https://api.github.com"))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
