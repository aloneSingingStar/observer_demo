package com.json.observer.application.activity.html;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
//import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by json on 16/2/3.
 */
public class JsonWebViewClient extends WebViewClient {
    public Context context;
    public JsonWebView jsonWebView;
    public JsonWebViewClient(Context context, JsonWebView jsonWebView) {
        this.context=context;
        this.jsonWebView=jsonWebView;
    }

//    @Override
//    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//        view.loadUrl(JsonWebView.WEB_ERROR);
//    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url == null){
            return super.shouldOverrideUrlLoading(null, null);
        }
        Intent intent=new Intent();
        intent.putExtra("url", url);
        intent.setClass(context, HtmInfoDetailActivity.class);
        context.startActivity(intent);
        return true;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }
}
