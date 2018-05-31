package com.fangzhang.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.home.bean.ResultBeanData;
import com.fangzhang.shoppingmall.utils.ConstantValue;

import java.util.List;

/**
 * Created by MoeRookie on 2018/5/31.
 */

class HotAdapter extends BaseAdapter{
    private Context ctx;
    private List<ResultBeanData.ResultBean.HotInfoBean> hot_info;
    public HotAdapter(Context ctx, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        this.ctx = ctx;
        this.hot_info = hot_info;
    }
    
    @Override
    public int getCount() {
        return hot_info == null?0:hot_info.size();
    }
    
    @Override
    public ResultBeanData.ResultBean.HotInfoBean getItem(int i) {
        return hot_info != null && hot_info.size() > 0?
        hot_info.get(i):null;
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HotViewHolder holder;
        ResultBeanData.ResultBean.HotInfoBean hotInfo = getItem(i);
        if (view == null) {
            view = View.inflate(ctx, R.layout.item_hot, null);
            holder = new HotViewHolder();
            holder.iv_hot = view.findViewById(R.id.iv_hot);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_price = view.findViewById(R.id.tv_price);
            view.setTag(holder);
        } else {
            holder = (HotViewHolder) view.getTag();
        }
        Glide
                .with(ctx)
                .load(ConstantValue.IMAGE_BASE_URL+hotInfo.getFigure())
                .into(holder.iv_hot);
        holder.tv_name.setText(hotInfo.getName());
        holder.tv_price.setText("￥"+hotInfo.getCover_price());
        return view;
    }
    static class HotViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
