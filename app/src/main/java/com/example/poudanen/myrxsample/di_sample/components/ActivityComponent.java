package com.example.poudanen.myrxsample.di_sample.components;

import com.example.poudanen.myrxsample.di_sample.PerActivity;
import com.example.poudanen.myrxsample.di_sample.modules.ActivityModule;
import com.example.poudanen.myrxsample.ui.activities.main_screen.MainActivity;

import dagger.Component;

/**
 * Created by Yuriy on 07.02.2017.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
