package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class SearchGoodsAdapter extends BaseMultiItemQuickAdapter<MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean>,BaseViewHolder> {

    private Context context;

    public SearchGoodsAdapter(Context context,List<MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean>> data) {
        super(data);
        this.context=context;
        addItemType(MultiItemView.TITLE, R.layout.search_goods_head_view);
        addItemType(MultiItemView.BODY, R.layout.item_search_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean> item) {
        switch (item.getItemType()){
            case MultiItemView.TITLE:
                helper.setText(R.id.tv_goods_type_name,item.getBean().getGoodsType());
                break;
            case MultiItemView.BODY:
                RecyclerView list=helper.getView(R.id.list);
                SearchGoodsItemAdapter adapter=new SearchGoodsItemAdapter(context,R.layout.item_search_goods,item.getBean().getGoodsList());
                list.setAdapter(adapter);
                list.setLayoutManager(new GridLayoutManager(context,3));
                break;
        }
    }
}
