package com.flame.mygeeknews.di.component;

import com.flame.mygeeknews.di.ActivityScope;
import com.flame.mygeeknews.di.module.ActivityModule;

import dagger.Component;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/18 0018 15:33
 * 邮箱：zhengtianji001@163.com
 * 描述：
 */
@ActivityScope
@Component(dependencies = {AppComponent.class},modules = ActivityModule.class)
public interface ActivityComponent {
}
