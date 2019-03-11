package com.cumulus.jgpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wang on 2019/3/11.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
