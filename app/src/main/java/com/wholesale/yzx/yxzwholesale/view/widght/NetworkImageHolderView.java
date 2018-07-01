package com.wholesale.yzx.yxzwholesale.view.widght;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.wholesale.yzx.yxzwholesale.R;

/**
 * Created by lyf on 2018/7/1.
 */
public class NetworkImageHolderView extends Holder<String> {

    private Context context;
    private ImageView imageView;

    public NetworkImageHolderView(Context context,View itemView) {
        super(itemView);
        this.context=context;
    }

    @Override
    protected void initView(View itemView) {
            imageView=itemView.findViewById(R.id.iv_banner);
    }

    @Override
    public void updateUI(String data) {
        Glide.with(context).load(data).into(imageView);
    }

//    @Override
//    public View createView(Context context) {
//        imageView=new ImageView(context);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        return imageView;
//    }
//
//    @Override
//
//    public void UpdateUI(Context context, int position, String data) {
//        Glide.with(context).load(data).into(imageView);
//    }
}