package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.widght.RecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class PersonFragment extends BaseFragment {

    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.tv_title_bar)
    TextView tvTitleBar;


    private GoodsListFragmentAdapter goodsListAdapter;
    private List<GoodsListBean.ListFreshTypeBean> goodDatas = new ArrayList<>();//商品数据

    @Override
    protected int getContentId() {
        return R.layout.person_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();

        View headView = View.inflate(getActivity(), R.layout.person_fragment_head_view, null);

        goodsListAdapter = new GoodsListFragmentAdapter(getActivity(), R.layout.item_goods_list, goodDatas);
        list.setAdapter(goodsListAdapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return (position == 0) ? layoutManager.getSpanCount() : 1;
//            }
//        });
        list.setLayoutManager(layoutManager);
//        goodsListAdapter.addHeaderView(headView);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                goodDatas.clear();
                getGoodslistData();
            }
        });
//        tvTitleBar.setAlpha(0);
        list.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public void hide() {
                tvTitleBar.setAlpha(0);
                tvTitleBar.setVisibility(View.GONE);
            }

            @Override
            public void show() {
                tvTitleBar.setAlpha(1);
                tvTitleBar.setVisibility(View.VISIBLE);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
