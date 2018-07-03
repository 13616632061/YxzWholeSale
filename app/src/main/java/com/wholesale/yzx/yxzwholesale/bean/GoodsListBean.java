package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by lyf on 2018/7/1.
 */
public class GoodsListBean  {


    private List<ListFreshTypeBean> listFreshType;

    public List<ListFreshTypeBean> getListFreshType() {
        return listFreshType;
    }

    public void setListFreshType(List<ListFreshTypeBean> listFreshType) {
        this.listFreshType = listFreshType;
    }

    public static class ListFreshTypeBean {
        /**
         * goodsID : 2
         * classID : 1
         * goodsName : 韩都衣舍韩版2015秋装新款女装翻领绣花图案长袖衬衫
         * goodsListImg : https:////img.alicdn.com/bao/uploaded///img.alicdn.com/bao/uploaded/i4/TB1vGO5IFXXXXb4XXXXXXXXXXXX_!!0-item_pic.jpg_160x160q90.jpg
         * price : 260
         * discount : 6
         */

        private String goodsID;
        private String classID;
        private String goodsName;
        private String goodsListImg;
        private String price;
        private String discount;

        public String getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(String goodsID) {
            this.goodsID = goodsID;
        }

        public String getClassID() {
            return classID;
        }

        public void setClassID(String classID) {
            this.classID = classID;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsListImg() {
            return goodsListImg;
        }

        public void setGoodsListImg(String goodsListImg) {
            this.goodsListImg = goodsListImg;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }
}