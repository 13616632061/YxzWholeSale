package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class MorePellingOrderInfoBean {

    private List<PellingOrderListBean> pellingOrderList;

    public List<PellingOrderListBean> getPellingOrderList() {
        return pellingOrderList;
    }

    public void setPellingOrderList(List<PellingOrderListBean> pellingOrderList) {
        this.pellingOrderList = pellingOrderList;
    }

    public static class PellingOrderListBean {
        /**
         * photo : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1612730903,2278218101&fm=27&gp=0.jpg
         * name : 0荣
         */

        private String photo;
        private String name;

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
    }
}
