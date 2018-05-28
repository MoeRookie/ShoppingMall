package com.fangzhang.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.home.bean.ResultBeanData;
import com.fangzhang.shoppingmall.utils.ConstantValue;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoeRookie on 2018/5/25.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter{
    private static final String TAG = HomeFragmentAdapter.class.getSimpleName();
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
        if (viewType == BANNER) {
            return new BannerViewHolder(mCtx,mLayoutInflater.inflate(R.layout.fragment_home_banner,null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mCtx,mLayoutInflater.inflate(R.layout.fragment_home_channel,null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(mResultBean.getBanner_info());
        } else if (type == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(mResultBean.getChannel_info());
        }
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
        return 2;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Banner banner;
        private Context ctx;

        public BannerViewHolder(Context ctx, View itemView) {
            super(itemView);
            this.banner = itemView.findViewById(R.id.banner);
            this.ctx = ctx;
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            // 设置Banner的数据
            // 1. 得到图片地址的集合
            ArrayList<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imageUrls.add(imageUrl);
            }
            // 设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            // 设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            // 2. 给Banner设置images
            banner.setImages(imageUrls);
            // 3. 给Banner设置图片加载器
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    //Glide 加载图片简单用法
                    Glide.with(context).load(ConstantValue.IMAGE_BASE_URL + path).into(imageView);
                }
            });
            // 设置item的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(ctx, "position == " + position, Toast.LENGTH_SHORT).show();
                }
            });
            // 4. 最后启动调用
            banner.start();
        }
    }

    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final GridView gv_channel;
        private Context ctx;
        private ChannelAdapter channelAdapter;

        public ChannelViewHolder(final Context ctx, View itemView) {
            super(itemView);
            this.gv_channel = itemView.findViewById(R.id.gv_channel);
            this.ctx = ctx;

            // 设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(ctx, "position == " + i, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            // 获取到ChannelInfo数据
            // 1. 设置GridView的适配器
            channelAdapter = new ChannelAdapter(ctx, channel_info);
            gv_channel.setAdapter(channelAdapter);
        }
    }
}
