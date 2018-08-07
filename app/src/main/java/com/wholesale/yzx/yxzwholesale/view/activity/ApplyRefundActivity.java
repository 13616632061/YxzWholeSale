package com.wholesale.yzx.yxzwholesale.view.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.bean.OrderListInfoBean;
import com.wholesale.yzx.yxzwholesale.view.adapter.ApplyRefundAdapter;
import com.wholesale.yzx.yxzwholesale.view.dialogAndPopup.LogisticsStatusPopuWindow;
import com.wholesale.yzx.yxzwholesale.view.dialogAndPopup.RefundReasonsWindow;
import com.wholesale.yzx.yxzwholesale.view.dialogAndPopup.RefundTypePopuWindow;
import com.wholesale.yzx.yxzwholesale.view.widght.WarpLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class ApplyRefundActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;
    @InjectView(R.id.activity_apply_refund)
    RelativeLayout activityApplyRefund;

    private TextView tv_type, tv_status, tv_reason;
    private EditText et_refund_price, et_explain;
    private WarpLinearLayout warp_layout;

    private List<OrderListInfoBean.OrderListBean.OrderDetailBean> datas = new ArrayList<>();
    private ApplyRefundAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.activity_apply_refund;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("申请退款", true, "", 0);

        View footerView = View.inflate(this, R.layout.apply_refund_footer_view, null);
        initFooterView(footerView);

        adapter = new ApplyRefundAdapter(this, R.layout.item_order_list_body, datas);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter.addFooterView(footerView);

        initdata();

    }

    private void initdata() {
        OrderListInfoBean.OrderListBean.OrderDetailBean bean = new OrderListInfoBean.OrderListBean.OrderDetailBean();
        bean.setGoodPicture(getIntent().getStringExtra("OrderDetail_GoodPicture"));
        bean.setGoodName(getIntent().getStringExtra("tv_goods_name"));
        bean.setGoodDescribe(getIntent().getStringExtra("OrderDetail_Describe"));
        bean.setGoodPrice(getIntent().getStringExtra("OrderDetail_GoodPrice"));
        bean.setGoodNum(1);
        datas.add(bean);
        adapter.notifyDataSetChanged();
    }

    private void initFooterView(View footerView) {
        tv_type = footerView.findViewById(R.id.tv_type);
        tv_status = footerView.findViewById(R.id.tv_status);
        tv_reason = footerView.findViewById(R.id.tv_reason);
        et_refund_price = footerView.findViewById(R.id.et_refund_price);
        et_explain = footerView.findViewById(R.id.et_explain);
        warp_layout = footerView.findViewById(R.id.warp_layout);

        tv_type.setOnClickListener(this);
        tv_status.setOnClickListener(this);
        tv_reason.setOnClickListener(this);

    }

    @OnClick({R.id.left_return_btn, R.id.tv_submit})
    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.left_return_btn:
                finish();
                break;
            case R.id.tv_submit:

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_type://退款类型
                RefundTypePopuWindow mRefundTypePopuWindow = new RefundTypePopuWindow(ApplyRefundActivity.this, handler);
                mRefundTypePopuWindow.showAtLocation(activityApplyRefund, Gravity.NO_GRAVITY,0,0);
                break;
            case R.id.tv_status://物流状态
                LogisticsStatusPopuWindow mLogisticsStatusPopuWindow = new LogisticsStatusPopuWindow(ApplyRefundActivity.this, handler,1);
                mLogisticsStatusPopuWindow.showAtLocation(activityApplyRefund, Gravity.NO_GRAVITY,0,0);
                break;
            case R.id.tv_reason://退款原因
                RefundReasonsWindow mRefundReasonsWindow=new RefundReasonsWindow(ApplyRefundActivity.this, handler,1);
                mRefundReasonsWindow.showAtLocation(activityApplyRefund, Gravity.NO_GRAVITY,0,0);
                break;
        }
    }


}
