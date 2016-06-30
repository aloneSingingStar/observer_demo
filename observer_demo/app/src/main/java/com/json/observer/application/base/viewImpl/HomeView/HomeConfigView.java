package com.json.observer.application.base.viewImpl.HomeView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.json.observer.R;
import com.json.observer.application.activity.homeActivity.components.DataInterface;
import com.json.observer.application.activity.homeActivity.components.Html5InfoWidget;
import com.json.observer.application.activity.homeActivity.components.ListViewInfoWidget;
import com.json.observer.application.base.BaseView;

import java.util.ArrayList;

/**
 * Created by json on 16/2/1.
 */
public class HomeConfigView extends BaseView {
    private ArrayList<DataInterface> mViews = new ArrayList<DataInterface>();
    private LinearLayout paoLayout;
    public HomeConfigView(Context context, String id, Bundle bundle) {
        super(context, id, bundle);
        init();
    }

    private void init() {
        LinearLayout rootview=new LinearLayout(context);
        rootview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        container=(LinearLayout)inflater.inflate(R.layout.home_middle_view,rootview);
        LinearLayout mainLayout = (LinearLayout) container.findViewById(R.id.linearLayout);

//        paoLayout = (LinearLayout) container.findViewById(R.id.pao);
//        paoLayout.setBackgroundColor(Color.argb(0, 255, 0, 0));

        mViews.add(getChildView(mainLayout,0));
//        mViews.add(getChildView(mainLayout,1));

    }

    private DataInterface getChildView(ViewGroup parent,int id) {
        DataInterface di=null;
        switch (id){
            case 0:
                di=new Html5InfoWidget(context);
                break;
            case 1:
                di=new ListViewInfoWidget(context);
                break;
        }
        if (di!=null){
            di.getView(parent);
        }
        return di;
    }
}
