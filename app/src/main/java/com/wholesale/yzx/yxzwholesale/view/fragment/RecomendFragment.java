package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class RecomendFragment extends BaseFragment {


    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private List<GoodsListBean.ListFreshTypeBean> datas=new ArrayList<>();//商品数据
    private GoodsListFragmentAdapter adapter;//商品列表适配器
    private View headerView;


    @Override
    protected int getContentId() {
        return R.layout.recomend_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();


        headerView=View.inflate(getActivity(), R.layout.recomendfragment_headview, null);

        adapter=new GoodsListFragmentAdapter(getActivity(),R.layout.item_goods_list,datas);
        list.setAdapter(adapter);

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? layoutManager.getSpanCount() : 1;
            }
        });
        list.setLayoutManager(layoutManager);
        adapter.addHeaderView(headerView);

        //拼单头像显示情况
        if(layoutManager.getSpanCount()>1){
            for(int i=0;i<datas.size();i++){
                datas.get(i).setShowPellNum(false);
                datas.get(i).setShowPellBtn(false);
            }
            adapter.notifyDataSetChanged();
        }else {
            for(int i=0;i<datas.size();i++){
                datas.get(i).setShowPellNum(true);
                datas.get(i).setShowPellBtn(true);
            }
            adapter.notifyDataSetChanged();
        }

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getGoodslistData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
            }
        });

        getGoodslistData();
    }

    /**
     * 商品列表数据
     */
    private void getGoodslistData(){
        //得到本地json文本内容
        String fileName = "goodslist.json";
        String json = JsonUtil.getJson(getActivity(), fileName);
        Log.i("shuju",json);
        GoodsListBean bean=new Gson().fromJson(json,GoodsListBean.class);
        if(bean!=null){
            for (GoodsListBean.ListFreshTypeBean bean1:bean.getListFreshType()){
                bean1.setShowPellBtn(false);
                bean1.setShowPellNum(true);
                datas.add(bean1);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }
}
