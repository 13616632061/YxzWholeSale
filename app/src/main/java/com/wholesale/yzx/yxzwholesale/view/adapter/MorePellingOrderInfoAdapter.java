package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.MorePellingOrderInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class MorePellingOrderInfoAdapter extends BaseQuickAdapter<MorePellingOrderInfoBean.PellingOrderListBean,BaseViewHolder> {

    private Context context;
    public MorePellingOrderInfoAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<MorePellingOrderInfoBean.PellingOrderListBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MorePellingOrderInfoBean.PellingOrderListBean item) {
        ImageView iv_photo=helper.getView(R.id.iv_photo);
        Glide.with(context).load(item.getPhoto()).into(iv_photo);
        helper.setText(R.id.tv_name,item.getName());

    }
}
