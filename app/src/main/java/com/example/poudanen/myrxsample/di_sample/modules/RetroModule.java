package com.example.poudanen.myrxsample.di_sample.modules;

import android.app.Application;

import com.example.poudanen.myrxsample.data.GitHubApi;
import com.example.poudanen.myrxsample.data.SampleInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Yuriy on 05.02.2017.
 */
@Module
public class RetroModule {

    private final String mUrl;

    public RetroModule(String url) {
        mUrl = url;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClientAuth(Cache cache,
                                         @Named("sample") Interceptor interceptor,
                                         @Named("log") HttpLoggingInterceptor loger) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);
        client.addInterceptor(loger);
        client.cache(cache);
        return client.build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofitSample(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named("sample")
    Interceptor provideInterceptorSample(SampleInterceptor authInterceptor) {
        return authInterceptor;
    }

    @Provides
    @Singleton
    @Named("log")
    HttpLoggingInterceptor provideInterceptorLoger() {
        HttpLoggingInterceptor authInterceptor = new HttpLoggingInterceptor();
        authInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return authInterceptor;
    }

    @Provides
    @Singleton
    public GitHubApi provideGithubApiService(Retrofit restAdapter) {
        return restAdapter.create(GitHubApi.class);
    }
}
