package com.flame.mygeeknews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flame.mygeeknews.app.App;
import com.flame.mygeeknews.di.component.DaggerFragementComponent;
import com.flame.mygeeknews.di.component.FragementComponent;
import com.flame.mygeeknews.di.module.FragementModule;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者：flamezheng（郑天吉）
 * 时间： on 2016/10/18 0018 16:23
 * 邮箱：zhengtianji001@163.com
 * 描述：
 */
public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {
    @Inject
    protected T mPresenter;

    private Activity mActivity;
    private Context mContext;
    private View mView;
    private boolean isInited;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        initInJect();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        unbinder = ButterKnife.bind(view);
        if(savedInstanceState==null){
            if(!isHidden()) {
                isInited = true;
                initEventAndData();
            }
        }else {
            if(!isSupportHidden()) {
                isInited =true;
                initEventAndData();
            }
        }
    }
    protected FragementComponent getFragmentComponent(){
        return DaggerFragementComponent.builder()
                .appComponent(App.getAppComponent())
                .fragementModule(getFragmentModule())
                .build();
    }

    private FragementModule getFragmentModule() {
        return new FragementModule(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!isInited&&!isHidden()) {
            isInited= true;
            initEventAndData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("Fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null) {
            mPresenter.detachView();
        }
    }

    protected abstract void initEventAndData();

    protected abstract void initInJect();

    /**
     * 资源文件的ID
     *
     * @return
     */
    protected abstract int getLayoutId();
}
