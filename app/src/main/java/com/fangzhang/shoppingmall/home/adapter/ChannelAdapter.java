package com.fangzhang.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.home.bean.ResultBeanData;
import com.fangzhang.shoppingmall.utils.ConstantValue;

import java.util.List;

/**
 * Created by MoeRookie on 2018/5/28.
 */

class ChannelAdapter extends BaseAdapter{
    private Context ctx;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info;
    public ChannelAdapter(Context ctx, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.ctx = ctx;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info == null?0:channel_info.size();
    }

    @Override
    public ResultBeanData.ResultBean.ChannelInfoBean getItem(int i) {
        return channel_info != null && channel_info.size() > 0?
        channel_info.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GridViewHolder holder;
        if (view == null) {
            holder = new GridViewHolder();
            view = View.inflate(ctx, R.layout.item_channel,null);
            holder.iv_channel = view.findViewById(R.id.iv_channel);
            holder.tv_channel = view.findViewById(R.id.tv_channel);
            view.setTag(holder);
        }else{
            holder = (GridViewHolder) view.getTag();
        }
        // 根据位置得到对应的数据
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = getItem(i);
        Glide.with(ctx)
                .load(ConstantValue.IMAGE_BASE_URL + channelInfoBean.getImage())
                .into(holder.iv_channel);
        holder.tv_channel.setText(channelInfoBean.getChannel_name());
        return view;
    }

    private class GridViewHolder {
        ImageView iv_channel;
        TextView tv_channel;
    }
}
