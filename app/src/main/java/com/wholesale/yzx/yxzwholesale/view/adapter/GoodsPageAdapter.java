package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.wholesale.yzx.yxzwholesale.bean.GoodsTypeBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/28.
 */

public class GoodsPageAdapter extends PagerAdapter {

    private ArrayList<GoodsTypeBean> datas=new ArrayList<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_index_goods_list,container,false);
//        TextView v
        return super.instantiateItem(container, position);
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
