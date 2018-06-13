package com.fangzhang.shoppingmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.fangzhang.shoppingmall.app.ShoppingMallApplication;
import com.fangzhang.shoppingmall.home.bean.GoodsBean;
import com.fangzhang.shoppingmall.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoeRookie on 2018/6/13.
 */

public class CartStorage {
    private static final String JSON_CART = "json_cart";
    private Context context;
    private static CartStorage instance;
    // 功能类似HashMap,但性能优于HashMap
    private final SparseArray<GoodsBean> sparseArray;
    
    private CartStorage(Context ctx){
        this.context = ctx;
        // 读取之前的数据到内存中
        sparseArray = new SparseArray<>(100);
        list2SparseArray();
    }
    
    /**
     * 获取购物车实例
     * @return 购物车实例
     */
    public static CartStorage getInstance(){
        if (instance == null) {
            instance = new CartStorage(ShoppingMallApplication.getContext());
        }
        return instance;
    }
    
    /**
     * 将List集合数据转换为SparseArray将数据存储在内存中
     */
    private void list2SparseArray() {
        List<GoodsBean> goodsBeanList =  getAllData();
        // 把List数据转换为SparseArray
        for (int i = 0; i < goodsBeanList.size(); i++) {
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }
    
    /**
     * 获取购物车中所有的商品列表
     * @return 商品列表
     */
    public List<GoodsBean> getAllData() {
        ArrayList<GoodsBean> goodsBeanList = new ArrayList<>();
        // 从本地获取商品列表
        String json = CacheUtils.getString(context, JSON_CART);
        // 使用Gson转换为List商品列表
        // 判断不为空的时候执行
        if (!TextUtils.isEmpty(json)) {
            // String -> List
            goodsBeanList = new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeanList;
    }
    
    /**
     * 添加数据
     * @param goodsBean 要添加的GoodsBean
     */
    public void addData(GoodsBean goodsBean){
        // 添加到内存中(SparseArray)
        // 如果当前数据已经存在,就修改成number递增
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempData != null) {
            // 内存中已经存在了这条数据
            tempData.setNumber(tempData.getNumber() + 1);
        } else {
            tempData = goodsBean;
            tempData.setNumber(1);
        }
        // 同步到内存中
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()),tempData);
        // 同步到本地
        saveLocal();
    }
    
    /**
     * 删除数据
     * @param goodsBean 要删除的GoodsBean
     */
    public void deleteData(GoodsBean goodsBean){
        // 内存中删除
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        // 内存保持到本地
        saveLocal();
    }
    
    /**
     * 更新数据
     * @param goodsBean 要更新的GoodsBean
     */
    public void updateData(GoodsBean goodsBean){
        // 内存中更新
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        // 将内存中的保持到本地
        saveLocal();
    }
    
    private void saveLocal() {
        // SparseArray -> List
        List<GoodsBean> goodsBeanList = sparse2List();
        // List -> String(Gson)
        String json = new Gson().toJson(goodsBeanList);
        // 保存数据到sp中
        CacheUtils.saveString(context,JSON_CART,json);
    }
    
    /**
     * 将内存中商品列表数据转换为集合类型
     * @return 商品列表集合
     */
    private List<GoodsBean> sparse2List() {
        ArrayList<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }
}
