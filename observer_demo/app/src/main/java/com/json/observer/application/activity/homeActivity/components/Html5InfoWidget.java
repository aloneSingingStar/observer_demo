package com.json.observer.application.activity.homeActivity.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.json.observer.R;
import com.json.observer.application.activity.html.JsonWebView;
import com.json.observer.application.activity.html.JsonWebViewClient;

/**
 * Created by json on 16/2/3.
 */
public class Html5InfoWidget extends DataInterface {
    public  Context context;
    public JsonWebView jsonWebView;
    public Html5InfoWidget(Context context){
        super(context);
        this.context=context;
    }

    @Override
    public void getView(ViewGroup parent) {
        View root = (View) LayoutInflater.from(context).inflate(R.layout.html_info_widget, parent);
        jsonWebView =(JsonWebView)root.findViewById(R.id.webview);
        jsonWebView.requestFocus();
        jsonWebView.loadUrl("https://tuc.hsmdb.com/operation_web/info");
        jsonWebView.setWebViewClient(new JsonWebViewClient(context,jsonWebView));
    }
}
