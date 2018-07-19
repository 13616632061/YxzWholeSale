package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class SecondKillBean {

    private List<SecondKillGoodsListBean> secondKillGoodsList;

    public List<SecondKillGoodsListBean> getSecondKillGoodsList() {
        return secondKillGoodsList;
    }

    public void setSecondKillGoodsList(List<SecondKillGoodsListBean> secondKillGoodsList) {
        this.secondKillGoodsList = secondKillGoodsList;
    }

    public static class SecondKillGoodsListBean {
        /**
         * time : 今日14：00
         * goodsList : [{"time":"今日14：00","goodsUrl":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4113048887,754507111&fm=27&gp=0.jpg","name":"[买一送一]唐波虎奶","peice":"12.9","stock":"56"},{"time":"今日14：00","goodsUrl":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1515229499,2804004726&fm=27&gp=0.jpg","name":"[莫高古道]青岛特供","peice":"99.9","stock":"16"},{"time":"今日14：00","goodsUrl":"https://f11.baidu.com/it/u=2948811668,602204235&fm=72","name":"[手工肉松","peice":"9.9","stock":"61"}]
         */

        private String time;
        private boolean isSeclect;
        private List<GoodsListBean> goodsList;

        public boolean isSeclect() {
            return isSeclect;
        }

        public void setSeclect(boolean seclect) {
            isSeclect = seclect;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * time : 今日14：00
             * goodsUrl : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4113048887,754507111&fm=27&gp=0.jpg
             * name : [买一送一]唐波虎奶
             * peice : 12.9
             * stock : 56
             */

            private String time;
            private String goodsUrl;
            private String name;
            private String peice;
            private String stock;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getGoodsUrl() {
                return goodsUrl;
            }

            public void setGoodsUrl(String goodsUrl) {
                this.goodsUrl = goodsUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPeice() {
                return peice;
            }

            public void setPeice(String peice) {
                this.peice = peice;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }
        }
    }
}
