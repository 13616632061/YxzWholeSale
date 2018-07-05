package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.SearchGoodsTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/5.
 */

public class SearchGoodsAdapter extends BaseMultiItemQuickAdapter<MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean.GoodsListBean>,BaseViewHolder> {

    private Context context;

    public SearchGoodsAdapter(Context context,List<MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean.GoodsListBean>> data) {
        super(data);
        this.context=context;
        addItemType(MultiItemView.TITLE, R.layout.search_goods_head_view);
        addItemType(MultiItemView.BODY, R.layout.item_search_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean.GoodsListBean> item) {
        switch (item.getItemType()){
            case MultiItemView.TITLE:
                helper.setText(R.id.tv_goods_type_name,item.getBean().getGoodsType());
                break;
            case MultiItemView.BODY:
                ImageView iv_goods_image=helper.getView(R.id.iv_goods_image);
                Glide.with(context).load(item.getBean().getGoodsImageUrl()).into(iv_goods_image);
                helper.setText(R.id.tv_goods_name,item.getBean().getGoodsName());
                break;
        }
    }
}
