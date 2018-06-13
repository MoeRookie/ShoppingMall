package com.fangzhang.shoppingmall.home.bean;

import com.baoyz.pg.Parcelable;

/**
 * Created by MoeRookie on 2018/6/13.
 */
@Parcelable
public class GoodsBean {
    // 商品id
    private String product_id;
    // 图片
    private String figure;
    // 名称
    private String name;
    // 价格
    private String cover_price;
    // 数量
    private int number;
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }
    
    public GoodsBean(String product_id, String figure, String name, String cover_price) {
        this.product_id = product_id;
        this.figure = figure;
        this.name = name;
        this.cover_price = cover_price;
    }
    
    public GoodsBean() {
    }
    
    public String getProduct_id() {
        return product_id;
    }
    
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    public String getFigure() {
        return figure;
    }
    
    public void setFigure(String figure) {
        this.figure = figure;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCover_price() {
        return cover_price;
    }
    
    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }
    
    @Override
    public String toString() {
        return "GoodsBean{" +
                "product_id='" + product_id + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", cover_price='" + cover_price + '\'' +
                ", number=" + number +
                '}';
    }
}
