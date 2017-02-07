package com.example.poudanen.myrxsample.data;

import android.content.Context;

import com.example.poudanen.myrxsample.data.model.User;
import com.example.poudanen.myrxsample.di_sample.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;


/**
 * Created by Yuriy on 06.02.2017.
 */
@Singleton
public class RetrofitHelperImpl implements IRetrofitHelper {

    private Retrofit retrofit;

    @Inject
    public RetrofitHelperImpl(@ApplicationContext Context context, Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<User> getUser(String login, String password) {
        return null;
    }
}
