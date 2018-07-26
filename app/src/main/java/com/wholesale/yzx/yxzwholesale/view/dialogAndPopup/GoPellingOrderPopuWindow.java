package com.wholesale.yzx.yxzwholesale.view.dialogAndPopup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;

import org.raphets.roundimageview.RoundImageView;

/**
 * Created by Administrator on 2018/7/26.
 */

public class GoPellingOrderPopuWindow extends PopupWindow implements View.OnClickListener {


    private Context context;
    private RoundImageView iv_image1,iv_image2;
    private ImageView iv_close;
    private Button btn_go_pelling_order;
    private Handler handler;

    public GoPellingOrderPopuWindow(Context context,Handler handler) {
        super(context);

        this.context=context;
        this.handler=handler;


        final View view=View.inflate(context, R.layout.go_pelling_order_popu_layout,null);
        initView(view);

        this.setContentView(view);
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
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pell_order_popu).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    private void initView(View view) {
        iv_image1=view.findViewById(R.id.iv_image1);
        iv_image2=view.findViewById(R.id.iv_image2);
        iv_close=view.findViewById(R.id.iv_close);
        btn_go_pelling_order=view.findViewById(R.id.btn_go_pelling_order);

        String url="http://img4.imgtn.bdimg.com/it/u=2628386979,3922899995&fm=27&gp=0.jpg";
        Glide.with(context).load(url).into(iv_image1);

        iv_close.setOnClickListener(this);
        btn_go_pelling_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.btn_go_pelling_order:

                handler.sendEmptyMessage(1);
                dismiss();
                break;
        }
    }
}
