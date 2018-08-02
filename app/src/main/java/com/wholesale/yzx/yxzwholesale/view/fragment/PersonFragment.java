package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.activity.GoodsDetailActivity;
import com.wholesale.yzx.yxzwholesale.view.activity.MyOrderListAcitivty;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.widght.CircleImageView;
import com.wholesale.yzx.yxzwholesale.view.widght.RecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class PersonFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.tv_title_bar)
    TextView tvTitleBar;


    private TextView tv_wait_pay, tv_wait_share, tv_wait_send, tv_wait_get, tv_wait_comment,tv_all_order;

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

        initHeadterView(headView);
        CircleImageView iv_person_photo = headView.findViewById(R.id.iv_person_photo);
        String URL = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3612952217,3851762239&fm=27&gp=0.jpg";
        Glide.with(getActivity()).load(URL).into(iv_person_photo);


        goodsListAdapter = new GoodsListFragmentAdapter(getActivity(), R.layout.item_goods_list, goodDatas);
        list.setAdapter(goodsListAdapter);
        goodsListAdapter.setOnItemClickListener(this);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? layoutManager.getSpanCount() : 1;
            }
        });
        list.setLayoutManager(layoutManager);
        goodsListAdapter.addHeaderView(headView);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                goodDatas.clear();
                getGoodslistData();
            }
        });
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

    private void initHeadterView(View headView) {
        tv_wait_pay=headView.findViewById(R.id.tv_wait_pay);
        tv_wait_share=headView.findViewById(R.id.tv_wait_share);
        tv_wait_send=headView.findViewById(R.id.tv_wait_send);
        tv_wait_get=headView.findViewById(R.id.tv_wait_get);
        tv_wait_comment=headView.findViewById(R.id.tv_wait_comment);
        tv_all_order=headView.findViewById(R.id.tv_all_order);

        tv_wait_pay.setOnClickListener(this);
        tv_wait_share.setOnClickListener(this);
        tv_wait_send.setOnClickListener(this);
        tv_wait_get.setOnClickListener(this);
        tv_wait_comment.setOnClickListener(this);
        tv_all_order.setOnClickListener(this);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_wait_pay:
                Intent intent = new Intent(getActivity(), MyOrderListAcitivty.class);
                intent.putExtra("index", 1);
                startActivity(intent);
                break;
            case R.id.tv_wait_share:
                Intent intent2 = new Intent(getActivity(), MyOrderListAcitivty.class);
                intent2.putExtra("index", 2);
                startActivity(intent2);
                break;
            case R.id.tv_wait_send:
                Intent intent3 = new Intent(getActivity(), MyOrderListAcitivty.class);
                intent3.putExtra("index", 3);
                startActivity(intent3);
                break;
            case R.id.tv_wait_get:
                Intent intent4 = new Intent(getActivity(), MyOrderListAcitivty.class);
                intent4.putExtra("index", 4);
                startActivity(intent4);
                break;
            case R.id.tv_wait_comment:
                Intent intent5 = new Intent(getActivity(), MyOrderListAcitivty.class);
                intent5.putExtra("index", 5);
                startActivity(intent5);
                break;
            case R.id.tv_all_order:
                Intent intent0 = new Intent(getActivity(), MyOrderListAcitivty.class);
                intent0.putExtra("index", 0);
                startActivity(intent0);
                break;
        }
    }
}
