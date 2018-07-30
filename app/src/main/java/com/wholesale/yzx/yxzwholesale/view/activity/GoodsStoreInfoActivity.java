package com.wholesale.yzx.yxzwholesale.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import com.wholesale.yzx.yxzwholesale.bean.GoodsStorePellingOrderBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsStorePellingOrderAdapter;

import org.raphets.roundimageview.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsStoreInfoActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.iv_share)
    ImageView ivShare;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private RoundImageView iv_store_logo,iv_cpupon;
    private TextView tv_look_all;
    private RecyclerView list_pelling;

    private GoodsListFragmentAdapter adapter;//商品列表适配器
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据

    private GoodsStorePellingOrderAdapter mGoodsStorePellingOrderAdapter;
    private List<GoodsStorePellingOrderBean.PellingInfoListBean> pellingDatas=new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.activity_goods_store_info;
    }

    @Override
    protected void init() {
        super.init();

        View headerView=View.inflate(this,R.layout.goods_store_info_headerview,null);
        initHeaderView(headerView);

        adapter = new GoodsListFragmentAdapter(this, R.layout.item_goods_list, datas);
        list.setAdapter(adapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? layoutManager.getSpanCount() : 1;
            }
        });
        list.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(this);
        adapter.addHeaderView(headerView);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                getGoodslistData();
            }
        });
        getGoodslistData();
    }

    private void initHeaderView(View headerView) {
        String coupon="http://img4.imgtn.bdimg.com/it/u=2572795582,3667236826&fm=27&gp=0.jpg";
        String logo = "http://img2.imgtn.bdimg.com/it/u=2971196485,2046477827&fm=27&gp=0.jpg";
        iv_store_logo=headerView.findViewById(R.id.iv_store_logo);
        iv_cpupon=headerView.findViewById(R.id.iv_cpupon);
        tv_look_all=headerView.findViewById(R.id.tv_look_all);
        list_pelling=headerView.findViewById(R.id.list_pelling);

        Glide.with(this).load(logo).into(iv_store_logo);
        Glide.with(this).load(coupon).into(iv_cpupon);
         mGoodsStorePellingOrderAdapter=new GoodsStorePellingOrderAdapter(this,R.layout.item_goods_store_pelling_info,pellingDatas);
        list_pelling.setAdapter(mGoodsStorePellingOrderAdapter);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        list_pelling.setLayoutManager(manager);

        getGoodsPellingData();
    }

    /**
     * 商品列表数据
     */
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "goodslist.json";
        String json = JsonUtil.getJson(this, fileName);
        GoodsListBean bean = new Gson().fromJson(json, GoodsListBean.class);
        if (bean != null) {
            for (GoodsListBean.ListFreshTypeBean bean1 : bean.getListFreshType()) {
                bean1.setShowPellNum(false);
                bean1.setShowPellBtn(false);
                datas.add(bean1);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }
    /**
     * 商品拼单数据
     */
    private void getGoodsPellingData() {
        //得到本地json文本内容
        String fileName = "goods_store_headview_pellinginfo.json";
        String json = JsonUtil.getJson(this, fileName);
        GoodsStorePellingOrderBean bean = new Gson().fromJson(json, GoodsStorePellingOrderBean.class);
        if (bean != null) {
            for (GoodsStorePellingOrderBean.PellingInfoListBean bean1 : bean.getPellingInfoList()) {
                pellingDatas.add(bean1);
            }
        }
        mGoodsStorePellingOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(GoodsStoreInfoActivity.this,GoodsDetailActivity.class);
        startActivity(intent);
    }
    @OnClick({R.id.left_return_btn})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.left_return_btn:
                finish();
                break;
        }
    }
}
