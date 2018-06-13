package com.fangzhang.shoppingmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MoeRookie on 2018/6/13.
 */

public class CacheUtils {
    
    /**
     * 获取本地保存的String类型的商品列表
     * @param context
     * @param key
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("congqi", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
    
    /**
     * 保存String类型的商品列表数据
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("congqi", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
