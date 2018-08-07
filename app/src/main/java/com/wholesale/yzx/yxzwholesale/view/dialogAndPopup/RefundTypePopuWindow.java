package com.wholesale.yzx.yxzwholesale.view.dialogAndPopup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;

/**
 * Created by Administrator on 2018/8/7.
 */

public class RefundTypePopuWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private Handler del_handler;
    private Bundle bundle;

    public RefundTypePopuWindow(final Context context, Handler del_handler) {//, int position,
        super(context);
        this.context = context;
        this.del_handler = del_handler;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_refund_type, null);

        this.setContentView(mMenuView);
        //sdk > 21 解决 标题栏没有办法遮罩的问题
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ScreenUtil.getScreenHeight(context));
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_refund_type_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        LinearLayout ll_one= (LinearLayout) mMenuView.findViewById(R.id.ll_one);
        LinearLayout ll_two= (LinearLayout) mMenuView.findViewById(R.id.ll_two);
        ll_one.setOnClickListener(this);
        ll_two.setOnClickListener(this);

    }



    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.ll_one:
                Message msg = new Message();
                msg.what = 1;
                del_handler.sendMessage(msg);

                RefundTypePopuWindow.this.dismiss();
                break;
            case R.id.ll_two:
                Message msg2 = new Message();
                msg2.what = 2;
                del_handler.sendMessage(msg2);

                RefundTypePopuWindow.this.dismiss();
                break;
            default:
                break;
        }
    }


}
