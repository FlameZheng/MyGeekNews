package com.flame.mygeeknews.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/18 0018 16:56
 * 邮箱：zhengtianji001@163.com
 * 描述：
 */
@Module
public class FragementModule {
    private  Fragment fragment;

    public FragementModule(Fragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return  fragment.getActivity();
    }
}
