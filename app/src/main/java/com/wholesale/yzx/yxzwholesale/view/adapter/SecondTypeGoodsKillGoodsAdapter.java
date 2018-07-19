package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.SecondKillBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class SecondTypeGoodsKillGoodsAdapter extends BaseQuickAdapter<SecondKillBean.SecondKillGoodsListBean.GoodsListBean,BaseViewHolder> {

    private Context context;

    public SecondTypeGoodsKillGoodsAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<SecondKillBean.SecondKillGoodsListBean.GoodsListBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondKillBean.SecondKillGoodsListBean.GoodsListBean item) {
        ImageView iv_goods_image=helper.getView(R.id.iv_goods_image);
        Glide.with(context).load(item.getGoodsUrl()).into(iv_goods_image);
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_price,"￥"+item.getPeice());
        helper.setText(R.id.tv_stock,"库存剩"+item.getStock()+"件");


    }
}
