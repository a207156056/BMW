package com.cumulus.basemvp2.model;

import com.cumulus.basemvp2.base.BaseModel;
import com.cumulus.basemvp2.bean.Bean;
import com.cumulus.basemvp2.net.Apiservice;
import com.cumulus.basemvp2.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wang on 2019/3/10.
 */

public class NetModel extends BaseModel {
    public void getData(int mPage, final ResultCallBack<Bean> callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiservice.mBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Observable<Bean> data = retrofit.create(Apiservice.class).getData(mPage);

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        if (bean != null && bean.getResults() != null && bean.getResults().size() > 0) {
                                callBack.onSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
