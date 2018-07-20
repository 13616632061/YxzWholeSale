package com.wholesale.yzx.yxzwholesale.view.activity;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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

import static android.R.attr.type;

public class GoodsDetailActivity extends BaseActivity {


    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.title_layout)
    View title_layout;


    private GoodsListFragmentAdapter adapter;//商品列表适配器
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据

    @Override
    protected int getContentId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void init() {
        super.init();

        View headerView=View.inflate(GoodsDetailActivity.this,R.layout.goods_detail_headview,null);

        adapter = new GoodsListFragmentAdapter(GoodsDetailActivity.this, R.layout.item_goods_list, datas);
        list.setAdapter(adapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(GoodsDetailActivity.this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? layoutManager.getSpanCount() : 1;
            }
        });
        list.setLayoutManager(layoutManager);
        adapter.addHeaderView(headerView);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                getGoodslistData();
            }
        });
    }
    /**
     * 商品列表数据
     */
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "goodslist.json";
        String json = JsonUtil.getJson(GoodsDetailActivity.this, fileName);
        GoodsListBean bean = new Gson().fromJson(json, GoodsListBean.class);
        if (bean != null) {
            for (GoodsListBean.ListFreshTypeBean bean1 : bean.getListFreshType()) {
                if(type==1){
                    bean1.setShowPellNum(true);
                    bean1.setShowPellBtn(true);
                }else {
                    bean1.setShowPellNum(false);
                    bean1.setShowPellBtn(false);
                }
                datas.add(bean1);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }

}
