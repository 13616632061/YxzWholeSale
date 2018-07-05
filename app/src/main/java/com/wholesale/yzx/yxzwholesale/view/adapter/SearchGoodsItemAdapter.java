package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.SearchGoodsTypeBean;

import java.util.List;

/**
 * Created by lyf on 2018/7/5.
 */
public class SearchGoodsItemAdapter extends BaseQuickAdapter<SearchGoodsTypeBean.GoodsTypeListBean.GoodsListBean,BaseViewHolder> {

    private Context context;
    public SearchGoodsItemAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<SearchGoodsTypeBean.GoodsTypeListBean.GoodsListBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchGoodsTypeBean.GoodsTypeListBean.GoodsListBean item) {
        ImageView iv_goods_image=helper.getView(R.id.iv_goods_image);
        Glide.with(context).load(item.getGoodsImageUrl()).into(iv_goods_image);
        helper.setText(R.id.tv_goods_name,item.getGoodsName());

    }
}