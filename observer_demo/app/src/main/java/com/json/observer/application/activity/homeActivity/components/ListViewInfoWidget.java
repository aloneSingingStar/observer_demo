package com.json.observer.application.activity.homeActivity.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.json.observer.R;

/**
 * Created by json on 16/2/4.
 */
public class ListViewInfoWidget extends DataInterface {
    public ListViewInfoWidget(Context context) {
        super(context);
    }

    @Override
    public void getView(ViewGroup parent) {
        View root = (View) LayoutInflater.from(context).inflate(R.layout.popup_select_bank, parent);
    }
}
