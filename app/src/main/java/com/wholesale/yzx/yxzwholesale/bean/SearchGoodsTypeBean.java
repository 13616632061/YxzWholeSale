package com.wholesale.yzx.yxzwholesale.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/7/5.
 */

public class SearchGoodsTypeBean {

    private List<GoodsTypeListBean> goodsTypeList;

    public List<GoodsTypeListBean> getGoodsTypeList() {
        return goodsTypeList;
    }

    public void setGoodsTypeList(List<GoodsTypeListBean> goodsTypeList) {
        this.goodsTypeList = goodsTypeList;
    }

    public static class GoodsTypeListBean {
        /**
         * typeId : 0001
         * goodsType : 男装
         * goodsList : [{"goodsId":"1001","goodsName":"爆款","goodsImageUrl":"http://f10.baidu.com/it/u=4223110324,1704019977&fm=72"}]
         */

        private String typeId;
        private String goodsType;
        private boolean isSelect;
        private List<GoodsListBean> goodsList;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean implements MultiItemEntity {
            /**
             *
             "typeId":"0010",
             "goodsType":"百货",
             * goodsId : 1001
             * goodsName : 爆款
             * goodsImageUrl : http://f10.baidu.com/it/u=4223110324,1704019977&fm=72
             */
            private int itemType;
            private String typeId;
            private String goodsType;
            private String goodsId;
            private String goodsName;
            private String goodsImageUrl;



            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsImageUrl() {
                return goodsImageUrl;
            }

            public void setGoodsImageUrl(String goodsImageUrl) {
                this.goodsImageUrl = goodsImageUrl;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            @Override
            public int getItemType() {
                return itemType;
            }
        }
    }
}
