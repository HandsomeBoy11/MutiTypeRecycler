package com.xrd.mutityperecycler.app;

import android.app.Application;

/**
 * Created by user on 2018/9/14.
 */

public class MyApp  extends Application {
    public static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }
}
