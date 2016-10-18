package com.flame.mygeeknews.app;

import android.app.Activity;
import android.app.Application;

import com.flame.mygeeknews.di.component.AppComponent;
import com.flame.mygeeknews.di.component.DaggerAppComponent;
import com.flame.mygeeknews.di.module.AppModules;

import java.util.HashSet;
import java.util.Set;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/12 0012 16:04
 * 邮箱：zhengtianji001@163.com
 * 描述：程序的入口
 */
public class App extends Application {
    private static App Instance;

    private Set<Activity> allActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public synchronized static App getInstance() {
        return Instance;
    }

    /**
     * 添加Activity到集合中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    /**
     * 从集合中移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }

    /**
     * 退出当前应用
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }

        }
        android.os.Process.killProcess(android.os.Process.myPid());
        //正常退出程序
        System.exit(0);
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModules(new AppModules(Instance))
                .build();
    }
}