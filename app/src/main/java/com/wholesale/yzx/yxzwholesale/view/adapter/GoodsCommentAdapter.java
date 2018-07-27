package com.wholesale.yzx.yxzwholesale.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.bean.GoodsCommentInfoBean;
import com.wholesale.yzx.yxzwholesale.bean.MultiItemView;
import com.wholesale.yzx.yxzwholesale.view.widght.WarpLinearLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */

public class GoodsCommentAdapter extends BaseMultiItemQuickAdapter<MultiItemView<GoodsCommentInfoBean.GoodsCommentListBean>,BaseViewHolder> {

    private Context context;
    public GoodsCommentAdapter(Context context,List<MultiItemView<GoodsCommentInfoBean.GoodsCommentListBean>> data) {
        super(data);
        this.context=context;

        addItemType(MultiItemView.TITLE, R.layout.item_goods_comment_title);
        addItemType(MultiItemView.BODY,R.layout.item_goods_comment_body);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemView<GoodsCommentInfoBean.GoodsCommentListBean> item) {
        switch (item.getItemType()){
            case MultiItemView.TITLE:
                ImageView iv_commenter_photo=helper.getView(R.id.iv_commenter_photo);
                Glide.with(context).load(item.getBean().getCommenterPhoto()).into(iv_commenter_photo);
                helper.setText(R.id.tv_commenter_name,item.getBean().getCommenterName());
                helper.setText(R.id.tv_comment_time,item.getBean().getCommentTime());
                break;
            case MultiItemView.BODY:
                helper.setText(R.id.tv_comment_content,item.getBean().getCommentContent());
                WarpLinearLayout warpLinearLayout=helper.getView(R.id.iv_comment_photo);
                warpLinearLayout.removeAllViews();
                if(item.getBean().getCommentImage().size()==1){
                    View view=View.inflate(context,R.layout.item_comment_photo1,null);
                    ImageView iv_photo1=view.findViewById(R.id.iv_photo1);
                    Glide.with(context).load(item.getBean().getCommentImage().get(0).getImageUrl()).into(iv_photo1);
                    warpLinearLayout.addView(view);
                }
                if(item.getBean().getCommentImage().size()>1){
                    for(int i=0;i<item.getBean().getCommentImage().size();i++){
                        View view=View.inflate(context,R.layout.item_comment_photo2,null);
                        ImageView iv_photo2=view.findViewById(R.id.iv_photo2);
                        Glide.with(context).load(item.getBean().getCommentImage().get(i).getImageUrl()).into(iv_photo2);
                        warpLinearLayout.addView(view);
                    }
                }
                break;
        }
    }
}
