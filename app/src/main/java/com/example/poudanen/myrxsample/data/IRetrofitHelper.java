package com.example.poudanen.myrxsample.data;

import com.example.poudanen.myrxsample.data.model.User;

import io.reactivex.Observable;


/**
 * Created by Yuriy on 05.02.2017.
 */

public interface IRetrofitHelper {

    Observable<User> getUser(String login, String password);
    
}
