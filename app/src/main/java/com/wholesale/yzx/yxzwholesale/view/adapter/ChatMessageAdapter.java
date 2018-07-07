package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.ChatMessageBean;
import com.wholesale.yzx.yxzwholesale.util.ScreenUtil;

import java.util.List;

/**
 * Created by lyf on 2018/7/5.
 */
public class ChatMessageAdapter extends BaseQuickAdapter<ChatMessageBean.ChatMessageListBean,BaseViewHolder> {

    private Context context;
    private List<ChatMessageBean.ChatMessageListBean> data;
    public ChatMessageAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<ChatMessageBean.ChatMessageListBean> data) {
        super(layoutResId, data);
        this.context=context;
        this.data=data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, ChatMessageBean.ChatMessageListBean item) {

        ImageView iv_chat_photo=helper.getView(R.id.iv_chat_photo);
        Glide.with(context).load(item.getChatPersonPhoto()).into(iv_chat_photo);
        helper.setText(R.id.tv_chat_name,item.getChatName());
        helper.setText(R.id.tv_chat_message,item.getChatContent());
        helper.setText(R.id.tv_chat_time,item.getChatTime());


        TextView tv_delete=helper.getView(R.id.tv_delete);

        helper.addOnClickListener(R.id.tv_delete);

    }

}