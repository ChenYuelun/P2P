package com.example.p2p.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by chenyuelun on 2017/6/20.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //CrashHandler.getInstance().init(this);
    }
}
