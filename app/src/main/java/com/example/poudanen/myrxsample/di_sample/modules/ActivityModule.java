package com.example.poudanen.myrxsample.di_sample.modules;

import android.app.Activity;
import android.content.Context;

import com.example.poudanen.myrxsample.ui.activities.main_screen.InteractorImpl;
import com.example.poudanen.myrxsample.ui.activities.main_screen.IntercatorInterface;
import com.example.poudanen.myrxsample.ui.activities.main_screen.MainPresenter;
import com.example.poudanen.myrxsample.ui.activities.main_screen.MainView;
import com.example.poudanen.myrxsample.ui.activities.main_screen.MainViewPresenterImpl;
import com.example.poudanen.myrxsample.di_sample.ActivityContext;
import com.example.poudanen.myrxsample.di_sample.PerActivity;

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

    @Provides
    @PerActivity
    MainPresenter<MainView> provideMainPresenter(MainViewPresenterImpl<MainView> mainViewPresenter) {
        return mainViewPresenter;
    }
}
