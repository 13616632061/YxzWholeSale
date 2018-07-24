package com.wholesale.yzx.yxzwholesale.view.dialogAndPopup;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;
import com.wholesale.yzx.yxzwholesale.view.activity.OrderPayActivity;
import com.wholesale.yzx.yxzwholesale.view.widght.AmountView;
import com.wholesale.yzx.yxzwholesale.view.widght.WarpLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class PellingOrderPopuWindow extends PopupWindow implements View.OnClickListener {

    private ImageView iv_goods_picture,iv_close;
    private WarpLinearLayout warp_layout1,warp_layout2;
    private TextView tv_sure;
    private AmountView amount_view;
    private Context context;

    public PellingOrderPopuWindow(Context context) {
        super(context);
        this.context=context;
        final View view=View.inflate(context, R.layout.pelling_order_popu_window,null);
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
        iv_goods_picture=view.findViewById(R.id.iv_goods_picture);
        iv_close=view.findViewById(R.id.iv_close);
        warp_layout1=view.findViewById(R.id.warp_layout1);
        warp_layout2=view.findViewById(R.id.warp_layout2);
        tv_sure=view.findViewById(R.id.tv_sure);
        amount_view=view.findViewById(R.id.amount_view);

        String url="https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/TB1K46CHXXXXXcbXpXXXXXXXXXX_!!0-item_pic.jpg_220x220.jpg";
        Glide.with(context).load(url).into(iv_goods_picture);

        amount_view.setAmount(1);

        initData();

        iv_close.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_sure:
                Intent intent=new Intent(context, OrderPayActivity.class);
                context.startActivity(intent);
                break;
        }
    }

    private void initData(){
        List<String> colorData=new ArrayList<>();
        colorData.add("蓝色");
        colorData.add("绿色");
        colorData.add("粉色");
        colorData.add("米色");

        warp_layout1.removeAllViews();
        for(int i=0;i<colorData.size();i++){
            View view=View.inflate(context,R.layout.goods_comment_type_layout,null);
            TextView tv_comment_type=view.findViewById(R.id.tv_comment_type);
            tv_comment_type.setText(colorData.get(i));

            warp_layout1.addView(view);
        }

        List<String> modelData=new ArrayList<>();
        modelData.add("160/S");
        modelData.add("165/S[适合100-120斤]");
        modelData.add("170/S[适合110-130斤]");
        modelData.add("180/S[适合130-150斤]");

        warp_layout2.removeAllViews();
        for(int i=0;i<modelData.size();i++){
            View view=View.inflate(context,R.layout.goods_comment_type_layout,null);
            TextView tv_comment_type=view.findViewById(R.id.tv_comment_type);
            tv_comment_type.setText(modelData.get(i));

            warp_layout2.addView(view);
        }
    }
}
