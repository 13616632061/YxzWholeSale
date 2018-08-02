package com.wholesale.yzx.yxzwholesale.view.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;

/**
 * Created by Administrator on 2018/8/2.
 */

public class EmptyLayout extends LinearLayout {

    private ImageView iv_empty;
    private TextView tv_empty_msg,tv_look_all,tv_login_style;
    private LinearLayout empty_layout;

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        View view = View.inflate(context, R.layout.empty_data_layout, null);
        LayoutInflater.from(context).inflate(R.layout.empty_data_layout,this);
        iv_empty = findViewById(R.id.iv_empty);
        tv_empty_msg = findViewById(R.id.tv_empty_msg);
        empty_layout = findViewById(R.id.empty_layout);
        tv_look_all = findViewById(R.id.tv_look_all);
        tv_login_style = findViewById(R.id.tv_login_style);
    }


    /**
     * 查看更多
     * @param onClickListener
     */
    public void setLookAll(OnClickListener onClickListener){
        tv_look_all.setOnClickListener(onClickListener);
    }
}
