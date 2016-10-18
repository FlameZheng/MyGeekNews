package com.flame.mygeeknews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.flame.mygeeknews.app.App;
import com.flame.mygeeknews.di.component.ActivityComponent;
import com.flame.mygeeknews.di.component.DaggerActivityComponent;
import com.flame.mygeeknews.di.module.ActivityModule;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/12 0012 16:25
 * 邮箱：zhengtianji001@163.com
 * 描述：MVP Activity的 基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView {
    @Inject
    protected T mPresenter;

    private Unbinder mUnbinder;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        ininInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        App.getInstance().addActivity(this);
        initEventAndDate();
    }

    /**
     * 使用夜间模式（每个Activity中都一样的内容在BaseActivity中实现）
     */
    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    protected void setToolBar(Toolbar toolbar, String tittle) {
        toolbar.setTitle(tittle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected ActivityComponent getActivityComponent() {
        return
                DaggerActivityComponent.builder()
                        .appComponent(App.getAppComponent())
                        .activityModule(getActivityModule())
                        .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();
        App.getInstance().removeActivity(this);
    }

    protected abstract void initEventAndDate();

    protected abstract void ininInject();

    protected abstract int getLayout();
}
