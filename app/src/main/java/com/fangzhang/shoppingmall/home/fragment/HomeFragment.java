package com.fangzhang.shoppingmall.home.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fangzhang.shoppingmall.base.BaseFragment;

/**
 * 主页面的Fragment
 * Created by MoeRookie on 2018/2/10.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected void initData() {
        Log.e(TAG, "主页数据被初始化了");
        textView.setText(TAG);
    }
}
