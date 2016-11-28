package com.github.rayboot.project;

import android.app.Application;

import com.tumblr.remember.Remember;

/**
 * author: rayboot  Created on 15/12/3.
 * email : sy0725work@gmail.com
 */
public class App extends Application {
    private static App ourInstance = new App();

    public static App getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //shared preferences,
        Remember.init(this, BuildConfig.APPLICATION_ID + "_remember");
    }
}
