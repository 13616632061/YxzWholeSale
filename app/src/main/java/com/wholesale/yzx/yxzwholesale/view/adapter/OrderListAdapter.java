package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.OrderListInfoBean;
import com.wholesale.yzx.yxzwholesale.view.widght.PileAvertView;

import org.raphets.roundimageview.RoundImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class OrderListAdapter extends BaseMultiItemQuickAdapter<MultiItemView<OrderListInfoBean.OrderListBean>,BaseViewHolder> {

    private Context context;
    public OrderListAdapter(Context context, List<MultiItemView<OrderListInfoBean.OrderListBean>> data) {
        super(data);
        this.context=context;
        addItemType(MultiItemView.TITLE, R.layout.item_order_list_title);
        addItemType(MultiItemView.BODY, R.layout.item_order_list_body);
        addItemType(MultiItemView.FOOTER, R.layout.item_order_list_footer);
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
                    case "5":
                        helper.setText(R.id.tv_order_status,"待评价");
                        break;
                    case "3":
                        helper.setText(R.id.tv_order_status,"交易取消");
                        break;

                }
                break;
            case MultiItemView.BODY:
                RoundImageView iv_goods_image=helper.getView(R.id.iv_goods_image);
                if(item.getBean()!=null&&item.getBean().getOrderDetail()!=null){
                    Glide.with(context).load(item.getBean().getOrderDetail().get(0).getGoodPicture()).into(iv_goods_image);
                    helper.setText(R.id.tv_goods_name,item.getBean().getOrderDetail().get(0).getGoodName());
                    helper.setText(R.id.tv_goods_describe,item.getBean().getOrderDetail().get(0).getGoodDescribe());
                    helper.setText(R.id.tv_goods_price,item.getBean().getOrderDetail().get(0).getGoodPrice());
                    helper.setText(R.id.tv_goods_nums,item.getBean().getOrderDetail().get(0).getGoodNum()+"");

                }
                break;
            case MultiItemView.FOOTER:
                helper.setText(R.id.tv_order_price,"实付："+item.getBean().getOrderPrice()+"（免运费）");
                PileAvertView iv_pell_photo=helper.getView(R.id.iv_pell_photo);
                List<String> pellPhoto=new ArrayList<>();
                for(int i=0;i<item.getBean().getOrderPellInfo().size();i++){
                    pellPhoto.add(item.getBean().getOrderPellInfo().get(i).getPellPersonPhoto());
                }
                iv_pell_photo.setAvertImages(pellPhoto,2);

                TextView tv1=helper.getView(R.id.tv1);
                TextView tv2=helper.getView(R.id.tv2);


                switch (item.getBean().getOrderStadue()){
                   case "0":
                       tv1.setVisibility(View.VISIBLE);
                       tv1.setText("评价");
                       tv1.setTag("comment");
                       tv2.setVisibility(View.VISIBLE);
                       tv2.setText("删除订单");
                       tv2.setTag("delete");
                        break;
                    case "1":
                        tv1.setVisibility(View.VISIBLE);
                        tv1.setText("付款");
                        tv1.setTag("pay");
                        tv2.setVisibility(View.VISIBLE);
                        tv2.setText("取消订单");
                        tv2.setTag("cancel");
                        break;
                    case "5":
                        tv1.setVisibility(View.VISIBLE);
                        tv1.setText("去评价");
                        tv1.setTag("goComment");
                        tv2.setVisibility(View.VISIBLE);
                        tv2.setText("删除订单");
                        tv2.setTag("delete");
                        break;
                    case "3":
                        tv1.setVisibility(View.VISIBLE);
                        tv1.setText("再次购买");
                        tv2.setVisibility(View.VISIBLE);
                        tv2.setText("删除订单");
                        tv2.setTag("delete");
                        break;

                }
                break;
        }
        helper.addOnClickListener(R.id.tv1);
        helper.addOnClickListener(R.id.tv2);
    }

}
