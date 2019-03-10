package com.cumulus.basemvp2.presenter;

import com.cumulus.basemvp2.base.BasePresenter;
import com.cumulus.basemvp2.bean.Bean;
import com.cumulus.basemvp2.model.NetModel;
import com.cumulus.basemvp2.net.ResultCallBack;

/**
 * Created by wang on 2019/3/10.
 */

public class NetPresenter extends BasePresenter {

    private NetModel model;
    @Override
    protected void initModel() {
        model = new NetModel();
    }

    public void getData(int mPage) {
        model.getData(mPage, new ResultCallBack<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                mView.setData(bean);
            }

            @Override
            public void onFail(String msg) {
                mView.showToast(msg);
            }
        });
    }


}
