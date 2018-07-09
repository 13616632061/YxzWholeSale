package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by lyf on 2018/7/2.
 */
public class GoodsSecondTypeBean {

    private List<TypeListBean> typeList;

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public static class TypeListBean {
        /**
         * typeId : 0
         * typeName : 限时秒杀
         * typePhoto : http://img2.imgtn.bdimg.com/it/u=723835899,2909173229&fm=27&gp=0.jpg
         */

        private int typeId;
        private String typeName;
        private String typePhoto;

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypePhoto() {
            return typePhoto;
        }

        public void setTypePhoto(String typePhoto) {
            this.typePhoto = typePhoto;
        }
    }
}