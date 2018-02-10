package com.fangzhang.shoppingmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.fangzhang.shoppingmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoeRookie on 2018/2/8.
 */

public class HomeActivity extends Activity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.rg_home)
    RadioGroup mRgHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // 将ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        // 设置第一个Rb默认选中
        mRgHome.check(R.id.rb_home);
    }
}
