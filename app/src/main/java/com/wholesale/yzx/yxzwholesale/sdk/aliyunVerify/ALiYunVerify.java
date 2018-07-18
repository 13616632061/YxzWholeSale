package com.wholesale.yzx.yxzwholesale.sdk.aliyunVerify;

import android.content.Context;
import android.util.Log;

import com.alibaba.verificationsdk.ui.IActivityCallback;
import com.alibaba.verificationsdk.ui.VerifyActivity;
import com.alibaba.verificationsdk.ui.VerifyType;
import com.alibaba.wireless.security.jaq.JAQException;
import com.alibaba.wireless.security.jaq.SecurityInit;
import com.alibaba.wireless.security.jaq.SecurityVerification;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/18.
 * 阿里云风控验证
 */

public class ALiYunVerify {

    private static SecurityVerification securityVerification = null;

    public static void aLiYunVerfy(Context context){
        Context mContext = context.getApplicationContext();
        try {
            SecurityInit.Initialize(context);
            securityVerification = new SecurityVerification(context);
        } catch (JAQException e) {
            e.printStackTrace();
        }

        VerifyActivity. startSimpleVerifyUI(context,
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
