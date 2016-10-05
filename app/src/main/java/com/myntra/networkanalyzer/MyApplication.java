package com.myntra.networkanalyzer;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static  MyApplication getMyApplicationIntance()
    {
        if(null == instance)
        {
            throw new RuntimeException("Application not intialized.Check Manifest");
        }
        return instance;
    }
}
