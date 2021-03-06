package com.wholesale.yzx.yxzwholesale.view.activity;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.dialogAndPopup.GoPellingOrderPopuWindow;
import com.wholesale.yzx.yxzwholesale.view.dialogAndPopup.MorePellingOrderInfoPopuWindow;
import com.wholesale.yzx.yxzwholesale.view.dialogAndPopup.PellingOrderPopuWindow;
import com.wholesale.yzx.yxzwholesale.view.widght.NetworkImageHolderView;
import com.wholesale.yzx.yxzwholesale.view.widght.UPMarqueeView;
import com.wholesale.yzx.yxzwholesale.view.widght.WarpLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsDetailActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @InjectView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.title_layout)
    View title_layout;
    @InjectView(R.id.tv_bottom_more)
    TextView tvBottomMore;
    @InjectView(R.id.tv_collage)
    TextView tvCollage;
    @InjectView(R.id.tv_custom_service)
    TextView tvCustomService;
    @InjectView(R.id.tv_alone_price)
    TextView tvAlonePrice;
    @InjectView(R.id.alone_layout)
    RelativeLayout aloneLayout;
    @InjectView(R.id.tv_spell_price)
    TextView tvSpellPrice;
    @InjectView(R.id.spell_layout)
    RelativeLayout spellLayout;
    @InjectView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;
    @InjectView(R.id.iv_titlle_image_right)
    ImageView ivTitlleImageRight;
    @InjectView(R.id.activity_goods_detail)
    RelativeLayout activityGoodsDetail;

    private ConvenientBanner convenient_banner;
    private LinearLayout layout_promise, layout_goods_evaluate;
    private TextView tv_look_more, tv_look_all, tv_go_shop;
    private UPMarqueeView arquee_v;
    private WarpLinearLayout warp_layout;
    private ImageView iv_logo;
    private RecyclerView shop_goods_list;

    private List<String> photoDatas = new ArrayList<>();
    private List<View> views = new ArrayList<>();


    private GoodsListFragmentAdapter adapter;//商品列表适配器
    private List<GoodsListBean.ListFreshTypeBean> datas = new ArrayList<>();//商品数据
    public List<String> goodsImageBanner;//广告轮播图数据

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    PellingOrderPopuWindow popuWindow2 = new PellingOrderPopuWindow(GoodsDetailActivity.this);
                    popuWindow2.showAtLocation(activityGoodsDetail, Gravity.NO_GRAVITY, 0, 0);
                    break;
            }
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("商品详情",true,"",R.drawable.share);
        View headerView = View.inflate(GoodsDetailActivity.this, R.layout.goods_detail_headview, null);
        initheaderView(headerView);

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
        adapter.setOnItemClickListener(this);
        adapter.addHeaderView(headerView);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                getGoodslistData();
            }
        });

        getGoodslistData();
    }

    /**
     * 拼单数据
     */
    private void initGoToOrder() {
        photoDatas.add("http://img4.imgtn.bdimg.com/it/u=2628386979,3922899995&fm=27&gp=0.jpg");
        photoDatas.add("http://img1.imgtn.bdimg.com/it/u=3328043160,1899669641&fm=27&gp=0.jpg");
        photoDatas.add("http://img4.imgtn.bdimg.com/it/u=3835798881,283752655&fm=27&gp=0.jpg");

        for (int i = 0; i < photoDatas.size(); i = i + 2) {
            View view = View.inflate(GoodsDetailActivity.this, R.layout.go_do_order_layout, null);
            ImageView iv = view.findViewById(R.id.iv_photo);
            ImageView iv2 = view.findViewById(R.id.iv_photo2);
            TextView tv_goto_order1=view.findViewById(R.id.tv_goto_order1);
            TextView tv_goto_order2=view.findViewById(R.id.tv_goto_order2);
            Glide.with(GoodsDetailActivity.this).load(photoDatas.get(i)).into(iv);
            if (photoDatas.size() > i + 1) {
                Glide.with(GoodsDetailActivity.this).load(photoDatas.get(i + 1)).into(iv2);
            } else {
                view.findViewById(R.id.layout2).setVisibility(View.INVISIBLE);
            }
            tv_goto_order1.setOnClickListener(this);
            tv_goto_order2.setOnClickListener(this);

            views.add(view);
        }
        arquee_v.setViews(views);
    }

    @OnClick({R.id.spell_layout, R.id.alone_layout, R.id.tv_bottom_more, R.id.tv_collage, R.id.tv_custom_service,R.id.iv_title_text_left2})
    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.spell_layout://拼单
                PellingOrderPopuWindow popuWindow = new PellingOrderPopuWindow(GoodsDetailActivity.this);
                popuWindow.showAtLocation(activityGoodsDetail, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.alone_layout://单独购买
                PellingOrderPopuWindow popuWindow2 = new PellingOrderPopuWindow(GoodsDetailActivity.this);
                popuWindow2.showAtLocation(activityGoodsDetail, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.tv_bottom_more://更多
                break;
            case R.id.tv_collage://收藏
                break;
            case R.id.tv_custom_service://客服
                break;
            case R.id.iv_title_text_left2://返回
                finish();
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_goto_order1:
                GoPellingOrderPopuWindow popuWindow1 = new GoPellingOrderPopuWindow(GoodsDetailActivity.this,handler);
                popuWindow1.showAtLocation(activityGoodsDetail, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.tv_goto_order2:
                GoPellingOrderPopuWindow popuWindow2= new GoPellingOrderPopuWindow(GoodsDetailActivity.this,handler);
                popuWindow2.showAtLocation(activityGoodsDetail, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.tv_look_more:
                MorePellingOrderInfoPopuWindow morePellingOrderInfoPopuWindow=new MorePellingOrderInfoPopuWindow(GoodsDetailActivity.this);
                morePellingOrderInfoPopuWindow.showAtLocation(activityGoodsDetail, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.tv_look_all:
                Intent intent=new Intent(GoodsDetailActivity.this,GoodsCommentAcitvity.class);
                startActivity(intent);
                break;
            case R.id.tv_go_shop:
                Intent intent_go_store=new Intent(GoodsDetailActivity.this,GoodsStoreInfoActivity.class);
                startActivity(intent_go_store);
                break;
        }
    }
    /**
     * 初始化头部
     *
     * @param headerView
     */
    private void initheaderView(View headerView) {
        convenient_banner = headerView.findViewById(R.id.convenient_banner);
        layout_promise = headerView.findViewById(R.id.layout_promise);
        layout_goods_evaluate = headerView.findViewById(R.id.layout_goods_evaluate);
        tv_look_more = headerView.findViewById(R.id.tv_look_more);
        tv_look_all = headerView.findViewById(R.id.tv_look_all);
        tv_go_shop = headerView.findViewById(R.id.tv_go_shop);
        arquee_v = headerView.findViewById(R.id.arquee_v);
        warp_layout = headerView.findViewById(R.id.warp_layout);
        iv_logo = headerView.findViewById(R.id.iv_logo);
        shop_goods_list = headerView.findViewById(R.id.shop_goods_list);


        showConvenientBanner();
        initGoToOrder();
        initCommentData();

        tv_look_more.setOnClickListener(this);
        tv_look_all.setOnClickListener(this);
        tv_go_shop.setOnClickListener(this);

    }

    /**
     * 初始化店铺商品
     */
    private void initShopGoodsData() {
        String logo = "http://img2.imgtn.bdimg.com/it/u=2971196485,2046477827&fm=27&gp=0.jpg";
        Glide.with(GoodsDetailActivity.this).load(logo).into(iv_logo);

        List<GoodsListBean.ListFreshTypeBean> shop_goods_datas = new ArrayList<>();//商品数据
        shop_goods_datas.add(datas.get(0));
        shop_goods_datas.add(datas.get(1));
        shop_goods_datas.add(datas.get(2));
        GoodsListFragmentAdapter adapter = new GoodsListFragmentAdapter(GoodsDetailActivity.this, R.layout.item_goods_list, shop_goods_datas);
        shop_goods_list.setAdapter(adapter);
        shop_goods_list.setLayoutManager(new GridLayoutManager(this, 3));
    }

    /**
     * 初始化评论数据
     */
    private void initCommentData() {
        List<String> commentDatas = new ArrayList<>();
        commentDatas.add("质量好（65）");
        commentDatas.add("快递速度快（27）");
        commentDatas.add("服务好（8）");

        warp_layout.removeAllViews();
        for (int i = 0; i < commentDatas.size(); i++) {
            View view = View.inflate(GoodsDetailActivity.this, R.layout.goods_comment_type_layout, null);
            TextView tv_comment_type = view.findViewById(R.id.tv_comment_type);
            tv_comment_type.setText(commentDatas.get(i));

            tv_comment_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            warp_layout.setHorizontal_Space(ScreenUtil.dp2px(GoodsDetailActivity.this,8));
            warp_layout.setVertical_Space(ScreenUtil.dp2px(GoodsDetailActivity.this,8));
            warp_layout.addView(view);
        }

        layout_goods_evaluate.removeAllViews();
        for (int i = 0; i < 2; i++) {
            View view = View.inflate(GoodsDetailActivity.this, R.layout.item_goods_comment, null);
            ImageView iv_commenter_photo = view.findViewById(R.id.iv_commenter_photo);
            Glide.with(GoodsDetailActivity.this).load(photoDatas.get(i)).into(iv_commenter_photo);

            layout_goods_evaluate.addView(view);
        }
    }

    /**
     * 广告轮播图
     */
    private void showConvenientBanner() {
        goodsImageBanner = new ArrayList<>();
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457446439&di=f9fe04c988d334900287de887d88c703&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F337%2F44549.jpg");
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457488011&di=98aea79bfe7db959223aae74c15f1552&imgtype=0&src=http%3A%2F%2Fpic22.photophoto.cn%2F20120105%2F0017030057473498_b.jpg");
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457515523&di=aa0cddeff5f699a2a875d44ec7cb0341&imgtype=0&src=http%3A%2F%2Fpic23.nipic.com%2F20120805%2F10665405_101511605116_2.jpg");
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457566718&di=056f36ce1989f21506390cc198c21318&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130323%2F9544839_100946415000_2.jpg");


        convenient_banner.setPages(new CBViewHolderCreator() {
            @Override
            public NetworkImageHolderView createHolder(View itemView) {
                return new NetworkImageHolderView(GoodsDetailActivity.this, itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_convenientbanner;
            }
        }, goodsImageBanner)
//                .setPageIndicator()
//           .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

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
                bean1.setShowPellNum(false);
                bean1.setShowPellBtn(false);
                datas.add(bean1);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
        initShopGoodsData();
    }

    @Override
    public void onResume() {
        super.onResume();
        convenient_banner.startTurning(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        convenient_banner.stopTurning();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

}
