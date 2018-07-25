package com.wholesale.yzx.yxzwholesale.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.bean.OrderPayGoodsBean;
import com.wholesale.yzx.yxzwholesale.view.adapter.OrderPayAdapter;
import com.wholesale.yzx.yxzwholesale.view.widght.AmountView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class OrderPayActivity extends BaseActivity implements View.OnClickListener {


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

    private ImageView iv_selsect_weixin,iv_select_weixin_friend,iv_select_zhifubao,iv_select_qq;
    private RelativeLayout more_pay_layout,layout_select_zhifubao,layout_weixin,layout_select_weixin_friend,layout_select_qq;
    private AmountView amount_view;

    private OrderPayAdapter adapter;
    private List<MultiItemView<OrderPayGoodsBean.GoodsShopInfoBean>> datas=new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("订单支付",true,"",0);

        View headerView=View.inflate(this,R.layout.order_pay_headview,null);

        View footView=View.inflate(this,R.layout.order_pay_footview,null);
        initFootView(footView);

        adapter=new OrderPayAdapter(OrderPayActivity.this,datas);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter.addHeaderView(headerView);
        adapter.addFooterView(footView);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                initData();
            }
        });

        initData();

    }

    /**
     * 初始化
     * @param footView
     */
    private void initFootView(View footView) {
        iv_selsect_weixin=footView.findViewById(R.id.iv_selsect_weixin);
        iv_select_weixin_friend=footView.findViewById(R.id.iv_select_weixin_friend);
        more_pay_layout=footView.findViewById(R.id.more_pay_layout);
        layout_select_zhifubao=footView.findViewById(R.id.layout_select_zhifubao);
        layout_select_qq=footView.findViewById(R.id.layout_select_qq);
        layout_weixin=footView.findViewById(R.id.layout_weixin);
        layout_select_weixin_friend=footView.findViewById(R.id.layout_select_weixin_friend);
        iv_select_zhifubao=footView.findViewById(R.id.iv_select_zhifubao);
        iv_select_qq=footView.findViewById(R.id.iv_select_qq);
        amount_view=footView.findViewById(R.id.amount_view);
        amount_view.setAmount(1);

        layout_weixin.setOnClickListener(this);
        layout_select_weixin_friend.setOnClickListener(this);
        more_pay_layout.setOnClickListener(this);
        layout_select_zhifubao.setOnClickListener(this);
        layout_select_qq.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_weixin:
                iv_selsect_weixin.setVisibility(View.VISIBLE);
                iv_select_weixin_friend.setVisibility(View.GONE);
                iv_select_zhifubao.setVisibility(View.GONE);
                iv_select_qq.setVisibility(View.GONE);
                break;
            case R.id.layout_select_weixin_friend:
                iv_selsect_weixin.setVisibility(View.GONE);
                iv_select_weixin_friend.setVisibility(View.VISIBLE);
                iv_select_zhifubao.setVisibility(View.GONE);
                iv_select_qq.setVisibility(View.GONE);
                break;
            case R.id.layout_select_zhifubao:
                iv_selsect_weixin.setVisibility(View.GONE);
                iv_select_weixin_friend.setVisibility(View.GONE);
                iv_select_zhifubao.setVisibility(View.VISIBLE);
                iv_select_qq.setVisibility(View.GONE);
                break;
            case R.id.layout_select_qq:
                iv_selsect_weixin.setVisibility(View.GONE);
                iv_select_weixin_friend.setVisibility(View.GONE);
                iv_select_zhifubao.setVisibility(View.GONE);
                iv_select_qq.setVisibility(View.VISIBLE);
                break;
            case R.id.more_pay_layout:
                more_pay_layout.setVisibility(View.GONE);
                layout_select_zhifubao.setVisibility(View.VISIBLE);
                layout_select_qq.setVisibility(View.VISIBLE);
                break;
        }
    }
}
