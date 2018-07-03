package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.ImageUtil;

import java.util.List;

/**
 * Created by lyf on 2018/7/1.
 */
public class GoodsListFragmentAdapter extends BaseQuickAdapter<GoodsListBean.ListFreshTypeBean,BaseViewHolder>{

    private Context context;

    public GoodsListFragmentAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<GoodsListBean.ListFreshTypeBean> data) {
        super(layoutResId, data);
        this.context=context;
    }


    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean.ListFreshTypeBean item) {
        ImageView iv_goods_url=helper.getView(R.id.iv_goods_url);
        Glide.with(context).load(item.getGoodsListImg()+ ImageUtil.ossSetImageSize(1000)).into(iv_goods_url);

        helper.setText(R.id.tv_goods_name,item.getGoodsName());
        helper.setText(R.id.tv_goods_price,"￥"+item.getPrice()+"");
        helper.setText(R.id.tv_goods_spell_nums,"已拼"+item.getDiscount()+"件");


    }
}