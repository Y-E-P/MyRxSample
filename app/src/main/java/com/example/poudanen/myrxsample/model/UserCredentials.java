package com.example.poudanen.myrxsample.model;

/**
 * Created by poudanen on 18.01.17.
 */

public class UserCredentials {

    private String name, password;

    public UserCredentials(String name, String login) {
        this.name = name;
        this.password = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
