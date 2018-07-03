package com.wholesale.yzx.yxzwholesale.util;

/**
 * Created by Administrator on 2018/7/3.
 */

public class ImageUtil {
    /**
     * 阿里云设置图片缩略图大小
     * @param size
     * @return
     */
    public static String ossSetImageSize(int size){
        return "?x-oss-process=image/resize,w_"+size;
    }

}
