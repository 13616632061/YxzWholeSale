package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class ChatFragment extends BaseFragment {

    @InjectView(R.id.list)
    RecyclerView list;



    @Override
    protected int getContentId() {
        return R.layout.chat_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();
    }
}
