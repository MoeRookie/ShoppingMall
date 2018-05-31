package com.fangzhang.shoppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.base.BaseFragment;
import com.fangzhang.shoppingmall.home.bean.ResultBeanData;
import com.fangzhang.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.fangzhang.shoppingmall.utils.ConstantValue;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

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
    private HomeFragmentAdapter homeFragmentAdapter;

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
        // 联网请求主页的数据
        getDataFromNet();
    }

    /**
     * 请求网络数据
     */
    private void getDataFromNet() {
        String url = ConstantValue.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 请求失败时回调
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "首页请求失败 == " + e.getMessage());
                    }

                    /**
                     * 请求成功时回调
                     * @param response 响应到的字符串等数据
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "首页请求成功 == " + response);
                        precessData(response);
                    }
                });
    }

    /**
     * 解析json数据
     * @param response 待解析的json串
     */
    private void precessData(String response) {
        // 解析数据
        ResultBeanData resultBeanData = JSON.parseObject(response, ResultBeanData.class);
        ResultBeanData.ResultBean resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            // 设置适配器
            /**
             * 首页有很多类型的数据
             * 并不是说每一种类型的数据我都要对应的设置相应的适配器
             * 其实RecyclerView是支持条目类型不同的
             * 也就是说,我只要把数据设置到不同的条目上显示就可以了
             */
            homeFragmentAdapter = new HomeFragmentAdapter(mContext, resultBean);
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1);
            rv_home.setLayoutManager(layoutManager);
            // 设置跨度大小监听
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position <= 3) {
                        // 隐藏
                        ib_top.setVisibility(View.GONE);
                    } else {
                        // 显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    // 只能返回1
                    return 1;
                }
            });
            rv_home.setAdapter(homeFragmentAdapter);
        }
    }
}
