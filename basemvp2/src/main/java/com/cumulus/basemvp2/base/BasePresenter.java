package com.cumulus.basemvp2.base;

/**
 * Created by wang on 2019/3/10.
 */

public abstract class BasePresenter <V extends BaseMvpView>{

    protected V mView;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void attachView(V view) {
        mView = view;
    }
}
