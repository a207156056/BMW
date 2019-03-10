package com.cumulus.basemvp2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cumulus.basemvp2.adapter.NetAdapter;
import com.cumulus.basemvp2.base.BaseActivity;
import com.cumulus.basemvp2.bean.Bean;
import com.cumulus.basemvp2.presenter.NetPresenter;
import com.cumulus.basemvp2.view.NetView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<NetView,NetPresenter> implements NetView{

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private NetAdapter adapter;
    int mPage = 1;
    private Handler mHandler;

    @Override
    protected void initView() {
        mRlv.setLayoutManager(new GridLayoutManager(this,2));
        final ArrayList<Bean.ResultsBean> resultsBeans = new ArrayList<>();
        adapter = new NetAdapter(this,resultsBeans);
        mRlv.setAdapter(adapter);
        mSmart.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        mSmart.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        mSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                initData();
                mSmart.finishRefresh();
            }
        });

       mSmart.setOnLoadmoreListener(new OnLoadmoreListener() {
           @Override
           public void onLoadmore(RefreshLayout refreshlayout) {
               mPage++;
               initData();
               mSmart.finishLoadmore();
           }
       });

        mSmart.setEnableLoadmore(true);
        mSmart.autoRefresh();

    }

    @Override
    protected void initData() {
        mPresenter.getData(mPage);
    }

    @Override
    protected NetPresenter initPresenter() {
        return new NetPresenter();
    }

    @Override
    protected int getLatoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setData(Bean bean) {
        adapter.addData(bean.getResults());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
