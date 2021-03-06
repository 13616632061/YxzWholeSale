package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.OrderListInfoBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.activity.GoodsDetailActivity;
import com.wholesale.yzx.yxzwholesale.view.activity.OrderDetailActivity;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/7/30.
 */

public class OrderListFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;


    private OrderListAdapter adapter;
    private List<MultiItemView<OrderListInfoBean.OrderListBean>> datas = new ArrayList<>();
    private String orderType;
    private View emptyLayout, tv_headterView;

    private GoodsListFragmentAdapter goodsListAdapter;
    private List<GoodsListBean.ListFreshTypeBean> goodDatas = new ArrayList<>();//商品数据
    private boolean isFirst = true;


    @Override
    protected int getContentId() {
        return R.layout.order_list_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();
        orderType = getArguments().getString("TypeId");

        emptyLayout = View.inflate(getActivity(), R.layout.emptylayout, null);
        tv_headterView = View.inflate(getActivity(), R.layout.tv_headter_view, null);


        adapter = new OrderListAdapter(getActivity(), datas);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                adapter.notifyDataSetChanged();
                getOrderData();
            }
        });
//
        if (isFirst) {
            datas.clear();
            adapter.notifyDataSetChanged();
            getOrderData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (adapter != null) {
                datas.clear();
                adapter.notifyDataSetChanged();
            }
            if (!isFirst && refresh != null) {
                getOrderData();
            }
        }
    }

    /**
     * 商品列表数据
     */
    private void getOrderData() {
        //得到本地json文本内容
        isFirst = false;
        String fileName = "orderlist.json";
        String json = JsonUtil.getJson(getActivity(), fileName);
        Log.i("shuju", json);
        OrderListInfoBean bean = new Gson().fromJson(json, OrderListInfoBean.class);
        if (bean != null) {
            for (OrderListInfoBean.OrderListBean bean1 : bean.getOrderList()) {
                if (orderType.equals("0") || bean1.getOrderStadue().equals(orderType)) {

                    MultiItemView<OrderListInfoBean.OrderListBean> MultiItemViewTitle = new MultiItemView<>(MultiItemView.TITLE);
                    OrderListInfoBean.OrderListBean orderListBeanTitle = new OrderListInfoBean.OrderListBean();
                    orderListBeanTitle.setShopLogo(bean1.getShopLogo());
                    orderListBeanTitle.setShopName(bean1.getShopName());
                    orderListBeanTitle.setOrderStadue(bean1.getOrderStadue());
                    MultiItemViewTitle.setBean(orderListBeanTitle);
                    datas.add(MultiItemViewTitle);


//                for(OrderListInfoBean.OrderListBean.OrderDetailBean bean2:bean1.getOrderDetail()){
                    MultiItemView<OrderListInfoBean.OrderListBean> MultiItemViewBody = new MultiItemView<>(MultiItemView.BODY);
                    OrderListInfoBean.OrderListBean orderListBeanBody = new OrderListInfoBean.OrderListBean();
                    orderListBeanBody.setOrderDetail(bean1.getOrderDetail());
                    MultiItemViewBody.setBean(orderListBeanBody);
                    datas.add(MultiItemViewBody);
//                }

                    MultiItemView<OrderListInfoBean.OrderListBean> MultiItemViewFooter = new MultiItemView<>(MultiItemView.FOOTER);
                    OrderListInfoBean.OrderListBean orderListBeanFooter = new OrderListInfoBean.OrderListBean();
                    orderListBeanFooter.setOrderPellInfo(bean1.getOrderPellInfo());
                    orderListBeanFooter.setOrderPrice(bean1.getOrderPrice());
                    orderListBeanFooter.setOrderStadue(bean1.getOrderStadue());
                    MultiItemViewFooter.setBean(orderListBeanFooter);
                    datas.add(MultiItemViewFooter);
                }

            }
        }

        Log.i("datas", datas.size() + "");
        if (datas.size() <= 0) {

            if (goodsListAdapter != null) {
                goodsListAdapter.removeAllHeaderView();
            }
            goodsListAdapter = new GoodsListFragmentAdapter(getActivity(), R.layout.item_goods_list, goodDatas);
            list.setAdapter(goodsListAdapter);

            final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (position == 0) ? layoutManager.getSpanCount() : 1;
                }
            });
            list.setLayoutManager(layoutManager);
            if (goodsListAdapter != null) {

                goodsListAdapter.setOnItemClickListener(this);

                goodsListAdapter.addHeaderView(emptyLayout);
                goodsListAdapter.addHeaderView(tv_headterView);
                goodDatas.clear();
                goodsListAdapter.notifyDataSetChanged();
                goodsListAdapter.setOnItemClickListener(this);
                getGoodslistData();
            }

        } else {
            adapter.notifyDataSetChanged();
            refresh.finishRefresh();
        }
    }

    /**
     * 订单列表子元素点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Object tag = view.getTag();
        Log.i("position", position + "");
        switch (view.getId()) {
            case R.id.tv1:

                break;
            case R.id.tv2:
                if (tag.equals("delete")) {
                    deleteDialog(position);
                }
                break;
        }

    }

    /**
     * 商品列表数据
     */
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "goodslist.json";
        String json = JsonUtil.getJson(getActivity(), fileName);
        Log.i("shuju", json);
        GoodsListBean bean = new Gson().fromJson(json, GoodsListBean.class);
        if (bean != null) {
            for (GoodsListBean.ListFreshTypeBean bean1 : bean.getListFreshType()) {
                bean1.setShowPellBtn(false);
                bean1.setShowPellNum(true);
                goodDatas.add(bean1);
            }
        }
        goodsListAdapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }

    /**
     * 删除订单dialog
     *
     * @param position
     */
    private void deleteDialog(final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("确定删除订单？")
                .setMessage("删除订单无法恢复，无法处理您的售后问题，请慎重考虑")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(position);
                        adapter.remove(position - 1);
                        adapter.remove(position - 2);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter mAdapter, View view, int position) {
        if (mAdapter == adapter) {
            switch (mAdapter.getItemViewType(position)) {
                case MultiItemView.BODY:
                    Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                    intent.putExtra("OrderPrice", datas.get(position + 1).getBean().getOrderPrice());
                    intent.putExtra("ShopName", datas.get(position - 1).getBean().getShopName());
                    intent.putExtra("ShopLogo", datas.get(position - 1).getBean().getShopLogo());
                    intent.putExtra("OrderStadue", datas.get(position - 1).getBean().getOrderStadue());
                    intent.putExtra("OrderDetail_GoodName", datas.get(position).getBean().getOrderDetail().get(0).getGoodName());
                    intent.putExtra("OrderDetail_Describe", datas.get(position).getBean().getOrderDetail().get(0).getGoodDescribe());
                    intent.putExtra("OrderDetail_GoodPicture", datas.get(position).getBean().getOrderDetail().get(0).getGoodPicture());
                    intent.putExtra("OrderDetail_GoodNum", datas.get(position).getBean().getOrderDetail().get(0).getGoodNum());
                    intent.putExtra("OrderDetail_GoodPrice", datas.get(position).getBean().getOrderDetail().get(0).getGoodPrice());
                    intent.putExtra("OrderDetail_GoodPrice0", datas.get(position + 1).getBean().getOrderPellInfo().get(0).getPellPersonPhoto());
                    intent.putExtra("OrderDetail_GoodPrice1", datas.get(position + 1).getBean().getOrderPellInfo().get(1).getPellPersonPhoto());
                    startActivity(intent);
                    break;

            }

        }else if(mAdapter==goodsListAdapter){
            Intent intent=new Intent(getActivity(), GoodsDetailActivity.class);
            startActivity(intent);
        }
    }

}
