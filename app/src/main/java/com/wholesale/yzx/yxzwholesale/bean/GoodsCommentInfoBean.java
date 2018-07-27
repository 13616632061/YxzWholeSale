package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */

public class GoodsCommentInfoBean {

    private List<GoodsCommentListBean> goodsCommentList;

    public List<GoodsCommentListBean> getGoodsCommentList() {
        return goodsCommentList;
    }

    public void setGoodsCommentList(List<GoodsCommentListBean> goodsCommentList) {
        this.goodsCommentList = goodsCommentList;
    }

    public static class GoodsCommentListBean {
        /**
         * commenterPhoto : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1114957251,3704468764&fm=27&gp=0.jpg
         * commenterName : 至善
         * commentTime : 2018.07.27
         * commentContent : 很不错，喜欢
         * commentImage : [{"imageUrl":"http://img5.imgtn.bdimg.com/it/u=1571428641,1791014462&fm=27&gp=0.jpg"}]
         */

        private String commenterPhoto;
        private String commenterName;
        private String commentTime;
        private String commentContent;
        private List<CommentImageBean> commentImage;

        public String getCommenterPhoto() {
            return commenterPhoto;
        }

        public void setCommenterPhoto(String commenterPhoto) {
            this.commenterPhoto = commenterPhoto;
        }

        public String getCommenterName() {
            return commenterName;
        }

        public void setCommenterName(String commenterName) {
            this.commenterName = commenterName;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public List<CommentImageBean> getCommentImage() {
            return commentImage;
        }

        public void setCommentImage(List<CommentImageBean> commentImage) {
            this.commentImage = commentImage;
        }

        public static class CommentImageBean {
            /**
             * imageUrl : http://img5.imgtn.bdimg.com/it/u=1571428641,1791014462&fm=27&gp=0.jpg
             */

            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
