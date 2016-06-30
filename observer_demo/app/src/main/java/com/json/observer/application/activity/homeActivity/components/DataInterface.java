package com.json.observer.application.activity.homeActivity.components;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by json on 16/2/4.
 */
public abstract class DataInterface {
    protected Context context;
    public DataInterface(Context context){
        this.context=context;
    }
    public abstract void getView(ViewGroup parent);
}
