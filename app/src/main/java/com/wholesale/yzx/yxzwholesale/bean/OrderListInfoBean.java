package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class OrderListInfoBean {

    private List<OrderListBean> orderList;

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * orderId : 001
         * shopLogo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2513028765,2563288400&fm=27&gp=0.jpg
         * shopName : 果大食品专营店
         * orderStadue : 0
         * orderPrice : 2.99
         * orderDetail : [{"goodPicture":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3298787981,176541398&fm=27&gp=0.jpg","goodName":"焦糖核桃原味水煮瓜子","goodPrice":"2.99","goodNum":1,"goodDescribe":"原味100g*2袋"}]
         * orderPellInfo : [{"pellPersonPhoto":"http://img5.imgtn.bdimg.com/it/u=1660719847,2128667878&fm=27&gp=0.jpg"},{"pellPersonPhoto":"http://img4.imgtn.bdimg.com/it/u=2500434987,4293101441&fm=27&gp=0.jpg"}]
         */

        private String orderId;
        private String shopLogo;
        private String shopName;
        private String orderStadue;
        private String orderPrice;
        private List<OrderDetailBean> orderDetail;
        private List<OrderPellInfoBean> orderPellInfo;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getOrderStadue() {
            return orderStadue;
        }

        public void setOrderStadue(String orderStadue) {
            this.orderStadue = orderStadue;
        }

        public String getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            this.orderPrice = orderPrice;
        }

        public List<OrderDetailBean> getOrderDetail() {
            return orderDetail;
        }

        public void setOrderDetail(List<OrderDetailBean> orderDetail) {
            this.orderDetail = orderDetail;
        }

        public List<OrderPellInfoBean> getOrderPellInfo() {
            return orderPellInfo;
        }

        public void setOrderPellInfo(List<OrderPellInfoBean> orderPellInfo) {
            this.orderPellInfo = orderPellInfo;
        }

        public static class OrderDetailBean {
            /**
             * goodPicture : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3298787981,176541398&fm=27&gp=0.jpg
             * goodName : 焦糖核桃原味水煮瓜子
             * goodPrice : 2.99
             * goodNum : 1
             * goodDescribe : 原味100g*2袋
             */

            private String goodPicture;
            private String goodName;
            private String goodPrice;
            private int goodNum;
            private String goodDescribe;

            public String getGoodPicture() {
                return goodPicture;
            }

            public void setGoodPicture(String goodPicture) {
                this.goodPicture = goodPicture;
            }

            public String getGoodName() {
                return goodName;
            }

            public void setGoodName(String goodName) {
                this.goodName = goodName;
            }

            public String getGoodPrice() {
                return goodPrice;
            }

            public void setGoodPrice(String goodPrice) {
                this.goodPrice = goodPrice;
            }

            public int getGoodNum() {
                return goodNum;
            }

            public void setGoodNum(int goodNum) {
                this.goodNum = goodNum;
            }

            public String getGoodDescribe() {
                return goodDescribe;
            }

            public void setGoodDescribe(String goodDescribe) {
                this.goodDescribe = goodDescribe;
            }
        }

        public static class OrderPellInfoBean {
            /**
             * pellPersonPhoto : http://img5.imgtn.bdimg.com/it/u=1660719847,2128667878&fm=27&gp=0.jpg
             */

            private String pellPersonPhoto;

            public String getPellPersonPhoto() {
                return pellPersonPhoto;
            }

            public void setPellPersonPhoto(String pellPersonPhoto) {
                this.pellPersonPhoto = pellPersonPhoto;
            }
        }
    }
}
