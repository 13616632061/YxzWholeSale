package com.wholesale.yzx.yxzwholesale.view.activity;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

import org.raphets.roundimageview.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class PellDetailActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private RoundImageView iv_pell_first,iv_pell_second;
    private TextView tv_go_index;
    private GoodsListFragmentAdapter adapter;
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据

    private String OrderDetail_GoodPrice0,OrderDetail_GoodPrice1;

    @Override
    protected int getContentId() {
        return R.layout.activity_pell_detail;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("拼单成功",true,"",R.drawable.share);
        OrderDetail_GoodPrice0=getIntent().getStringExtra("OrderDetail_GoodPrice0");
        OrderDetail_GoodPrice1=getIntent().getStringExtra("OrderDetail_GoodPrice1");

        View headterView=View.inflate(this,R.layout.pell_detail_headter_view,null);
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
        iv_pell_first=headterView.findViewById(R.id.iv_pell_first);
        iv_pell_second=headterView.findViewById(R.id.iv_pell_second);
        tv_go_index=headterView.findViewById(R.id.tv_go_index);

        Glide.with(this).load(OrderDetail_GoodPrice0).into(iv_pell_first);
        Glide.with(this).load(OrderDetail_GoodPrice1).into(iv_pell_second);
        tv_go_index.setOnClickListener(this);
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
            case R.id.tv_go_index:
                Intent intent_index=new Intent(PellDetailActivity.this,MainActivity.class);
                startActivity(intent_index);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(PellDetailActivity.this, GoodsDetailActivity.class);
        startActivity(intent);
    }
}
