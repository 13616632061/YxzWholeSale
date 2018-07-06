package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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

public class SearchFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

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
    private  boolean isScroll = false;//商品列表是否滚动

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
        goodsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                goodsDatas.clear();
                typeDatas.clear();
                getGoodslistData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
            }
        });
        typeAdapter.setOnItemClickListener(this);
        goodsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScroll=true;
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isScroll){
                    //第一个可见的位置
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int FirstItemPosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();


                    Log.i("FirstItemPosition",FirstItemPosition+"");
                    Log.i("lastVisiblePosition ",lastVisiblePosition +"");
                        for(int i=0;i<typeDatas.size();i++){
                            typeDatas.get(i).setSelect(false);
                        }
                    if ( lastVisiblePosition-FirstItemPosition<=2) {
                        typeDatas.get(FirstItemPosition/2).setSelect(true);
                    }
                    typeAdapter.notifyDataSetChanged();
                }
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
                beanMultiItemViewTitle.setBean(goodsListBean);
                goodsDatas.add(beanMultiItemViewTitle);

                MultiItemView<SearchGoodsTypeBean.GoodsTypeListBean> beanMultiItemViewBody=new MultiItemView<>(MultiItemView.BODY);
                SearchGoodsTypeBean.GoodsTypeListBean goodsListBeanBody=new SearchGoodsTypeBean.GoodsTypeListBean();
                goodsListBeanBody.setGoodsList(bean.getGoodsTypeList().get(i).getGoodsList());
                beanMultiItemViewBody.setBean(goodsListBeanBody);
                goodsDatas.add(beanMultiItemViewBody);
            }
        }
        typeAdapter.notifyDataSetChanged();
        goodsAdapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }

    /**
     * 分类点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        for(int i=0;i<typeDatas.size();i++){
            typeDatas.get(i).setSelect(false);
        }
        typeDatas.get(position).setSelect(true);
        typeAdapter.notifyDataSetChanged();

        if (position == 0) {
            goodsList.smoothScrollToPosition(0);
        }else {
            goodsList.smoothScrollToPosition(2*position+1);
        }
    }


}
