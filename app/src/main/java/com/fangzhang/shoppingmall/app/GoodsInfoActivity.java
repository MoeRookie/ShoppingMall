package com.fangzhang.shoppingmall.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fangzhang.shoppingmall.R;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
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
            // Handle clicks for btnGoodInfoAddcart
        }
    }
}
