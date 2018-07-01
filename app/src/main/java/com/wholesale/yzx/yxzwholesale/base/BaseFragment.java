package com.wholesale.yzx.yxzwholesale.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wholesale.yzx.yxzwholesale.view.widght.Loading;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/19.
 */

public abstract class BaseFragment extends Fragment {

    private android.support.v4.app.FragmentManager fragmentManager;
    //当前正在展示的Fragment
    private BaseFragment showFragment;
    private Loading mloading;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getContentId(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this,view);

        init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected abstract int getContentId();
    /**
     * 初始化
     */
    protected void init() {
        //获得fragmentManager对象
        fragmentManager = getChildFragmentManager();
    }

    /**
     * 显示隐藏Fragment
     */
    protected void showFragment(int resid, BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏正在暂时的Fragment
        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }
        //展示需要显示的Fragment对象
        Fragment mFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        if (mFragment != null) {
            fragmentTransaction.show(mFragment);
            showFragment = (BaseFragment) mFragment;
        } else {
            fragmentTransaction.add(resid, fragment, fragment.getClass().getName());
            showFragment = fragment;
        }
        fragmentTransaction.commit();
    }
    /**
     * 加载动画显示
     */
    public void showLoading(){
        if(mloading==null){
            mloading=new Loading(getActivity());
            mloading.show();
        }
    }

    /**
     * 取消加载动画
     */
    public void dismissLoading(){
        if(mloading!=null&&mloading.isShowing()){
            mloading.dismiss();
            mloading=null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissLoading();
    }
}
