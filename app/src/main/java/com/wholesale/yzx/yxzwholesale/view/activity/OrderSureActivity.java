package com.wholesale.yzx.yxzwholesale.view.activity;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;

public class OrderSureActivity extends BaseActivity {


    @InjectView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @InjectView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @InjectView(R.id.btn_go_pay)
    Button btnGoPay;

    @Override
    protected int getContentId() {
        return R.layout.activity_order_sure;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("订单确认", false, "取消", 0);
        ivTitleBack.setVisibility(View.GONE);
        ivTitleTextLeft2.setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_title_text_right,R.id.btn_go_pay})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.iv_title_text_right://取消
                break;
            case R.id.btn_go_pay://立即支付
                break;
        }
    }


}
