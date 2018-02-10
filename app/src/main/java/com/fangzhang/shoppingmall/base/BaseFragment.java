package com.fangzhang.shoppingmall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MoeRookie on 2018/2/10.
 * Fragment基类
 *      首页：HomeFragment
 *      分类：TypeFragment
 *      发现：CommunityFragment
 *      购物车：ShoppingCartFragment
 *      用户中心：UserFragment
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 当该类对象被系统创建的时候调用
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 抽象类,由子类实现,以显示不同的效果
     * @return
     */
    public abstract View initView();

    /**
     * 当Activity被创建了的时候回调这个方法
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要联网请求数据的时候,可以重写该方法,在该方法中联网请求
     */
    protected abstract void initData();
}
