package com.cumulus.basemvp2.base;

import com.cumulus.basemvp2.bean.Bean;

/**
 * Created by wang on 2019/3/10.
 */

public interface BaseMvpView {
    void setData(Bean bean);

    void showToast(String msg);

}
