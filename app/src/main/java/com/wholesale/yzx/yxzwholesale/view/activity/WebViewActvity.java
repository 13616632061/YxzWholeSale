package com.wholesale.yzx.yxzwholesale.view.activity;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;


public class WebViewActvity extends BaseActivity {


    @InjectView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @InjectView(R.id.webview)
    WebView webview;

    private String url = "https://www.baidu.com/";
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessage5;
    public static final int FILECHOOSER_RESULTCODE = 5173;
    public static final int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 5174;


    @Override
    protected int getContentId() {
        return R.layout.activity_web_view_actvity;
    }

    @Override
    protected void init() {
        super.init();
        webview.loadUrl(url);

        WebSettings settings = webview.getSettings();
//设置WebView是否允许执行JavaScript脚本，默认false，不允许
        settings.setJavaScriptEnabled(true);
//是否开启本地DOM存储  可处理一些手机显示空白问题
        settings.setDomStorageEnabled(true);
//设置自适应屏幕宽度
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        settings.setDefaultTextEncodingName("utf-8");
//设置是否支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);

//设置缓存，否则后台重定向返回后导致缓存丢失
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//android5.0以上不显示图片的解决办法
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //处理webview URL跳转事件
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                loadHistoryUrls.add(url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //接收所有证书
                handler.proceed();
//                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("url=" + url);

                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setWebChromeClient();//处理android系统webView调用H5文件上传JS无反应操作
    }

    @OnClick({R.id.iv_title_text_left2})
    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
                finish();
                break;
        }
    }

    /**
     * TODO 处理android系统webView调用H5文件上传JS无反应操作
     */
    private void setWebChromeClient() {
        webview.setWebChromeClient(new WebChromeClient() {

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                this.openFileChooser(uploadMsg, "*/*");
            }

            // For Android >= 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                        String acceptType) {
                this.openFileChooser(uploadMsg, acceptType, null);
            }

            // For Android >= 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                        String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"),
                        FILECHOOSER_RESULTCODE);
            }

            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {
                if (mUploadMessage5 != null) {
                    mUploadMessage5.onReceiveValue(null);
                    mUploadMessage5 = null;
                }
                mUploadMessage5 = filePathCallback;
                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent,
                            FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
                } catch (ActivityNotFoundException e) {
                    mUploadMessage5 = null;
                    return false;
                }
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress == 100) {
//                    // 网页加载完成
//                    pb.setVisibility(View.GONE);
//                } else {
//                    // 加载中
//                    pb.setVisibility(View.VISIBLE);
//                    pb.setProgress(newProgress);
//                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {//获取网页标题
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title)) {
                    initTitle(title, true, "", 0);
                }
            }
        });

    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage) {
                return;
            }
            Uri result = intent == null || resultCode != Activity.RESULT_OK ? null
                    : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            if (null == mUploadMessage5) {
                return;
            }
            mUploadMessage5.onReceiveValue(WebChromeClient.FileChooserParams
                    .parseResult(resultCode, intent));
            mUploadMessage5 = null;
        }
    }

}
