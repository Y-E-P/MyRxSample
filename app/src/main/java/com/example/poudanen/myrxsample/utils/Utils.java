package com.example.poudanen.myrxsample.utils;

import android.util.Base64;

/**
 * Created by Yuriy on 03.10.2016.
 */

public class Utils {

    public static String base64Generator(String username, String password) {
        String credentials = username + ":" + password;
        return Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }
}
