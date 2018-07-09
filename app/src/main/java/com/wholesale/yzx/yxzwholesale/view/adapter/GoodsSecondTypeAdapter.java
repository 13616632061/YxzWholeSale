package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.GoodsSecondTypeBean;
import com.wholesale.yzx.yxzwholesale.view.widght.CircleImageView;

import java.util.List;

/**
 * Created by lyf on 2018/7/2.
 */
public class GoodsSecondTypeAdapter extends BaseQuickAdapter<GoodsSecondTypeBean.TypeListBean,BaseViewHolder> {

    private Context context;
    public GoodsSecondTypeAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<GoodsSecondTypeBean.TypeListBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSecondTypeBean.TypeListBean item) {
        CircleImageView iv_goods_url=helper.getView(R.id.iv_type_url);
        Glide.with(context).load(item.getTypePhoto()).into(iv_goods_url);

        helper.setText(R.id.tv_type_name,item.getTypeName());

    }
}