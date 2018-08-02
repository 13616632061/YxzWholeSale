package com.wholesale.yzx.yxzwholesale.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;
import com.wholesale.yzx.yxzwholesale.view.fragment.OrderListFragment;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

public class MyOrderListAcitivty extends BaseActivity {


    @InjectView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @InjectView(R.id.tab_layout)
    RecyclerTabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    private IndexFragmentAdapter adapter;
    private ArrayList<OrderListFragment> fragments = new ArrayList<>();
    private ArrayList<String> tabList = new ArrayList<>();
    private  int index=0;

    @Override
    protected int getContentId() {
        return R.layout.activity_my_order_list_acitivty;
    }

    @Override
    protected void init() {
        super.init();

        initTitle("我的订单",true,"",R.drawable.search_bar_icon_normal);
        initData();
        adapter = new IndexFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setUpWithViewPager(viewpager);
        viewpager.setOffscreenPageLimit(0);

        Intent intent=getIntent();
        if(intent!=null){
             index=intent.getIntExtra("index",0);
        }
        tabLayout.setCurrentItem(index,true);
    }
    /**
     * 初始化数据
     */
    private void initData(){
        tabList.add("全部");
        tabList.add("待付款");
        tabList.add("待分享");
        tabList.add("代发货");
        tabList.add("待收货");
        tabList.add("待评价");

        for(int i = 0; i< tabList.size(); i++){
            Bundle bundle=new Bundle();
            bundle.putString("TypeId",i+"");//分类标签id,用户对应每一个分类数据刷新
            OrderListFragment orderListFragment=new OrderListFragment();
            orderListFragment.setArguments(bundle);

            fragments.add(orderListFragment);
        }
    }
    /**
     * viewpager支持Fragment适配器
     */
    public class IndexFragmentAdapter extends FragmentPagerAdapter {

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
            return tabList.get(position);//分类标签
        }
    }
    @OnClick({R.id.left_return_btn})
    public void setOnClick(View view){
        switch (view.getId()){
            case R.id.left_return_btn:
                finish();
                break;
        }
    }
}
