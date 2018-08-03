package com.wholesale.yzx.yxzwholesale.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

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

public class OrderDetailActivity extends BaseActivity {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private GoodsListFragmentAdapter adapter;
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据

    @Override
    protected int getContentId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void init() {
        super.init();

        adapter= new GoodsListFragmentAdapter(this, R.layout.item_goods_list, datas);
        list.setAdapter(adapter);

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

        getGoodslistData();
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
}
