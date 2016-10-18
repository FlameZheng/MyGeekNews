package com.flame.mygeeknews.base;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/13 0013 10:26
 * 邮箱：zhengtianji001@163.com
 * 描述：presenter的基类
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
