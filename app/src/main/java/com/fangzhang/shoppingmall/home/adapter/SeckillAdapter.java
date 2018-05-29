package com.fangzhang.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.home.bean.ResultBeanData;
import com.fangzhang.shoppingmall.utils.ConstantValue;

import java.util.List;

/**
 * Created by MoeRookie on 2018/5/29.
 */

class SecKillAdapter extends RecyclerView.Adapter<SecKillAdapter.SecKillViewHolder>{
    private Context ctx;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckill_infoList;
    public SecKillAdapter(Context ctx, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckill_infoList) {
        this.ctx = ctx;
        this.seckill_infoList = seckill_infoList;
    }

    @Override
    public SecKillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(ctx, R.layout.item_seckill, null);
        return new SecKillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SecKillViewHolder holder, int position) {
        // 1. 获取到ListBean
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = seckill_infoList.get(position);
        // 2. 将数据填充到列表项的每一个控件上
        Glide.with(ctx)
                .load(ConstantValue.IMAGE_BASE_URL + listBean.getFigure())
                .into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return seckill_infoList != null && seckill_infoList.size() > 0?seckill_infoList.size():0;
    }

    public class SecKillViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_figure;
        private final TextView tv_cover_price;
        private final TextView tv_origin_price;

        public SecKillViewHolder(View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);
        }
    }
}
