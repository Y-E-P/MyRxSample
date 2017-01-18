package com.example.poudanen.myrxsample;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.poudanen.myrxsample.model.UserCredentials;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by poudanen on 18.01.17.
 */

public class KeysHelper {
    private static KeysHelper sharedPreference;
    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String PREFS_KEY = "AOP_PREFS_String";


    public static KeysHelper getInstance() {
        if (sharedPreference == null) {
            sharedPreference = new KeysHelper();
        }
        return sharedPreference;
    }

    public Flowable<UserCredentials> getUserCredentials(){
        //return Flowable.just(getUserCreObj());
        return Flowable.just(getUserCreObj())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public KeysHelper() {
        super();
    }

    public UserCredentials getUserCreObj(){
        return new UserCredentials("Mike","234324");
    }

    public boolean save(Context context, String text, String Key) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Key, text); //3

        return editor.commit(); //4
    }

    public String getValue(Context context, String Key) {
        SharedPreferences settings;
        String text = "";
        //  settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(Key, "");
        return text;
    }

    public boolean clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        return editor.commit();
    }

    public boolean removeValue(Context context, String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(value);
        return editor.commit();
    }
}
