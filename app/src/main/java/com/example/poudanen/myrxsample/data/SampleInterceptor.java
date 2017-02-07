package com.example.poudanen.myrxsample.data;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.poudanen.myrxsample.data.IKeysHelper.TOKEN;

/**
 * Created by poudanen on 06.02.17.
 */

public class SampleInterceptor implements Interceptor {

    public KeysHelper keysHelper;

    @Inject
    public SampleInterceptor(KeysHelper keysHelper) {
        this.keysHelper = keysHelper;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", "Basic : " + keysHelper.getValue(TOKEN))
                .header("Accept", "application/json")
                .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
