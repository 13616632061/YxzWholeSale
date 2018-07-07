package com.wholesale.yzx.yxzwholesale.bean;

import java.util.List;

/**
 * Created by lyf on 2018/7/7.
 */
public class ChatMessageBean {

    private List<ChatMessageListBean> chatMessageList;

    public List<ChatMessageListBean> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessageListBean> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public static class ChatMessageListBean {
        /**
         * chatPersonPhoto : http://c.hiphotos.baidu.com/image/h%3D300/sign=ed84f4d0c4fcc3ceabc0cf33a244d6b7/cefc1e178a82b901e004bbc17f8da9773812ef93.jpg
         * chatName : 潮衣服饰
         * chatContent : 亲，很高兴为您服务，请问您要咨询什么问题呢？
         * chatTime : 10:35
         * chatType : 2
         */

        private String chatPersonPhoto;
        private String chatName;
        private String chatContent;
        private String chatTime;
        private String chatType;
        private boolean isShowDel;

        public boolean isShowDel() {
            return isShowDel;
        }

        public void setShowDel(boolean showDel) {
            isShowDel = showDel;
        }

        public String getChatPersonPhoto() {
            return chatPersonPhoto;
        }

        public void setChatPersonPhoto(String chatPersonPhoto) {
            this.chatPersonPhoto = chatPersonPhoto;
        }

        public String getChatName() {
            return chatName;
        }

        public void setChatName(String chatName) {
            this.chatName = chatName;
        }

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
        }

        public String getChatTime() {
            return chatTime;
        }

        public void setChatTime(String chatTime) {
            this.chatTime = chatTime;
        }

        public String getChatType() {
            return chatType;
        }

        public void setChatType(String chatType) {
            this.chatType = chatType;
        }
    }
}