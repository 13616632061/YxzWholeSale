package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.GoodsTypeBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/28.
 */

public class GoodsPageAdapter extends PagerAdapter {

    private ArrayList<GoodsTypeBean> datas=new ArrayList<>();
    private Context context;

    public GoodsPageAdapter(Context context, ArrayList<GoodsTypeBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_index_goods_list,container,false);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).getTypeName();
    }

    public void addAll(ArrayList<GoodsTypeBean> datas){
        datas=new ArrayList<>(datas);
    }
}
