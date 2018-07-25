package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.ChatMessageBean;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.view.activity.GoodsDetailActivity;
import com.wholesale.yzx.yxzwholesale.view.adapter.ChatMessageAdapter;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class ChatFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private RecyclerView goods_list;

    private ChatMessageAdapter chatMessageAdapter;
    private List<ChatMessageBean.ChatMessageListBean> chatDatas = new ArrayList<>();
    private GoodsListFragmentAdapter goodsListAdapter;
    private List<GoodsListBean.ListFreshTypeBean> goodDatas=new ArrayList<>();//商品数据


    @Override
    protected int getContentId() {
        return R.layout.chat_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();

        View footview=View.inflate(getContext(),R.layout.chat_message_foot_view,null);
        goods_list=footview.findViewById(R.id.goods_list);

        goodsListAdapter=new GoodsListFragmentAdapter(getActivity(),R.layout.item_goods_list,goodDatas);
        goods_list.setAdapter(goodsListAdapter);
        goodsListAdapter.setOnItemClickListener(this);

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        goods_list.setLayoutManager(layoutManager);

        chatMessageAdapter = new ChatMessageAdapter(getActivity(), R.layout.item_chat_message, chatDatas);
        list.setAdapter(chatMessageAdapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatMessageAdapter.addFooterView(footview);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                chatDatas.clear();
                goodDatas.clear();
                getGoodslistData();
                getChatMessageData();

            }
        });

        chatMessageAdapter.setOnItemChildClickListener(this);
        getGoodslistData();
        getChatMessageData();
    }

    /**
     * 消息列表数据
     */
    private void getChatMessageData() {
        //得到本地json文本内容
        String fileName = "chat_message.json";
        String json = JsonUtil.getJson(getActivity(), fileName);
        ChatMessageBean bean = new Gson().fromJson(json, ChatMessageBean.class);

        for (ChatMessageBean.ChatMessageListBean bean1 : bean.getChatMessageList()) {
            chatDatas.add(bean1);
        }
        chatMessageAdapter.notifyDataSetChanged();
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
                goodDatas.add(bean1);
            }
        }
        goodsListAdapter.notifyDataSetChanged();
        refresh.finishRefresh();

    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        chatDatas.remove(position);
        chatMessageAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(getActivity(),GoodsDetailActivity.class);
        startActivity(intent);
    }
}
