package com.fangzhang.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangzhang.shoppingmall.home.bean.ResultBeanData;

/**
 * Created by MoeRookie on 2018/5/25.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter{
    /**
     * 横幅广告
     */
    private static final int BANNER = 0;
    /**
     * 频道
     */
    private static final int CHANNEL = 1;
    /**
     * 活动
     */
    private static final int ACT = 2;
    /**
     * 秒杀
     */
    private static final int SECKILL = 3;
    /**
     * 推荐
     */
    private static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    private static final int HOT = 5;
    /**
     * 当前类型
     */
    private int currentType = 0;
    private Context mCtx;
    private ResultBeanData.ResultBean mResultBean;
    private LayoutInflater mLayoutInflater;
    public HomeFragmentAdapter(Context ctx, ResultBeanData.ResultBean resultBean) {
        this.mCtx = ctx;
        this.mResultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mCtx);
    }

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        // 以后做完后改成6,现在只实现横幅广告,暂时写1
        return 1;
    }
}
