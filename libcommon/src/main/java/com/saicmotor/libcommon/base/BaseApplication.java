package com.saicmotor.libcommon.base;

import android.app.Application;

/**
 * @author zyf
 * Created by zyf on 2018/10/9.
 */

public class BaseApplication extends Application{

    private static BaseApplication sInstance;


    public static BaseApplication getIns() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
