package com.wholesale.yzx.yxzwholesale.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class LookLogisticsActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private ImageView iv_goods_image;
    private String OrderDetail_GoodPicture;

    private GoodsListFragmentAdapter adapter;
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据

    @Override
    protected int getContentId() {
        return R.layout.activity_look_logistics;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("查看物流",true,"",0);
        OrderDetail_GoodPicture=getIntent().getStringExtra("OrderDetail_GoodPicture");


        View headterView=View.inflate(this,R.layout.look_logistics_headter_view,null);
        initHeadterView(headterView);
        adapter= new GoodsListFragmentAdapter(this, R.layout.item_goods_list, datas);
        list.setAdapter(adapter);
        adapter.addHeaderView(headterView);

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
        iv_goods_image=headterView.findViewById(R.id.iv_goods_image);
        Glide.with(this).load(OrderDetail_GoodPicture).into(iv_goods_image);
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
        }
    }
}
