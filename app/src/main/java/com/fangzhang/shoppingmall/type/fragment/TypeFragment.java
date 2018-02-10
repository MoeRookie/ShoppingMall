package com.fangzhang.shoppingmall.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fangzhang.shoppingmall.base.BaseFragment;
import com.fangzhang.shoppingmall.home.fragment.HomeFragment;

/**
 * 分类的Fragment
 * Created by MoeRookie on 2018/2/10.
 */

public class TypeFragment extends BaseFragment {
    private static final String TAG = TypeFragment.class.getSimpleName();
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG, "分类视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected void initData() {
        Log.e(TAG, "分类数据被初始化了");
        textView.setText(TAG);
    }
}
