package com.fangzhang.shoppingmall.shoppingcart.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangzhang.shoppingmall.R;

/**
 * Created by MoeRookie on 2018/6/15.
 */

public class AddSubView extends LinearLayout {
    
    private static final String NAME_SPACE = "http://schemas.android.com/apk/res/com.example.myaddsubview";
    private OnNumberChangeListener listener;
    private Context context;
    private ImageView btn_sub;
    private TextView tv_count;
    private ImageView btn_add;
    private int value;
    private int minValue = 1;
    private int maxValue = 10;
    
    public AddSubView(Context context) {
        this(context,null);
    }
    
    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public AddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        // xml -> view 将实现好的样式布局转换成view对象,直接添加到当前的AddSubView中
        View.inflate(context,R.layout.view_add_sub,this);
        // 获取组合控件中的所有控件
        btn_sub = findViewById(R.id.btn_sub);
        tv_count = findViewById(R.id.tv_count);
        btn_add = findViewById(R.id.btn_add);
        // 获取自定义以及原生属性,从AttributeSet attrs对象中获取(写在此处)
        initAttrs(attrs);
        // 给控件赋值/添加事件监听
        if(value > 0){
            tv_count.setText(value+"");
        }
        btn_sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value > minValue) {
                    tv_count.setText(--value+"");
                }
                listener.subNumber(view,value);
            }
        });
        btn_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value < maxValue) {
                    tv_count.setText(++value+"");
                }
                listener.addNumber(view,value);
            }
        });
    }
    
    /**
     * 当数量发生变化时回调
     */
    public interface OnNumberChangeListener{
        void addNumber(View view, int value);
        void subNumber(View view, int value);
    }
    
    public void setOnNumberChangeListener(OnNumberChangeListener listener) {
        this.listener = listener;
    }
    
    /**
     * 获取自定义以及原生属性
     * @param attrs 自定义以及原生属性
     */
    @SuppressLint("RestrictedApi")
    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            @SuppressLint("RestrictedApi")
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);
            value = tintTypedArray.getInt(R.styleable.AddSubView_value, 1);
        }
    }
    
    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue > 0 ? minValue : 1;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue > 0 ? maxValue : 10;
    }
}
