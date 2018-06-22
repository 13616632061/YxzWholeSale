package com.wholesale.yzx.yxzwholesale.view.widght;

/**
 * Created by Administrator on 2018/6/19.
 */

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;

/**
 * TODO 加载动画
 */
public  class Loading extends Dialog {
    private final RotateAnimation refreshingAnimation;


    public  Loading(Context context, String src) {
        super(context, R.style.Loading);
        // 加载布局
        setContentView(R.layout.dialog_loading_layout);
        // 设置Dialog参数
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        ImageView iv_center = (ImageView) findViewById(R.id.iv_center);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.rotating);
        refreshingAnimation.setInterpolator(new LinearInterpolator());
        iv_center.startAnimation(refreshingAnimation);

        TextView tv_text= (TextView) findViewById(R.id.tv_text);
        if(!TextUtils.isEmpty(src)){
            tv_text.setVisibility(View.VISIBLE);
            tv_text.setText(src);
        }else {
            tv_text.setVisibility(View.GONE);
        }
    }
    public  Loading(Context context) {
        super(context, R.style.Loading);
        // 加载布局
        setContentView(R.layout.dialog_loading_layout1);
        // 设置Dialog参数
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        ImageView iv_center = (ImageView) findViewById(R.id.iv_center);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.rotating);
        refreshingAnimation.setInterpolator(new LinearInterpolator());
        iv_center.startAnimation(refreshingAnimation);
    }
}
