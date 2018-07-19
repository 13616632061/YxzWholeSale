package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.bean.SecondKillBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.adapter.SecondTypeGoodsKillGoodsAdapter;
import com.wholesale.yzx.yxzwholesale.view.adapter.SecondTypeGoodsKillTimeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/7/19.
 */

public class SecondTypeGoodsFragment extends BaseFragment {
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    RecyclerView time_list,goods_list;

    private GoodsListFragmentAdapter adapter;//商品列表适配器
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据

    private List<SecondKillBean.SecondKillGoodsListBean> timeDatas=new ArrayList<>();
    private SecondTypeGoodsKillTimeAdapter timeAdapter;

    private List<SecondKillBean.SecondKillGoodsListBean.GoodsListBean> goodDatas=new ArrayList<>();
    private SecondTypeGoodsKillGoodsAdapter goodsAdapter;

    @Override
    protected int getContentId() {
        return R.layout.second_type_goods_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();

        View headview=View.inflate(getActivity(),R.layout.second_type_goods_headerview,null);
        time_list=headview.findViewById(R.id.time_list);
        goods_list=headview.findViewById(R.id.goods_list);

        timeAdapter=new SecondTypeGoodsKillTimeAdapter(R.layout.item_second_type_time,timeDatas);
        time_list.setAdapter(timeAdapter);
        time_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        goodsAdapter=new SecondTypeGoodsKillGoodsAdapter(getActivity(),R.layout.item_second_kill_goods,goodDatas);
        goods_list.setAdapter(goodsAdapter);
        goods_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));




        adapter = new GoodsListFragmentAdapter(getActivity(), R.layout.item_goods_list, datas);
        list.setAdapter(adapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? layoutManager.getSpanCount() : 1;
            }
        });
        list.setLayoutManager(layoutManager);
        adapter.addHeaderView(headview);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                getGoodslistData();

                timeDatas.clear();
                goodDatas.clear();
                getsecondKillData();
            }
        });

        getGoodslistData();
        getsecondKillData();
    }

    /**
     * 每日秒杀数据
     */

    private void getsecondKillData() {
        //得到本地json文本内容
        String fileName = "second_kill.json";
        String json = JsonUtil.getJson(getActivity(), fileName);
        Log.i("shuju", json);
        SecondKillBean bean = new Gson().fromJson(json, SecondKillBean.class);
        if (bean != null) {
           for (SecondKillBean.SecondKillGoodsListBean bean1:bean.getSecondKillGoodsList()){
               timeDatas.add(bean1);
               timeDatas.get(0).setSeclect(true);

               for (SecondKillBean.SecondKillGoodsListBean.GoodsListBean bean2:bean1.getGoodsList()){
                   goodDatas.add(bean2);
               }
           }
        }
        timeAdapter.notifyDataSetChanged();
        goodsAdapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }

    /**
     * 商品列表数据
     */
    private void  getGoodslistData() {
        //得到本地json文本内容
    String fileName = "goodslist.json";
    String json = JsonUtil.getJson(getActivity(), fileName);
    Log.i("shuju", json);
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
}
