package com.cumulus.basemvp2.net;



import com.cumulus.basemvp2.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wang on 2019/3/10.
 */

public interface Apiservice {
    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1

    String mBaseUrl = "http://gank.io/";
    @GET("api/data/%E7%A6%8F%E5%88%A9/20/{page}")
    Observable<Bean>getData(@Path("page") int mPage);
}
