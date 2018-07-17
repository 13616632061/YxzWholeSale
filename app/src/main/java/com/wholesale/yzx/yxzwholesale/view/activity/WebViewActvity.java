package com.wholesale.yzx.yxzwholesale.view.activity;


import android.content.Context;
import android.util.Log;

import com.alibaba.verificationsdk.ui.IActivityCallback;
import com.alibaba.verificationsdk.ui.VerifyActivity;
import com.alibaba.verificationsdk.ui.VerifyType;
import com.alibaba.wireless.security.jaq.JAQException;
import com.alibaba.wireless.security.jaq.SecurityInit;
import com.alibaba.wireless.security.jaq.SecurityVerification;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;

import java.util.Map;


public class WebViewActvity extends BaseActivity {

    private SecurityVerification securityVerification = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_web_view_actvity;
    }

    @Override
    protected void init() {
        super.init();
        Context context = this.getApplicationContext();
        try {
            SecurityInit.Initialize(context);
            securityVerification = new SecurityVerification(context);
        } catch (JAQException e) {
            e.printStackTrace();
        }

        VerifyActivity. startSimpleVerifyUI(WebViewActvity.this,
                VerifyType.NOCAPTCHA,"0335",null, new IActivityCallback() {
                    @Override
                    public void onNotifyBackPressed() {
                    }
                    @Override
                    public void onResult(int retInt, Map<String, String> code) {
                        switch ( retInt ) {
                            case VerifyActivity.VERIFY_SUCC:
                                Log.e("风控",code.get("sessionID"));
                                break;
                            case VerifyActivity.VERIFY_FAILED:
                                Log.e("风控",code.get("errorCode"));
                                Log.e("风控",code.get("errorMsg"));
                                break;
                            default:
                                break;
                        }
                    }
                });
    }
}
