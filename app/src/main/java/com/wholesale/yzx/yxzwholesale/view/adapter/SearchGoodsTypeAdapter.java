package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.SearchGoodsTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/5.
 */

public class SearchGoodsTypeAdapter extends BaseQuickAdapter<SearchGoodsTypeBean.GoodsTypeListBean,BaseViewHolder> {

    private Context context;
    public SearchGoodsTypeAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<SearchGoodsTypeBean.GoodsTypeListBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchGoodsTypeBean.GoodsTypeListBean item) {
        RelativeLayout type_layout=helper.getView(R.id.type_layout);
        View view_red=helper.getView(R.id.view_red);
        TextView tv_type=helper.getView(R.id.tv_type);

        tv_type.setText(item.getGoodsType());
        if(item.isSelect()){
            type_layout.setBackgroundResource(R.color.white);
            view_red.setVisibility(View.VISIBLE);
            tv_type.setTextColor(context.getResources().getColor(R.color.red));
        }else {
            type_layout.setBackgroundResource(R.color.bggray);
            view_red.setVisibility(View.INVISIBLE);
            tv_type.setTextColor(context.getResources().getColor(R.color.text_color_title));
        }

    }
}
