package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.OrderListInfoBean;

import org.raphets.roundimageview.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/8/7.
 */

public class ApplyRefundAdapter extends BaseQuickAdapter<OrderListInfoBean.OrderListBean.OrderDetailBean,BaseViewHolder> {

    private Context context;
    public ApplyRefundAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<OrderListInfoBean.OrderListBean.OrderDetailBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListInfoBean.OrderListBean.OrderDetailBean item) {
        RoundImageView iv_goods_image=helper.getView(R.id.iv_goods_image);
        Glide.with(context).load(item.getGoodPicture()).into(iv_goods_image);
        helper.setText(R.id.tv_goods_name,item.getGoodName());
        helper.setText(R.id.tv_goods_describe,item.getGoodDescribe());
        helper.setText(R.id.tv_goods_price,item.getGoodPrice());
        helper.setText(R.id.tv_goods_nums,"x "+item.getGoodNum());




    }
}
