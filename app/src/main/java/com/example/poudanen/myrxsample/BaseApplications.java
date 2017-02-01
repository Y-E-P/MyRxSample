package com.example.poudanen.myrxsample;

import android.app.Application;
import android.content.Context;

import com.example.poudanen.myrxsample.di_sample.DaggerIDaggerComponent;
import com.example.poudanen.myrxsample.di_sample.DaggerPrefsHelper;
import com.example.poudanen.myrxsample.di_sample.IDaggerComponent;

/**
 * Created by poudanen on 01.02.17.
 */

public class BaseApplications extends Application {
    private IDaggerComponent IDaggerComponent;
    private static Context mContext;
    public static IDaggerComponent component(Context context) {
        return ((BaseApplications) context.getApplicationContext()).IDaggerComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        IDaggerComponent = DaggerIDaggerComponent.builder().daggerPrefsHelper(new DaggerPrefsHelper()).build();
    }

    public static Context getContext() {
        return mContext;
    }
}
