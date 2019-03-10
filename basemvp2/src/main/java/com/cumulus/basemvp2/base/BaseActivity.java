package com.cumulus.basemvp2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by wang on 2019/3/10.
 */

public abstract class BaseActivity<V extends BaseMvpView,T extends BasePresenter> extends AppCompatActivity {
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLatoutId());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V)this);
        }
        initView();
        initListener();
        initData();
    }

    protected abstract T initPresenter();

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected abstract int getLatoutId();
}
