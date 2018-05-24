package com.fangzhang.shoppingmall.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.base.BaseFragment;

/**
 * 主页面的Fragment
 * Created by MoeRookie on 2018/2/10.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView tv_search_home;
    private TextView tv_message_home;
    private RecyclerView rv_home;
    private ImageButton ib_top;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        rv_home = view.findViewById(R.id.rv_home);
        ib_top = view.findViewById(R.id.ib_top);
        // 设置点击事件
        initListener();
        return view;
    }

    private void initListener() {
        // 监听置顶按钮
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 回到顶部
                rv_home.scrollToPosition(0);
            }
        });
        // 监听搜索框
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        // 监听消息
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        Log.e(TAG, "主页数据被初始化了");
    }
}
