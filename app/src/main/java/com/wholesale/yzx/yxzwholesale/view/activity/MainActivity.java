package com.wholesale.yzx.yxzwholesale.view.activity;

import android.graphics.drawable.Drawable;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;

import butterknife.InjectView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @InjectView(R.id.tabs_rg)
    RadioGroup tabsRg;
    @InjectView(R.id.tab_index)
    RadioButton tabIndex;
    @InjectView(R.id.tab_recommend)
    RadioButton tabRecommend;
    @InjectView(R.id.tab_search)
    RadioButton tabSearch;
    @InjectView(R.id.tab_chat)
    RadioButton tabChat;
    @InjectView(R.id.tab_person)
    RadioButton tabPerson;

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();

        tabsRg.setOnCheckedChangeListener(this);
    }

    // 设置底部导航栏图片的颜色和字体的颜色。
    private void setDrawable(RadioButton rb, int picture) {
        Drawable drawable = getResources().getDrawable(picture);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 这一步必须要做,否则不会显示.
        rb.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * 底部导航栏
     *
     * @param radioGroup
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.tab_index:
                break;
            case R.id.tab_recommend:
                break;
            case R.id.tab_search:
                break;
            case R.id.tab_chat:
                break;
            case R.id.tab_person:
                break;
        }
    }


}
