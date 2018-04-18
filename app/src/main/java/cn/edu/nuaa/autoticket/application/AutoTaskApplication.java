package cn.edu.nuaa.autoticket.application;

import android.app.Application;
import android.content.Context;

//在manifest文件中，把默认application类替换成 这个，可以在任何位置获取application context
public class AutoTaskApplication extends Application {
    private static Context context;
    private String userName;
    private String userPasswd;

    public AutoTaskApplication() {

    }

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
