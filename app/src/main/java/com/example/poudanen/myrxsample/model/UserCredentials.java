package com.example.poudanen.myrxsample.model;

/**
 * Created by poudanen on 18.01.17.
 */

public class UserCredentials {

    private String name,login;

    public UserCredentials(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
