package com.fangzhang.shoppingmall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.fangzhang.shoppingmall.R;
import com.fangzhang.shoppingmall.base.BaseFragment;
import com.fangzhang.shoppingmall.community.fragment.CommunityFragment;
import com.fangzhang.shoppingmall.home.fragment.HomeFragment;
import com.fangzhang.shoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.fangzhang.shoppingmall.type.fragment.TypeFragment;
import com.fangzhang.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoeRookie on 2018/2/8.
 */

public class HomeActivity extends FragmentActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.rg_home)
    RadioGroup mRgHome;
    List<BaseFragment> mFragmentList;
    private int postion; // 记录被点击Tab的位置
    /**
     * 缓存的Fragment或者上次显示的Fragment
     */
    private BaseFragment tempFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // 将ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        // （1）. 初始化所有Fragment（onCreate（）方法）
        initFragment();
        // （2）. 给RadioGroup设置监听，点击时获取到对应的position
        setListener();
    }

    /**
     * 给RadioGroup设置监听,点击时获取到对应position的方法
     */
    private void setListener() {
        mRgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        postion = 0;
                        break;
                    case R.id.rb_type:
                        postion = 1;
                        break;
                    case R.id.rb_community:
                        postion = 2;
                        break;
                    case R.id.rb_cart:
                        postion = 3;
                        break;
                    case R.id.rb_user:
                        postion = 4;
                        break;
                    default:
                        postion = 0;
                        break;
                }
                //（3）. 根据位置获取到对应的Fragment
                BaseFragment fragment = getFragment(postion);
                // （4）. 显示相应的Fragment
                switchFragment(tempFragment, fragment);

            }
        });
        // 设置第一个Rb默认选中
        mRgHome.check(R.id.rb_home);
    }

    /**
     * 显示相应Fragment的方法
     * @param fromFragment 上次显示的Fragment
     * @param nextFragment 当前要显示的Fragment
     */
    private void switchFragment(BaseFragment fromFragment, BaseFragment nextFragment) {
        if (fromFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 判断当前要显示的Fragment是否添加过
                if (!nextFragment.isAdded()) {
                    // 隐藏上次显示的Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    // 添加当前要显示的Fragment
                    transaction.add(R.id.fl_container, nextFragment).commit();
                } else {
                    // 隐藏上次要显示的Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    // 显示当前Fragment
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    /**
     * 根据位置获取到对应的Fargment的方法
     * @param postion 被选中Rb所在的位置
     * @return 对应的Fragment
     */
    private BaseFragment getFragment(int postion) {
        if (mFragmentList != null && mFragmentList.size() > 0) {
            return mFragmentList.get(postion);
        }
        return null;
    }

    /**
     * 初始化所有Fragment的方法
     */
    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new TypeFragment());
        mFragmentList.add(new CommunityFragment());
        mFragmentList.add(new ShoppingCartFragment());
        mFragmentList.add(new UserFragment());
    }
}
