package com.json.observer.application.base.viewImpl.PersonalView;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.json.observer.R;
import com.json.observer.application.base.BaseView;

/**
 * Created by json on 16/2/15.
 */
public class PerSonalConfigView extends BaseView {
    public PerSonalConfigView(Context context, String id, Bundle bundle) {
        super(context, id, bundle);
        init();
    }

    private void init() {
        LinearLayout rootview=new LinearLayout(context);
        rootview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        container=(LinearLayout)inflater.inflate(R.layout.personal_login,rootview);

    }
}
