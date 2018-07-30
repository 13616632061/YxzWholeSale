package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/30.
 */

public class GoodsStorePellingOrderBean {

    private List<PellingInfoListBean> pellingInfoList;

    public List<PellingInfoListBean> getPellingInfoList() {
        return pellingInfoList;
    }

    public void setPellingInfoList(List<PellingInfoListBean> pellingInfoList) {
        this.pellingInfoList = pellingInfoList;
    }

    public static class PellingInfoListBean {
        /**
         * photo : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1612730903,2278218101&fm=27&gp=0.jpg
         * name : 0荣
         * time : 04:15:18
         * goodsImage : http://img3.imgtn.bdimg.com/it/u=2348237451,1480165332&fm=27&gp=0.jpg
         * price : 19.9
         */

        private String photo;
        private String name;
        private String time;
        private String goodsImage;
        private double price;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
