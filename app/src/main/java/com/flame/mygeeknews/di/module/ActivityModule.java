package com.flame.mygeeknews.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/18 0018 15:19
 * 邮箱：zhengtianji001@163.com
 * 描述：
 */
@Module
public class ActivityModule {
    private  Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }
    @Provides
    protected Activity provideActivity(){
        return mActivity;
    }

}
