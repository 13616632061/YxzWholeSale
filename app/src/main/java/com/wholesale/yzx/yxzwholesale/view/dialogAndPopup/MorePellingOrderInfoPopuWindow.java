package com.wholesale.yzx.yxzwholesale.view.dialogAndPopup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.MorePellingOrderInfoBean;
import com.wholesale.yzx.yxzwholesale.util.JsonUtil;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;
import com.wholesale.yzx.yxzwholesale.view.adapter.MorePellingOrderInfoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class MorePellingOrderInfoPopuWindow extends PopupWindow implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    private Context context;
    private ImageView iv_close;
    private MaterialRefreshLayout refresh;
    private RecyclerView list;
    private MorePellingOrderInfoAdapter adapter;
    private List<MorePellingOrderInfoBean.PellingOrderListBean> datas=new ArrayList<>();

    public MorePellingOrderInfoPopuWindow(Context context) {
        super(context);

        this.context=context;

        final View view=View.inflate(context, R.layout.more_pelling_order_popu_layout,null);
        initView(view);

        this.setContentView(view);
        //sdk > 21 解决 标题栏没有办法遮罩的问题
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ScreenUtil.getScreenHeight(context));
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pell_order_popu).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    private void initView(View view) {
        iv_close=view.findViewById(R.id.iv_close);
        refresh=view.findViewById(R.id.refresh);
        list=view.findViewById(R.id.list);

        adapter=new MorePellingOrderInfoAdapter(context,R.layout.item_more_pelling_order_info,datas);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(context));
        adapter.setOnItemChildClickListener(this);

        iv_close.setOnClickListener(this);
        getGoodslistData();

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                datas.clear();
                getGoodslistData();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                dismiss();
                break;
        }
    }
    /**
     * 商品列表数据
     */
    private void getGoodslistData() {
        //得到本地json文本内容
        String fileName = "more_pelling_order_info.json";
        String json = JsonUtil.getJson(context, fileName);
        MorePellingOrderInfoBean bean = new Gson().fromJson(json, MorePellingOrderInfoBean.class);
        if (bean != null) {
            for (MorePellingOrderInfoBean.PellingOrderListBean bean1 : bean.getPellingOrderList()) {
                datas.add(bean1);
            }
        }
        adapter.notifyDataSetChanged();
        refresh.finishRefresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
