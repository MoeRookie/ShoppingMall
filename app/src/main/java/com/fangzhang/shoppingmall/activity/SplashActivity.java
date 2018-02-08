package com.fangzhang.shoppingmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fangzhang.shoppingmall.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 两秒钟之后进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            // run() 方法在主线程中执行
            public void run() {
                // 启动主页面
                startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                // 关闭当前页面
                finish();
            }
        },2000);
    }
}
