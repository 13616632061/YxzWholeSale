package com.wholesale.yzx.yxzwholesale.view.dialogAndPopup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;

/**
 * Created by Administrator on 2018/8/7.
 */

public class LogisticsStatusPopuWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private Handler del_handler;
    private TextView tv_close;
    private int type;

    public LogisticsStatusPopuWindow(final Context context, Handler del_handler, int type) {//, int position,
        super(context);
        this.context = context;
        this.del_handler = del_handler;
        this.type = type;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_logistics_status, null);

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
        ColorDrawable dw = new ColorDrawable(0x55000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_logistics_status_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        initView(mMenuView);

    }

    private void initView(View mMenuView) {
        tv_close = mMenuView.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(this);
        LinearLayout ll_ten = mMenuView.findViewById(R.id.ll_ten);
        LinearLayout ll_eleven = mMenuView.findViewById(R.id.ll_eleven);
        CheckBox iv_buttons_1 =  mMenuView.findViewById(R.id.iv_buttons_1);
        CheckBox iv_buttons_2 =  mMenuView.findViewById(R.id.iv_buttons_2);

        ll_ten.setOnClickListener(this);
        ll_eleven.setOnClickListener(this);

        switch (type) {
            case 0:
                iv_buttons_1.setChecked(false);
                iv_buttons_2.setChecked(false);
                break;
            case 1:
                iv_buttons_1.setChecked(true);
                iv_buttons_2.setChecked(false);
                break;
            case 2:
                iv_buttons_1.setChecked(false);
                iv_buttons_2.setChecked(true);
                break;
        }
    }


    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.tv_close:
                dismiss();
                break;
            case R.id.ll_ten:

                Message msg = new Message();
                msg.what = 3;
                del_handler.sendMessage(msg);
                dismiss();
                break;
            case R.id.ll_eleven:
                Message msg2 = new Message();
                msg2.what = 4;
                del_handler.sendMessage(msg2);

                dismiss();
                break;
            default:
                break;
        }
    }


}
