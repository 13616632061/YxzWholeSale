package com.wholesale.yzx.yxzwholesale.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.widght.WarpLinearLayout;

import org.raphets.roundimageview.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private ImageView iv_status;
    private TextView tv_status,tv_pell_detail,tv_shop_name,tv_goods_name,tv_goods_describe,tv_goods_price,tv_goods_nums,
            tv_apply_refund,tv_order_price,tv_contact;
    private RelativeLayout layout_logistics,layout_apply_refund;
    private WarpLinearLayout iv_pell_person;
    private RoundImageView iv_shop_logo,iv_goods_image;
    private LinearLayout layout_pell_info;

    private String OrderPrice;
    private String ShopName;
    private String ShopLogo;
    private String OrderStadue;
    private String OrderDetail_GoodName;
    private String OrderDetail_Describe;
    private String OrderDetail_GoodPicture;
    private String OrderDetail_GoodNum;
    private String OrderDetail_GoodPrice;
    private String OrderDetail_GoodPrice0;
    private String OrderDetail_GoodPrice1;


    private GoodsListFragmentAdapter adapter;
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据

    @Override
    protected int getContentId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("订单详情",true,"",0);
        OrderPrice=getIntent().getStringExtra("OrderPrice");
        ShopName=getIntent().getStringExtra("ShopName");
        ShopLogo=getIntent().getStringExtra("ShopLogo");
        OrderStadue=getIntent().getStringExtra("OrderStadue");
        OrderDetail_GoodName=getIntent().getStringExtra("OrderDetail_GoodName");
        OrderDetail_Describe=getIntent().getStringExtra("OrderDetail_Describe");
        OrderDetail_GoodPicture=getIntent().getStringExtra("OrderDetail_GoodPicture");
        OrderDetail_GoodNum=getIntent().getStringExtra("OrderDetail_GoodNum");
        OrderDetail_GoodPrice=getIntent().getStringExtra("OrderDetail_GoodPrice");
        OrderDetail_GoodPrice0=getIntent().getStringExtra("OrderDetail_GoodPrice0");
        OrderDetail_GoodPrice1=getIntent().getStringExtra("OrderDetail_GoodPrice1");

        View headterView=View.inflate(this,R.layout.order_detail_headterview,null);
        initHeadterView(headterView);
        adapter= new GoodsListFragmentAdapter(this, R.layout.item_goods_list, datas);
        list.setAdapter(adapter);
        adapter.addHeaderView(headterView);
        adapter.setOnItemClickListener(this);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? layoutManager.getSpanCount() : 1;
            }
        });
        list.setLayoutManager(layoutManager);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                adapter.notifyDataSetChanged();
                getGoodslistData();
            }
        });

        leftReturnBtn.setOnClickListener(this);
        getGoodslistData();
    }

    private void initHeadterView(View headterView) {
        iv_status=headterView.findViewById(R.id.iv_status);
        tv_status=headterView.findViewById(R.id.tv_status);
        layout_logistics=headterView.findViewById(R.id.layout_logistics);
        tv_pell_detail=headterView.findViewById(R.id.tv_pell_detail);
        iv_pell_person=headterView.findViewById(R.id.iv_pell_person);
        iv_shop_logo=headterView.findViewById(R.id.iv_shop_logo);
        tv_shop_name=headterView.findViewById(R.id.tv_shop_name);
        iv_goods_image=headterView.findViewById(R.id.iv_goods_image);
        tv_goods_name=headterView.findViewById(R.id.tv_goods_name);
        tv_goods_describe=headterView.findViewById(R.id.tv_goods_describe);
        tv_goods_price=headterView.findViewById(R.id.tv_goods_price);
        tv_goods_nums=headterView.findViewById(R.id.tv_goods_nums);
        tv_apply_refund=headterView.findViewById(R.id.tv_apply_refund);
        tv_order_price=headterView.findViewById(R.id.tv_order_price);
        tv_contact=headterView.findViewById(R.id.tv_contact);
        layout_pell_info=headterView.findViewById(R.id.layout_pell_info);
        layout_apply_refund=headterView.findViewById(R.id.layout_apply_refund);

        switch (OrderStadue){
            case "0":
                tv_status.setText("交易成功");
                break;
            case "1":
                tv_status.setText("待付款");
                layout_logistics.setVisibility(View.GONE);
                layout_pell_info.setVisibility(View.GONE);
                layout_pell_info.setVisibility(View.GONE);
                layout_apply_refund.setVisibility(View.GONE);
                break;
            case "5":
                tv_status.setText("待评价");
                break;
            case "3":
                tv_status.setText("交易取消");
                layout_logistics.setVisibility(View.GONE);
                layout_pell_info.setVisibility(View.GONE);
                layout_pell_info.setVisibility(View.GONE);
                layout_apply_refund.setVisibility(View.GONE);
                break;

        }

        tv_shop_name.setText(ShopName);
        Glide.with(this).load(ShopLogo).into(iv_shop_logo);
        tv_order_price.setText(OrderPrice);
        Glide.with(this).load(OrderDetail_GoodPicture).into(iv_goods_image);
        tv_goods_name.setText(OrderDetail_GoodName);
        tv_goods_describe.setText(OrderDetail_Describe);
        tv_goods_price.setText(OrderDetail_GoodPrice);

        List<String> pell_person_list=new ArrayList<>();
        pell_person_list.add(OrderDetail_GoodPrice0);
        pell_person_list.add(OrderDetail_GoodPrice1);

        iv_pell_person.removeAllViews();
        for(int i=0;i<pell_person_list.size();i++){
            View view=View.inflate(this,R.layout.item_sigle_image,null);
            RoundImageView iv_single=view.findViewById(R.id.iv_single);
            Glide.with(this).load(pell_person_list.get(i)).into(iv_single);
            iv_pell_person.addView(view);
        }

        layout_logistics.setOnClickListener(this);
        tv_pell_detail.setOnClickListener(this);

    }

    /**
     * 商品列表数据
     */
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "goodslist.json";
        String json = JsonUtil.getJson(this, fileName);
        Log.i("shuju", json);
        GoodsListBean bean = new Gson().fromJson(json, GoodsListBean.class);
        if (bean != null) {
            for (GoodsListBean.ListFreshTypeBean bean1 : bean.getListFreshType()) {
                bean1.setShowPellBtn(false);
                bean1.setShowPellNum(true);
                datas.add(bean1);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_return_btn:
                finish();
                break;
            case R.id.layout_logistics:
                Intent intent_logistics=new Intent(OrderDetailActivity.this,LookLogisticsActivity.class);
                intent_logistics.putExtra("OrderDetail_GoodPicture",OrderDetail_GoodPicture);
                startActivity(intent_logistics);
                break;
            case R.id.tv_pell_detail:
                Intent intent_pell_detail=new Intent(OrderDetailActivity.this,PellDetailActivity.class);
                intent_pell_detail.putExtra("OrderDetail_GoodPrice0",OrderDetail_GoodPrice0);
                intent_pell_detail.putExtra("OrderDetail_GoodPrice1",OrderDetail_GoodPrice1);
                startActivity(intent_pell_detail);
                break;

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(OrderDetailActivity.this, GoodsDetailActivity.class);
        startActivity(intent);
    }
}
