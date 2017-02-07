package com.example.poudanen.myrxsample.data;

import com.example.poudanen.myrxsample.data.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by poudanen on 02.02.17.
 */

public interface GitHubApi {

    @GET("user")
    Observable<User> getUser();
    
}
