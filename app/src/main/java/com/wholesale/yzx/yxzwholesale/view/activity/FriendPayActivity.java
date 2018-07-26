package com.wholesale.yzx.yxzwholesale.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;

import org.raphets.roundimageview.RoundImageView;

import butterknife.InjectView;
import butterknife.OnClick;

public class FriendPayActivity extends BaseActivity {



    @InjectView(R.id.iv_person_photo)
    RoundImageView ivPersonPhoto;
    @InjectView(R.id.iv_goods_photo)
    ImageView ivGoodsPhoto;

    @Override
    protected int getContentId() {
        return R.layout.activity_friend_pay;
    }

    @Override
    protected void init() {
        super.init();
        initTitle("好友代付", true, "", 0);

        String goodsImage="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3032392112,2704801904&fm=27&gp=0.jpg";
        String personPhoto="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3612952217,3851762239&fm=27&gp=0.jpg";

        Glide.with(this).load(goodsImage).into(ivGoodsPhoto);
        Glide.with(this).load(personPhoto).into(ivPersonPhoto);


    }

   @OnClick({R.id.iv_title_text_left2})
    public void setOnClick(View view){
       switch (view.getId()){
           case R.id.iv_title_text_left2:
               finish();
               break;
       }
   }
}
