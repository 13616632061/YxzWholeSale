package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.GoodsStorePellingOrderBean;

import org.raphets.roundimageview.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/7/30.
 */

public class GoodsStorePellingOrderAdapter extends BaseQuickAdapter<GoodsStorePellingOrderBean.PellingInfoListBean,BaseViewHolder> {

    private Context context;
    public GoodsStorePellingOrderAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<GoodsStorePellingOrderBean.PellingInfoListBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsStorePellingOrderBean.PellingInfoListBean item) {
        RoundImageView iv_photo=helper.getView(R.id.iv_photo);
        Glide.with(context).load(item.getPhoto()).into(iv_photo);
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_time,item.getTime());
        RoundImageView iv_goods_image=helper.getView(R.id.iv_goods_image);
        Glide.with(context).load(item.getGoodsImage()).into(iv_goods_image);
        helper.setText(R.id.tv_price,"￥"+item.getPrice());

    }
}
