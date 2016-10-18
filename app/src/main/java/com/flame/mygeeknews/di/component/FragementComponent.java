package com.flame.mygeeknews.di.component;

import android.app.Activity;

import com.flame.mygeeknews.di.module.FragementModule;
import com.flame.mygeeknews.di.module.FragmentScope;

import dagger.Component;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/18 0018 16:55
 * 邮箱：zhengtianji001@163.com
 * 描述：
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragementModule.class)
public interface FragementComponent {
    Activity getActivity();
}
