package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;

import java.util.List;

/**
 * Created by lyf on 2018/7/5.
 */
public class ChatMessageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Context context;
    public ChatMessageAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        LinearLayout layout=helper.getView(R.id.layout);
        int width= ScreenUtil.getScreenWidth(context);
        ViewGroup.LayoutParams params=layout.getLayoutParams();
        params.width=width;
        layout.setLayoutParams(params);


        helper.setText(R.id.tv_chat_message,item);

        TextView tv_delete=helper.getView(R.id.tv_delete);
    }
}