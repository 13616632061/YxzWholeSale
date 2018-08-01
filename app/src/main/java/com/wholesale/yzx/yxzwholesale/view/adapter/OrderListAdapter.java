package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.OrderListInfoBean;

import org.raphets.roundimageview.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class OrderListAdapter extends BaseMultiItemQuickAdapter<MultiItemView<OrderListInfoBean.OrderListBean>,BaseViewHolder> {

    private Context context;
    public OrderListAdapter(Context context,List<MultiItemView<OrderListInfoBean.OrderListBean>> data) {
        super(data);
        this.context=context;
        addItemType(MultiItemView.TITLE, R.layout.item_order_list_title);
        addItemType(MultiItemView.BODY, R.layout.item_order_list_body);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemView<OrderListInfoBean.OrderListBean> item) {
        switch (item.getItemType()){
            case MultiItemView.TITLE:
                RoundImageView iv_shop_logo=helper.getView(R.id.iv_shop_logo);
                Glide.with(context).load(item.getBean().getShopLogo()).into(iv_shop_logo);
                helper.setText(R.id.tv_shop_name,item.getBean().getShopName());

                switch (item.getBean().getOrderStadue()){
                    case "0":
                        helper.setText(R.id.tv_order_status,"交易成功");
                        break;
                    case "1":
                        helper.setText(R.id.tv_order_status,"待付款");
                        break;
                    case "2":
                        helper.setText(R.id.tv_order_status,"待评价");
                        break;
                    case "3":
                        helper.setText(R.id.tv_order_status,"交易取消");
                        break;

                }
                break;
            case MultiItemView.BODY:
                RoundImageView iv_goods_image=helper.getView(R.id.iv_goods_image);
                if(item.getBean()!=null&&item.getBean().getOrderDetail()!=null)
                Glide.with(context).load(item.getBean().getOrderDetail().get(0).getGoodPicture()).into(iv_goods_image);
                helper.setText(R.id.tv_goods_name,item.getBean().getOrderDetail().get(0).getGoodName());
                helper.setText(R.id.tv_goods_describe,item.getBean().getOrderDetail().get(0).getGoodDescribe());
                helper.setText(R.id.tv_goods_price,item.getBean().getOrderDetail().get(0).getGoodPrice());
                helper.setText(R.id.tv_goods_nums,item.getBean().getOrderDetail().get(0).getGoodNum()+"");

                break;
        }

    }
}
