package com.flame.mygeeknews.di.component;

import com.flame.mygeeknews.di.module.AppModules;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/18 0018 15:35
 * 邮箱：zhengtianji001@163.com
 * 描述：
 */
@Singleton
@Component(modules = AppModules.class)
public interface AppComponent {

}
