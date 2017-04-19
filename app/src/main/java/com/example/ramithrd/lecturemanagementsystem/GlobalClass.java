package com.example.ramithrd.lecturemanagementsystem;

import android.app.Application;

import com.example.ramithrd.lecturemanagementsystem.Model.User;

/**
 * Created by RamithRD on 3/14/2017.
 */

public class GlobalClass extends Application {

    private User userInfo;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

}
