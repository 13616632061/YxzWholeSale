package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class OrderPayGoodsBean {

    List<GoodsShopInfoBean> goodsShopInfoBeanList;

    public List<GoodsShopInfoBean> getGoodsShopInfoBeanList() {
        return goodsShopInfoBeanList;
    }

    public void setGoodsShopInfoBeanList(List<GoodsShopInfoBean> goodsShopInfoBeanList) {
        this.goodsShopInfoBeanList = goodsShopInfoBeanList;
    }

    public static  class GoodsShopInfoBean{
        String shop_logo;
        List<GoodsBean> goodsBeanList;

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }

        public List<GoodsBean> getGoodsBeanList() {
            return goodsBeanList;
        }

        public void setGoodsBeanList(List<GoodsBean> goodsBeanList) {
            this.goodsBeanList = goodsBeanList;
        }

        public static class GoodsBean{
            String goodImage;

            public String getGoodImage() {
                return goodImage;
            }

            public void setGoodImage(String goodImage) {
                this.goodImage = goodImage;
            }
        }
    }

}
