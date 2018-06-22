package com.wholesale.yzx.yxzwholesale.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.util.KeyboardUtil;
import com.wholesale.yzx.yxzwholesale.view.widght.Loading;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/19.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private android.support.v4.app.FragmentManager fragmentManager;
    //当前正在展示的Fragment
    private BaseFragment showFragment;
    private Loading mloading;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getContentId());
        initSystemStatus(R.color.status_bar_color);
        init();
    }

    /**
     * 初始化
     */
    protected void init() {
        ButterKnife.inject(this);
        //获得fragmentManager对象
        fragmentManager = getSupportFragmentManager();
    }

    /**
     * 引入布局
     * @return
     */
    protected abstract int getContentId();
    /**
     * 初始化系统状态栏
     * @param ResId
     */
    private void initSystemStatus(int ResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(ResId));
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 初始化标题
     * @param title 页面标题名
     * @param isGone 右边是否隐藏
     * @param rightText 右边
     */
    public void initTitle(String title, boolean isGone, String rightText){
        if (title != null) {
            ((TextView) findViewById(R.id.title_ac_text)).setText(title); //
        }
        if (isGone) {
            findViewById(R.id.iv_title_text_right).setVisibility(View.GONE);
        } else {
            findViewById(R.id.iv_title_text_right).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.iv_title_text_right)).setText(rightText);

        }
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
     * 点击空白处软键盘隐藏
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                KeyboardUtil.hideKeyboard(ev, view, BaseActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        try {
            return super.dispatchTouchEvent(ev);
        }catch (Exception e){
            return false;
        }
    }
    /**
     * 加载动画显示
     */
    public void showLoading(){
        if(mloading==null){
            mloading=new Loading(this);
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
    protected void onDestroy() {
        super.onDestroy();
        dismissLoading();
    }
}
