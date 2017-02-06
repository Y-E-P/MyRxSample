package com.example.poudanen.myrxsample;

import android.app.Application;
import android.content.Context;

import com.example.poudanen.myrxsample.di_sample.components.AppComponent;
import com.example.poudanen.myrxsample.di_sample.components.DaggerAppComponent;
import com.example.poudanen.myrxsample.di_sample.modules.AppModule;

/**
 * Created by poudanen on 01.02.17.
 */

public class BaseApplications extends Application {
    private AppComponent appComponent;
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static BaseApplications get(Context context) {
        return (BaseApplications) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
