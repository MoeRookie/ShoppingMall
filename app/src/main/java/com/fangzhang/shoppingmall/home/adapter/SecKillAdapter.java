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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 获取到当前被点击的秒杀列表项的位置getLayoutPosition();
                    // 调用listener中的onItemClick方法
                    if (listener != null) {
                        listener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }
    /*
    * 1. 写当前RecyclerView条目被点击时的接口
    *   当某个列表项被点击的时候回调
    *   回调其中的onItemClick方法
    * 2. 需要外界将接口的实现类对象(监听器)传入
    * 3. 所以需要定义一个listener变量;
    * 4. 实际上被点击的依然是itemView.
    *   故设置其点击事件,在处理点击事件时将position传入并调用onItemClick方法
    *   然后其实是调用了listener实现类对象的该方法
    * */
    private OnItemClickListener listener;
    // 1. 写当前RecyclerView条目被点击时的接口
    public interface OnItemClickListener{
        /**
         * 当某个列表项被点击的时候回调
         * @param position
         */
        // 3. 在传入监听器的时候,应将被点击条目的下标传过来
        public void onItemClick(int position);
    }
    
    /**
     * 设置item的监听器
     * @param listener
     */
    // 2. 需要外界将接口的实现类对象(监听器)传入
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}