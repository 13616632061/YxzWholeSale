package com.wholesale.yzx.yxzwholesale.view.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.bean.GoodsCommentInfoBean;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsCommentAdapter;
import com.wholesale.yzx.yxzwholesale.view.widght.WarpLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsCommentAcitvity extends BaseActivity {


    @InjectView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @InjectView(R.id.iv_titlle_image_right)
    ImageView ivTitlleImageRight;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private WarpLinearLayout warp_layout;
    private LinearLayout layout_more;
    private ImageView iv_more;

    private GoodsCommentAdapter adapter;
    private List<MultiItemView<GoodsCommentInfoBean.GoodsCommentListBean>> datas = new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.activity_goods_comment_acitvity;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("商品评价", false, "", R.drawable.share);

        View headterView=View.inflate(this,R.layout.goods_comment_headterview,null);

        initHeadterView(headterView);


        adapter = new GoodsCommentAdapter(this, datas);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter.addHeaderView(headterView);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                getGoodslistData();
            }
        });
        getGoodslistData();
    }

    private void initHeadterView(View headterView) {
        warp_layout=headterView.findViewById(R.id.warp_layout);
//        layout_more=headterView.findViewById(R.id.layout_more);
//        iv_more=headterView.findViewById(R.id.iv_more);
//        warp_layout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//
//                warp_layout.getViewTreeObserver().removeOnPreDrawListener(this);
//                Log.i("评论",warp_layout.getChildCount()+"");
//                Log.i("评论",warp_layout.getLineCount()+"");
//                if(warp_layout.getLineCount()>2){
//                    layout_more.setVisibility(View.VISIBLE);
//                }
//
//                return false;
//            }
//        });
        initCommentData();
    }
    /**
     * 初始化评论数据
     */
    private void initCommentData() {
        List<String> commentDatas = new ArrayList<>();
        commentDatas.add("全部（1725）");
        commentDatas.add("质量好（65）");
        commentDatas.add("快递快（27）");
        commentDatas.add("服务好（8）");
        commentDatas.add("追加（111）");
        commentDatas.add("有图（541）");
        commentDatas.add("回头客（122）");
        commentDatas.add("态度好（29）");
        commentDatas.add("价格实惠（15）");

        warp_layout.removeAllViews();
        for (int i = 0; i < commentDatas.size(); i++) {
            View view = View.inflate(GoodsCommentAcitvity.this, R.layout.goods_comment_type_layout, null);
            final TextView tv_comment_type = view.findViewById(R.id.tv_comment_type);
            tv_comment_type.setText(commentDatas.get(i));

            final int finalI = i;
            tv_comment_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<warp_layout.getChildCount();i++){
                        TextView tv= (TextView) warp_layout.getChildAt(i);
                        tv.setBackgroundResource(R.drawable.bg_pink_redus5);
                        tv.setTextColor(getResources().getColor(R.color.text_color_title));
                    }
                    tv_comment_type.setBackgroundResource(R.drawable.bg_red_redus5);
                    tv_comment_type.setTextColor(Color.parseColor("#FFFFFF"));
                }
            });
            warp_layout.setHorizontal_Space(ScreenUtil.dp2px(GoodsCommentAcitvity.this,8));
            warp_layout.setVertical_Space(ScreenUtil.dp2px(GoodsCommentAcitvity.this,8));
            warp_layout.addView(view);
        }
    }
    @OnClick({R.id.iv_title_text_left2})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.iv_title_text_left2:
                finish();
                break;
        }
    }
    /**
     * 商品列表数据
     */
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "goods_comment_info.json";
        String json = JsonUtil.getJson(this, fileName);
        GoodsCommentInfoBean bean = new Gson().fromJson(json, GoodsCommentInfoBean.class);
        if (bean != null) {
            for (GoodsCommentInfoBean.GoodsCommentListBean bean1 : bean.getGoodsCommentList()) {
                MultiItemView<GoodsCommentInfoBean.GoodsCommentListBean> multiItemViewTitle = new MultiItemView<>(MultiItemView.TITLE);
                GoodsCommentInfoBean.GoodsCommentListBean beanTitle = new GoodsCommentInfoBean.GoodsCommentListBean();
                beanTitle.setCommenterPhoto(bean1.getCommenterPhoto());
                beanTitle.setCommenterName(bean1.getCommenterName());
                beanTitle.setCommentTime(bean1.getCommentTime());
                multiItemViewTitle.setBean(beanTitle);
                datas.add(multiItemViewTitle);

                MultiItemView<GoodsCommentInfoBean.GoodsCommentListBean> multiItemViewBody = new MultiItemView<>(MultiItemView.BODY);
                GoodsCommentInfoBean.GoodsCommentListBean beanBody = new GoodsCommentInfoBean.GoodsCommentListBean();
                beanBody.setCommentContent(bean1.getCommentContent());
                beanBody.setCommentImage(bean1.getCommentImage());
                multiItemViewBody.setBean(beanBody);
                datas.add(multiItemViewBody);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }
}
