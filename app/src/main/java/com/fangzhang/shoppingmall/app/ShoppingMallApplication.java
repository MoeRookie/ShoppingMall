package com.fangzhang.shoppingmall.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by MoeRookie on 2018/5/25.
 */

public class ShoppingMallApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化OkHttpUtils
        initOkHttpClient();
    }

    /**
     * 初始化OkHttpUtils
     */
    private void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
