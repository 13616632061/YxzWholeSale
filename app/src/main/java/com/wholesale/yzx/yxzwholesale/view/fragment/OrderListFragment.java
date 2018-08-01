package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.OrderListInfoBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/7/30.
 */

public class OrderListFragment extends BaseFragment {
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private OrderListAdapter adapter;
    private List<MultiItemView<OrderListInfoBean.OrderListBean>> datas=new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.order_list_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();
        adapter=new OrderListAdapter(getActivity(),datas);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                getGoodslistData();
            }
        });

        getGoodslistData();
    }

    /**
     * 商品列表数据
     */
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "orderlist.json";
        String json = JsonUtil.getJson(getActivity(), fileName);
        Log.i("shuju", json);
        OrderListInfoBean bean = new Gson().fromJson(json, OrderListInfoBean.class);
        if (bean != null) {
            for (OrderListInfoBean.OrderListBean bean1 : bean.getOrderList()) {
                MultiItemView<OrderListInfoBean.OrderListBean> MultiItemViewTitle=new MultiItemView<>(MultiItemView.TITLE);
                OrderListInfoBean.OrderListBean orderListBeanTitle=new OrderListInfoBean.OrderListBean();
                orderListBeanTitle.setShopLogo(bean1.getShopLogo());
                orderListBeanTitle.setShopName(bean1.getShopName());
                orderListBeanTitle.setOrderStadue(bean1.getOrderStadue());
                MultiItemViewTitle.setBean(orderListBeanTitle);
                datas.add(MultiItemViewTitle);


//                for(OrderListInfoBean.OrderListBean.OrderDetailBean bean2:bean1.getOrderDetail()){
                    MultiItemView<OrderListInfoBean.OrderListBean> MultiItemViewBody=new MultiItemView<>(MultiItemView.BODY);
                    OrderListInfoBean.OrderListBean orderListBeanBody=new OrderListInfoBean.OrderListBean();
                    orderListBeanBody.setOrderDetail(bean1.getOrderDetail());
                    MultiItemViewBody.setBean(orderListBeanBody);
                    datas.add(MultiItemViewBody);
//                }

                MultiItemView<OrderListInfoBean.OrderListBean> MultiItemViewFooter=new MultiItemView<>(MultiItemView.FOOTER);
                OrderListInfoBean.OrderListBean orderListBeanFooter=new OrderListInfoBean.OrderListBean();
                orderListBeanFooter.setOrderPellInfo(bean1.getOrderPellInfo());
                orderListBeanFooter.setOrderPrice(bean1.getOrderPrice());
                MultiItemViewFooter.setBean(orderListBeanFooter);
                datas.add(MultiItemViewFooter);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }
}
