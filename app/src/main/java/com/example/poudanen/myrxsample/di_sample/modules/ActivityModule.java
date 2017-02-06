package com.example.poudanen.myrxsample.di_sample.modules;

import android.app.Activity;
import android.content.Context;

import com.example.poudanen.myrxsample.activities.interactor.InteractorImpl;
import com.example.poudanen.myrxsample.activities.interactor.IntercatorInterface;
import com.example.poudanen.myrxsample.di_sample.ActivityContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yuriy on 07.02.2017.
 */

@Singleton
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }

    @Provides
    IntercatorInterface provideInteractor(InteractorImpl interactor) {
        return interactor;
    }
}
