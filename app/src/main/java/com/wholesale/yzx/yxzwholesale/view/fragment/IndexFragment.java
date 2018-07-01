package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseData;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.GoodsTypeBean;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsPageAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lyf on 2018/6/27.
 */
public class IndexFragment extends BaseFragment {
        @InjectView(R.id.tab_layout)
        RecyclerTabLayout tabLayout;
        @InjectView(R.id.viewpager)
        ViewPager viewpager;
    private IndexFragmentAdapter adapter;
    private ArrayList<GoodsTypeBean> datas=new ArrayList<>();
    private ArrayList<GoodsListFragment> fragments=new ArrayList<>();


    @Override
    protected int getContentId() {
        return R.layout.index_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();

        initData();
        adapter=new IndexFragmentAdapter(getFragmentManager());
        viewpager.setAdapter(adapter);

        tabLayout.setUpWithViewPager(viewpager);//关联viewpager

    }

    /**
     * 初始化数据
     */
    private void initData(){

        for(int i = 0; i< BaseData.tabList.length; i++){
            Bundle bundle=new Bundle();
            bundle.putString("goodTypeId",i+"");//分类标签id,用户对应每一个分类数据刷新
            GoodsListFragment goodsListFragment=new GoodsListFragment();
            goodsListFragment.setArguments(bundle);

            fragments.add(goodsListFragment);
        }
    }

    /**
     * viewpager支持Fragment适配器
     */
    public class IndexFragmentAdapter extends FragmentPagerAdapter{

        public IndexFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return BaseData.tabList[position];//分类标签
        }
    }
}