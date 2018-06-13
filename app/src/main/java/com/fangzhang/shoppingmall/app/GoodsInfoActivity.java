package com.fangzhang.shoppingmall.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.home.bean.GoodsBean;
import com.fangzhang.shoppingmall.utils.ConstantValue;

/**
 * Created by MoeRookie on 2018/5/31.
 */

public class GoodsInfoActivity extends Activity implements View.OnClickListener{
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private View tv_more_home;
    private GoodsBean goodsBean;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        // 接收数据
        goodsBean = getIntent().getParcelableExtra("goods_bean");
        if (goodsBean != null) {
            setDataForView(goodsBean);
        }
    }
    
    /**
     * 添加显示数据
     * @param goodsBean 要显示的数据
     */
    private void setDataForView(GoodsBean goodsBean) {
        // 图片
        Glide.with(this)
                .load(ConstantValue
                .IMAGE_BASE_URL+goodsBean.getFigure())
                .into(ivGoodInfoImage);
        // 商品名称
        tvGoodInfoName.setText(goodsBean.getName());
        // 商品价格
        tvGoodInfoDesc.setText("￥"+goodsBean.getCover_price());
        // 配置WebView加载的网页
        setWebViewData(goodsBean.getProduct_id());
    }
    
    /**
     * 设置要显示的网页数据
     * @param product_id
     */
    private void setWebViewData(String product_id) {
        if (product_id != null) {
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            // 设置支持Js
            WebSettings webSettings = wbGoodInfoMore.getSettings();
            webSettings.setJavaScriptEnabled(true);
            // 支持双击页面变大变小
            webSettings.setUseWideViewPort(true);
            // 优先使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            wbGoodInfoMore.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }
    
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-06-11 10:21:05 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibGoodInfoBack = findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = findViewById( R.id.wb_good_info_more );
        llGoodsRoot = findViewById( R.id.ll_goods_root );
        tvGoodInfoCallcenter = findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = findViewById( R.id.btn_good_info_addcart );
        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );
    
        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
    
        tv_more_share = findViewById(R.id.tv_more_share);
        tv_more_search = findViewById(R.id.tv_more_search);
        tv_more_home = findViewById(R.id.tv_more_home);
        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);
    }
    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-06-11 10:21:05 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            // Handle clicks for ibGoodInfoBack
            finish();
        } else if ( v == ibGoodInfoMore ) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "更多 . . .", Toast.LENGTH_SHORT).show();
        } else if ( v == btnGoodInfoAddcart ) {
            Toast.makeText(this, "添加购物车", Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCallcenter ) {
            Toast.makeText(this, "联系客服", Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCollection ) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCart ) {
            Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_share ) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_search ) {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        }else if ( v == tv_more_home ) {
            Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
        }
    }
}
