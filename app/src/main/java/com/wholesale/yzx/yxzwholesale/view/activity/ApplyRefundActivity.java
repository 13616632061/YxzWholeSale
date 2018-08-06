package com.wholesale.yzx.yxzwholesale.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ApplyRefundActivity extends BaseActivity {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected int getContentId() {
        return R.layout.activity_apply_refund;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("申请退款", true, "", 0);
    }

}
