package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.OrderPayGoodsBean;
import com.wholesale.yzx.yxzwholesale.view.widght.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class OrderPayAdapter extends BaseMultiItemQuickAdapter<MultiItemView<OrderPayGoodsBean.GoodsShopInfoBean>,BaseViewHolder> {

    private Context context;
    public OrderPayAdapter(Context context,List<MultiItemView<OrderPayGoodsBean.GoodsShopInfoBean>> data ){
        super(data);
        this.context=context;

        addItemType(MultiItemView.TITLE, R.layout.item_order_pay_title);
        addItemType(MultiItemView.BODY, R.layout.item_order_pay_body);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemView<OrderPayGoodsBean.GoodsShopInfoBean> item) {
        switch (item.getItemType()){
            case MultiItemView.TITLE:
                CircleImageView iv_shop_logo=helper.getView(R.id.iv_shop_logo);
                Glide.with(context).load(item.getBean().getShop_logo()).into(iv_shop_logo);
                break;
            case MultiItemView.BODY:
                ImageView iv_goods_picture=helper.getView(R.id.iv_goods_picture);
                Glide.with(context).load(item.getBean().getGoodsBeanList().get(0).getGoodImage()).into(iv_goods_picture);
                break;
        }
    }
}
