package com.fangzhang.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.pg.PG;
import com.bumptech.glide.Glide;
import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.app.GoodsInfoActivity;
import com.fangzhang.shoppingmall.home.bean.GoodsBean;
import com.fangzhang.shoppingmall.home.bean.ResultBeanData;
import com.fangzhang.shoppingmall.utils.ConstantValue;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.ScaleInOutTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MoeRookie on 2018/5/25.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter{
    private static final String TAG = HomeFragmentAdapter.class.getSimpleName();
    private static final String GOODS_BEAN = "goods_bean";
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
        } else if (viewType == ACT) {
            return new ActViewHolder(mCtx,mLayoutInflater.inflate(R.layout.fragment_home_act,null));
        }else if (viewType == SECKILL) {
            return new SecKillViewHolder(mCtx,mLayoutInflater.inflate(R.layout.fragment_home_seckill,null));
        }else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mCtx,mLayoutInflater.inflate(R.layout.fragment_home_recommend,null));
        }else if (viewType == HOT) {
            return new HotViewHolder(mCtx,mLayoutInflater.inflate(R.layout.fragment_home_hot,null));
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
        } else if (type == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(mResultBean.getAct_info());
        } else if (type == SECKILL) {
            SecKillViewHolder secKillViewHolder = (SecKillViewHolder) holder;
            secKillViewHolder.setData(mResultBean.getSeckill_info());
        } else if (type == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(mResultBean.getRecommend_info());
        } else if (type == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(mResultBean.getHot_info());
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
        return 6;
    }
    /**
     * 横幅广告的Holder
     */
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
    
    /**
     * 启动商品信息列表界面
     * @param goodsBean
     */
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        // 序列化goodsBean
        Intent intent = new Intent(mCtx, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN, PG.convertParcelable(goodsBean));
        mCtx.startActivity(intent);
    }
    
    /**
     * 频道的Holder
     */
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
    /**
     * 活动的Holder
     */
    private class ActViewHolder extends RecyclerView.ViewHolder {
        private final ViewPager act_viewpager;
        private Context ctx;

        public ActViewHolder(Context ctx, View itemView) {
            super(itemView);
            this.act_viewpager = itemView.findViewById(R.id.act_viewpager);
            this.ctx = ctx;
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            // 使用美化库对ViewPager进行美化
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);//>=3
            // 设置动画效果
            act_viewpager.setPageTransformer(true,new ScaleInOutTransformer());
            // 获取到了活动数据
            // 1. 给ViewPager设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info == null?0:act_info.size();
                }

                /**
                 * @param view 页面
                 * @param object instantiateItem()方法的返回值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view == object;
                }

                /**
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                    // 1. 每个Item应该是一个ImageView
                    ImageView imageView = new ImageView(ctx);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    // 2. 给ImageView设置数据
                    Glide.with(ctx)
                            .load(ConstantValue.IMAGE_BASE_URL+act_info.get(position).getIcon_url())
                            .into(imageView);
                    // 4. 给imageView设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(ctx, "position == " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    // 3. 将Item添加到容器中
                    container.addView(imageView);
                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((ImageView) object);
                }
            });
        }
    }

    /**
     * 秒杀的Holder
     */
    private class SecKillViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_time_seckill;
        private final TextView tv_more_seckill;
        private final RecyclerView rv_seckill;
        private Context ctx;
        private SecKillAdapter secKillAdapter;
        /**
         * 相差多少时间 - 毫秒
         */
        // 1. 创建Handler对象,重写handleMessage方法(设置时间显示)
        private long dt = 0;
        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // 设置时间显示
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String time = format.format(new Date(dt));
                tv_time_seckill.setText(time);
                handler.removeMessages(0);
                // 改变dt,重发消息(直到dt<=0)
                dt = dt - 1000;
                handler.sendEmptyMessageDelayed(0,1000);
                if (dt <= 0) {
                    // 移除消息
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
        public SecKillViewHolder(Context ctx, View itemView) {
            super(itemView);
            this.ctx = ctx;
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            // 2. 获取时间间隔
            dt = Integer.valueOf(seckill_info.getEnd_time())
                    -Integer.valueOf(seckill_info.getStart_time());
            // 3. 发送延时消息
            handler.sendEmptyMessageDelayed(0,1000);
            // 1. 获取到列表数据
            final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> seckill_infoList
                    = seckill_info.getList();
            // 2. 创建RecyclerView的适配器
            secKillAdapter = new SecKillAdapter(mCtx, seckill_infoList);
            // 设置条目被点击时的监听
            secKillAdapter.setOnItemClickListener(new SecKillAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // 根据seckill_infoList获取到listBean对象
                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = seckill_infoList.get(position);
                    GoodsBean goodsBean = new GoodsBean(listBean.getProduct_id()
                    ,listBean.getFigure(),listBean.getName(),listBean.getCover_price());
                    startGoodsInfoActivity(goodsBean);
                }
            });
            // 3. 设置适配器到RecyclerView上
            rv_seckill.setAdapter(secKillAdapter);
            // 4. 别忘了给RecyclerView设置布局管理器
            rv_seckill.setLayoutManager(
                    new LinearLayoutManager(ctx,LinearLayoutManager.HORIZONTAL,false));
        }
    }
    
    /**
     * 推荐的Holder
     */
    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_more_recommend;
        private final GridView gv_recommend;
        private Context ctx;
        private RecommendAdapter recommendAdapter;
    
        public RecommendViewHolder(Context ctx, View itemView) {
            super(itemView);
            this.ctx = ctx;
            tv_more_recommend = itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = itemView.findViewById(R.id.gv_recommend);
        }
    
        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            // 1. 获取到推荐列表
            // 2. 创建GridView的适配器
            recommendAdapter = new RecommendAdapter(ctx, recommend_info);
            // 3. 给GridView设置适配器
            gv_recommend.setAdapter(recommendAdapter);
            // 4. 给GridView设置列表项点击事件监听
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // 根据recommend_info获取到recommendInfoBean对象
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(i);
                    GoodsBean goodsBean = new GoodsBean(recommendInfoBean.getProduct_id()
                            ,recommendInfoBean.getFigure(),recommendInfoBean.getName(),recommendInfoBean.getCover_price());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }
    
    /**
     * 热卖的Holder
     */
    private class HotViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_more_hot;
        private final GridView gv_hot;
        private Context ctx;
    
        public HotViewHolder(final Context ctx, View itemView) {
            super(itemView);
            this.ctx = ctx;
            tv_more_hot = itemView.findViewById(R.id.tv_more_hot);
            gv_hot = itemView.findViewById(R.id.gv_hot);
        }
    
        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            // 1. 创建gv_hot的适配器
            HotAdapter hotAdapter = new HotAdapter(ctx, hot_info);
            // 2. 将该适配器设置到gv_hot上
            gv_hot.setAdapter(hotAdapter);
            // 设置item的点击事件
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // 根据hot_info获取到hotInfoBean对象
                    ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hot_info.get(i);
                    GoodsBean goodsBean = new GoodsBean(hotInfoBean.getProduct_id()
                            ,hotInfoBean.getFigure(),hotInfoBean.getName(),hotInfoBean.getCover_price());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }
}
