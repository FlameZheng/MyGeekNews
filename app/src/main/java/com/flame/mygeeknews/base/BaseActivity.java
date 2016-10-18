package com.flame.mygeeknews.base;

import android.content.Context;
import android.os.Bundle;

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

    private Unbinder mUnbinder;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        ininInject();
    }

    protected abstract void ininInject();

    protected abstract int getLayout();
}
