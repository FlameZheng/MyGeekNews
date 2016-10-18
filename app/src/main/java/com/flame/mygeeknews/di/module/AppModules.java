package com.flame.mygeeknews.di.module;

import com.flame.mygeeknews.app.App;
import com.flame.mygeeknews.di.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/18 0018 15:36
 * 邮箱：zhengtianji001@163.com
 * 描述：AppModules,提供全局对象
 */
@Module
public class AppModules {
    private App application;

    public AppModules(App application) {
        this.application = application;
    }
    @Singleton
    @Provides
    @ContextLife("Application")
    App provideApplicationContext(){
        return application;
    }

}
