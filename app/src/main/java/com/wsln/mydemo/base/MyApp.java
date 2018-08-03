package com.wsln.mydemo.base;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * @auth: liunan
 * @date: 2018/7/24
 * @desc:
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "85863870ac", true);
    }

}
