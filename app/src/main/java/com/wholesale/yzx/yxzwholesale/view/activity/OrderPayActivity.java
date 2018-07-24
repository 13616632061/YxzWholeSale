package com.wholesale.yzx.yxzwholesale.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.OrderPayGoodsBean;
import com.wholesale.yzx.yxzwholesale.view.adapter.OrderPayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class OrderPayActivity extends BaseActivity {


    @InjectView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.tv_pay)
    TextView tvPay;
    @InjectView(R.id.tv_pay_money)
    TextView tvPayMoney;

    private OrderPayAdapter adapter;
    private List<MultiItemView<OrderPayGoodsBean.GoodsShopInfoBean>> datas=new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void init() {
        super.init();

        View headerView=View.inflate(this,R.layout.order_pay_headview,null);


        adapter=new OrderPayAdapter(OrderPayActivity.this,datas);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter.addHeaderView(headerView);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                initData();
            }
        });

        initData();

    }


    @OnClick({R.id.iv_title_text_left2})
    public void setOnClick(View view){
      switch (view.getId()){
          case R.id.iv_title_text_left2:
              finish();
              break;
      }
  }
    private void initData() {
        MultiItemView<OrderPayGoodsBean.GoodsShopInfoBean> beanMultiItemViewTitle=new MultiItemView<>(MultiItemView.TITLE);
        OrderPayGoodsBean.GoodsShopInfoBean beanTitle= new OrderPayGoodsBean.GoodsShopInfoBean();
        beanTitle.setShop_logo("http://img1.imgtn.bdimg.com/it/u=2660245143,3683401826&fm=27&gp=0.jpg");
        beanMultiItemViewTitle.setBean(beanTitle);
        datas.add(beanMultiItemViewTitle);

        OrderPayGoodsBean.GoodsShopInfoBean.GoodsBean bean= new OrderPayGoodsBean.GoodsShopInfoBean.GoodsBean();
        bean.setGoodImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3032392112,2704801904&fm=27&gp=0.jpg");
        List<OrderPayGoodsBean.GoodsShopInfoBean.GoodsBean> bean_datas=new ArrayList<>();
        bean_datas.add(bean);

        MultiItemView<OrderPayGoodsBean.GoodsShopInfoBean> beanMultiItemViewBODY=new MultiItemView<>(MultiItemView.BODY);
        OrderPayGoodsBean.GoodsShopInfoBean beanBODY= new OrderPayGoodsBean.GoodsShopInfoBean();
        beanBODY.setGoodsBeanList(bean_datas);
        beanMultiItemViewBODY.setBean(beanBODY);
        datas.add(beanMultiItemViewBODY);

        adapter.notifyDataSetChanged();
        refresh.finishRefresh();


    }
}
