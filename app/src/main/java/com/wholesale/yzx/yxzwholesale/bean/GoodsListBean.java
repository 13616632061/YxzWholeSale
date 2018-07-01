package com.wholesale.yzx.yxzwholesale.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by lyf on 2018/7/1.
 */
public class GoodsListBean  {

    private String goodsId;
    private String goodsImageUrl;
    private String goodsName;
    private double goodsPrice;
    private int    goodsSpellNums;
    private String goodsSpellPersonUrl;



    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsSpellNums() {
        return goodsSpellNums;
    }

    public void setGoodsSpellNums(int goodsSpellNums) {
        this.goodsSpellNums = goodsSpellNums;
    }

    public String getGoodsSpellPersonUrl() {
        return goodsSpellPersonUrl;
    }

    public void setGoodsSpellPersonUrl(String goodsSpellPersonUrl) {
        this.goodsSpellPersonUrl = goodsSpellPersonUrl;
    }


}