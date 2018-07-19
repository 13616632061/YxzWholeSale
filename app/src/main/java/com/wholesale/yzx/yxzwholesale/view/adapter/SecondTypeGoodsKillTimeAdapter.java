package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.SecondKillBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class SecondTypeGoodsKillTimeAdapter extends BaseQuickAdapter<SecondKillBean.SecondKillGoodsListBean,BaseViewHolder> {

    public SecondTypeGoodsKillTimeAdapter(@LayoutRes int layoutResId, @Nullable List<SecondKillBean.SecondKillGoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondKillBean.SecondKillGoodsListBean item) {
        TextView tv_time=helper.getView(R.id.tv_time);
        tv_time.setText(item.getTime());
        if(item.isSeclect()){
            tv_time.setTextColor(Color.parseColor("#FF0000"));
        }else {
            tv_time.setTextColor(Color.parseColor("#787679"));
        }

    }
}
