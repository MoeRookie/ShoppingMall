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
 * Created by MoeRookie on 2018/5/30.
 */

class RecommendAdapter extends BaseAdapter{
    private Context ctx;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info;
    public RecommendAdapter(Context ctx, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.ctx = ctx;
        this.recommend_info = recommend_info;
    }
    
    @Override
    public int getCount() {
        return recommend_info == null?0:recommend_info.size();
    }
    
    @Override
    public ResultBeanData.ResultBean.RecommendInfoBean getItem(int i) {
        return recommend_info != null && recommend_info.size() > 0?
        recommend_info.get(i):null;
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfo = getItem(i);
        RecommendViewHolder holder;
        if (view == null) {
            holder = new RecommendViewHolder();
            view = View.inflate(ctx, R.layout.item_recommend, null);
            holder.iv_recommend = view.findViewById(R.id.iv_recommend);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_price = view.findViewById(R.id.tv_price);
            view.setTag(holder);
        } else {
            holder = (RecommendViewHolder) view.getTag();
        }
        Glide.with(ctx)
                .load(ConstantValue.IMAGE_BASE_URL+recommendInfo.getFigure())
                .into(holder.iv_recommend);
        holder.tv_name.setText(recommendInfo.getName());
        holder.tv_price.setText("￥" + recommendInfo.getCover_price());
        return view;
    }
    static class RecommendViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
