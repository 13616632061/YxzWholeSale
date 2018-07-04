package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.util.ImageUtil;
import com.wholesale.yzx.yxzwholesale.view.widght.PileAvertView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2018/7/1.
 */
public class GoodsListFragmentAdapter extends BaseQuickAdapter<GoodsListBean.ListFreshTypeBean,BaseViewHolder>{

    private Context context;

    public GoodsListFragmentAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<GoodsListBean.ListFreshTypeBean> data) {
        super(layoutResId, data);
        this.context=context;
    }


    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean.ListFreshTypeBean item) {
        ImageView iv_goods_url=helper.getView(R.id.iv_goods_url);
        Glide.with(context).load(item.getGoodsListImg()+ ImageUtil.ossSetImageSize(1000)).into(iv_goods_url);

        TextView tv_go_spell=helper.getView(R.id.tv_go_spell);

        helper.setText(R.id.tv_goods_name,item.getGoodsName());
        helper.setText(R.id.tv_goods_price,"￥"+item.getPrice()+"");
        helper.setText(R.id.tv_goods_spell_nums,"已拼"+item.getDiscount()+"件");

        PileAvertView pile_nums=helper.getView(R.id.pile_nums);
        List<String> list=new ArrayList<>();
        for(int i=0;i<item.getPellImageList().size();i++){
            list.add(item.getPellImageList().get(i).getPellImageUrl());
        }
        pile_nums.setAvertImages(list,2);

        if(item.isShowPellBtn()){
            tv_go_spell.setVisibility(View.VISIBLE);
        }else {
            tv_go_spell.setVisibility(View.GONE);
        }
        if(item.isShowPellNum()){
            pile_nums.setVisibility(View.VISIBLE);
        }else {
            pile_nums.setVisibility(View.GONE);
        }


    }
}