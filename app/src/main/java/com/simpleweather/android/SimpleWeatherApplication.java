package com.simpleweather.android;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class SimpleWeatherApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
