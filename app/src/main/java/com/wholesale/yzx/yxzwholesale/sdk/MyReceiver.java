package com.wholesale.yzx.yxzwholesale.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/7/17.
 */

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle=intent.getExtras();
        if(JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())){
            String regId=bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.i(TAG, "[MyReceiver] 接收Registration Id : " + regId);
        }else if(JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            String type2 = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i(TAG, "[MyReceiver] 接收到推送下来的自定义消息内容: " + type2);
        }else if(JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())){
            Log.i(TAG, "[MyReceiver] 接收到推送下来的通知");

        }else if(JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())){
            Log.i(TAG, "[MyReceiver] 用户点击打开了通知");
        }
    }



    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        //省略了

    }
}
