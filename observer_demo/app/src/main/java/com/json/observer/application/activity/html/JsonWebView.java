package com.json.observer.application.activity.html;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by json on 16/2/3.
 */
public class JsonWebView extends WebView{
    public static final String WEB_ERROR = "winner_web_error";
    private  String mUrl;
    private Context context;
    private JsonWebViewClient jsonWebViewClient;
    private JsonChormWebClient jsonChormWebClient;
    public JsonWebView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    private void init() {
        jsonChormWebClient=new JsonChormWebClient();
        jsonWebViewClient=new JsonWebViewClient(context,this);
        setWebChromeClient(jsonChormWebClient);
        setWebViewClient(jsonWebViewClient);
        getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        WebSettings dd = getSettings();
        dd.setJavaScriptEnabled(true);
        dd.setBuiltInZoomControls(true);
//        addJavascriptInterface(new JsFunction(context), "action");
    }

    public JsonWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public JsonWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    @Override
    public void loadUrl(String url) {
        if(url != null &&( url.equals(WEB_ERROR))) {
            mUrl="https://www.baidu.com";
        } else {
            mUrl = url;
        }
        super.loadUrl(mUrl);
    }
}
