package com.fangzhang.shoppingmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.fangzhang.shoppingmall.R;

/**
 * Created by MoeRookie on 2018/2/8.
 */

public class HomeActivity extends Activity{
    private static final String TAG = HomeActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}