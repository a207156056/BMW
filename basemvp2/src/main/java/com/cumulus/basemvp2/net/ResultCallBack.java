package com.cumulus.basemvp2.net;

/**
 * Created by wang on 2019/3/10.
 */

public interface ResultCallBack<T>{
    void onSuccess(T bean);

    void onFail(String msg);
}
