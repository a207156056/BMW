package com.cumulus.basemvp2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cumulus.basemvp2.MainActivity;
import com.cumulus.basemvp2.R;
import com.cumulus.basemvp2.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2019/3/10.
 */

public class NetAdapter extends RecyclerView.Adapter {
    private MainActivity mContext;
    public ArrayList<Bean.ResultsBean> mResultBean;

    public NetAdapter(MainActivity mainActivity, ArrayList<Bean.ResultsBean> resultsBeans) {
        mContext = mainActivity;
        mResultBean = resultsBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_fuli, null);
        return new NetViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NetViewHolder holder1 = (NetViewHolder) holder;
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher);
        Glide.with(mContext).load(mResultBean.get(position).getUrl()).apply(options).into(holder1.img);
    }

    @Override
    public int getItemCount() {
        return mResultBean.size();
    }

    class NetViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        public NetViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }

    public void addData(List<Bean.ResultsBean> results) {
        mResultBean.addAll(results);
        notifyDataSetChanged();
    }
}
