package com.json.observer.application.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by json on 16/2/1.
 */
public abstract class BaseView implements View.OnClickListener {
    protected Context context;//父类属性不能为private,否则子类无法使用
    protected String id;
    protected Bundle bundle;
    protected LayoutInflater inflater;
    protected ViewGroup container;
    public BaseView(Context context,String id,Bundle bundle){
        this.context=context;
        this.id=id;
        this.bundle=bundle;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public void onClick(View v) {

    }

    public View getView() {
        if(container.getLayoutParams()==null){
            container.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return container;
    }
}
