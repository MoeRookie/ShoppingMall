package com.fangzhang.shoppingmall.home.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.base.BaseFragment;

/**
 * 主页面的Fragment
 * Created by MoeRookie on 2018/2/10.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initData() {
        Log.e(TAG, "主页数据被初始化了");
    }
}
