package com.wholesale.yzx.yxzwholesale.view.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wholesale.yzx.yxzwholesale.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/7/4.
 */

public class PileAvertView extends LinearLayout {


    @InjectView(R.id.pile_view)
    PileView pileView;
    private Context context = null;
    public static final int VISIBLE_COUNT = 3;//默认显示个数

    public PileAvertView(Context context) {
        this(context, null);
        this.context = context;
    }

    public PileAvertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_group_pile_avert, this);
        ButterKnife.inject(this);
    }

    public void setAvertImages(List<String> imageList) {
        setAvertImages(imageList, VISIBLE_COUNT);
    }

    //如果imageList>visiableCount,显示List最上面的几个
    public void setAvertImages(List<String> imageList, int visibleCount) {
        pileView.removeAllViews();
        List<String> visibleList = null;
        if (imageList.size() > visibleCount) {
            visibleList = imageList.subList(imageList.size() - 1 - visibleCount, imageList.size() - 1);
            for (int i = 0; i < visibleCount; i++) {
                CircleImageView image = (CircleImageView) LayoutInflater.from(context).inflate(R.layout.item_group_round_avert, pileView, false);
                Glide.with(context).load(imageList.get(i)).into(image);
                pileView.addView(image);
            }
        }else {
            for (int i = 0; i < imageList.size(); i++) {
                CircleImageView image = (CircleImageView) LayoutInflater.from(context).inflate(R.layout.item_group_round_avert, pileView, false);
                Glide.with(context).load(imageList.get(i)).into(image);
                pileView.addView(image);
            }
        }

    }

}
