package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.SearchGoodsTypeBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.SearchGoodsAdapter;
import com.wholesale.yzx.yxzwholesale.view.adapter.SearchGoodsTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SearchFragment extends BaseFragment {

    @InjectView(R.id.iv_share)
    ImageView ivShare;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.type_list)
    RecyclerView typeList;
    @InjectView(R.id.goods_list)
    RecyclerView goodsList;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout  refresh;


    private SearchGoodsTypeAdapter typeAdapter;
    private List<SearchGoodsTypeBean.GoodsTypeListBean> typeDatas = new ArrayList<>();
    private SearchGoodsAdapter goodsAdapter;
    private List<MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean>> goodsDatas = new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.search_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();


        typeAdapter = new SearchGoodsTypeAdapter(getActivity(), R.layout.item_search_good_type, typeDatas);
        typeList.setAdapter(typeAdapter);
        typeList.setLayoutManager(new LinearLayoutManager(getActivity()));

        goodsAdapter = new SearchGoodsAdapter(getActivity(), goodsDatas);
        goodsList.setAdapter(goodsAdapter);
        final GridLayoutManager layoutManager=new GridLayoutManager(getActivity(), 3);

        goodsList.setLayoutManager(layoutManager);

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
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "goods_type_list.json";
        String json = JsonUtil.getJson(getActivity(), fileName);
        Log.i("shuju", json);
        SearchGoodsTypeBean bean = new Gson().fromJson(json, SearchGoodsTypeBean.class);
        if (bean != null) {
            for (int i = 0; i < bean.getGoodsTypeList().size(); i++) {
                bean.getGoodsTypeList().get(0).setSelect(true);
                typeDatas.add(bean.getGoodsTypeList().get(i));

                MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean> beanMultiItemViewTitle=new MultiItemView<>(MultiItemView.TITLE);
                SearchGoodsTypeBean.GoodsTypeListBean goodsListBean=new SearchGoodsTypeBean.GoodsTypeListBean();
                goodsListBean.setTypeId(bean.getGoodsTypeList().get(i).getTypeId());
                goodsListBean.setGoodsType(bean.getGoodsTypeList().get(i).getGoodsType());
                goodsListBean.setGoodsList(bean.getGoodsTypeList().get(i).getGoodsList());
                beanMultiItemViewTitle.setBean(goodsListBean);
                goodsDatas.add(beanMultiItemViewTitle);
            }
        }
        typeAdapter.notifyDataSetChanged();
        goodsAdapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }


}
