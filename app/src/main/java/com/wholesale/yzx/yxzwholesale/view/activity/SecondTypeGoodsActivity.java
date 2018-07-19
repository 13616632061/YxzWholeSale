package com.wholesale.yzx.yxzwholesale.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.base.BaseData;
import com.wholesale.yzx.yxzwholesale.view.fragment.SecondTypeGoodsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class SecondTypeGoodsActivity extends BaseActivity {


    @InjectView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @InjectView(R.id.tab_layout)
    RecyclerTabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;


    private List<SecondTypeGoodsFragment> fragments = new ArrayList<>();
    private SecondTypeGoodsAdapter adapter;
    private String typeName;

    @Override
    protected int getContentId() {
        return R.layout.activity_second_type_goods;
    }

    @Override
    protected void init() {
        super.init();
        initData();
        adapter = new SecondTypeGoodsAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);

        tabLayout.setUpWithViewPager(viewpager);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        typeName = getIntent().getStringExtra("typeName");
        if (!TextUtils.isEmpty(typeName)) {
            initTitle(typeName, true, "", R.drawable.share);
        }
        for (int i = 0; i < BaseData.tabList.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("goodTypeId", i + "");//分类标签id,用户对应每一个分类数据刷新
            SecondTypeGoodsFragment secondTypeGoodsFragment = new SecondTypeGoodsFragment();
            secondTypeGoodsFragment.setArguments(bundle);

            fragments.add(secondTypeGoodsFragment);
        }
    }

    @OnClick({R.id.iv_title_text_left2})
    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
                finish();
                break;
        }
    }

    /**
     * viewpager支持Fragment适配器
     */
    public class SecondTypeGoodsAdapter extends FragmentPagerAdapter {

        public SecondTypeGoodsAdapter(FragmentManager fm) {
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
