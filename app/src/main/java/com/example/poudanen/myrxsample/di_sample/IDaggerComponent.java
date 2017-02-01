package com.example.poudanen.myrxsample.di_sample;

import com.example.poudanen.myrxsample.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by poudanen on 01.02.17.
 */
@Singleton
@Component(
        modules = {
                DaggerPrefsHelper.class,
        }
)
public interface IDaggerComponent {
    void inject(MainActivity mainActivity);
}
