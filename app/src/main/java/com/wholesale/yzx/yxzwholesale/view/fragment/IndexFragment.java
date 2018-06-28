package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseData;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class IndexFragment extends BaseFragment {

    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.tab_layout)
    RecyclerTabLayout tabLayout;

    private List<GoodsListFragment> mGoodsListFragment = new ArrayList<>();
    private GoodsPageAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.index_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();

        getTabType();

    }

    private void getTabType() {
        for (int i = 0; i < BaseData.tabList.length; i++) {
            mGoodsListFragment.add(new GoodsListFragment());
        }
        adapter = new GoodsPageAdapter(getFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setUpWithViewPager(viewpager);


    }

    class GoodsPageAdapter extends FragmentPagerAdapter {

        public GoodsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mGoodsListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mGoodsListFragment.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return BaseData.tabList[position];
        }
    }

}
