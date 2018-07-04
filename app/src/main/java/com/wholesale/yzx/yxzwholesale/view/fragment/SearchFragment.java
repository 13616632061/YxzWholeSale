package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;

import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class SearchFragment extends BaseFragment {

    @InjectView(R.id.iv_share)
    ImageView ivShare;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.type_list)
    RecyclerView typeList;
    @InjectView(R.id.goods_list)
    RecyclerView goodsList;

    @Override
    protected int getContentId() {
        return R.layout.search_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();
    }
}
