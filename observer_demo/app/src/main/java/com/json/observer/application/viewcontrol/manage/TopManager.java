package com.json.observer.application.viewcontrol.manage;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.json.observer.R;
import com.json.observer.application.activity.homeActivity.HomeActivity;
import com.json.observer.application.viewcontrol.activityMap.ActivityID;
import com.json.observer.application.viewcontrol.viewmap.ViewMapping;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by json on 16/2/1.
 */
public class TopManager implements Observer {
    private Context context;
    /** 自定义标题的实现控件 */
    public RelativeLayout titleWidget;
    /** 标题左边的设置按钮 */
    protected ImageButton setBtn;
    /** 标题右边的搜索按钮 */
    protected ImageButton searchBtn;
    protected ImageButton leftBack;
    protected TextView tvtitle;
    private static TopManager topManager=new TopManager();
    private TopManager(){

    }
    public static TopManager getTopManager(){
        return topManager;
    }
    @Override
    public void update(Observable observable, Object jumpId) {
        if (jumpId==null||jumpId.equals("")){
            return;
        }
        allViewGone();
        String title= ViewMapping.getMapping().getViewMap().get(jumpId).getTitle();
        if (jumpId.equals(ViewMapping.HOME)){
            showHomeView();
            tvtitle.setText(title);
        }else if (jumpId.equals(ViewMapping.PERSONAL)){
            titleWidget.setVisibility(View.VISIBLE);
            tvtitle.setText(title);
        }else if (jumpId.equals(ViewMapping.PRODUCT)){
            titleWidget.setVisibility(View.VISIBLE);
            tvtitle.setText(title);
        }
    }

    private void showHomeView() {
        titleWidget.setVisibility(View.VISIBLE);
        searchBtn.setVisibility(View.VISIBLE);
        setBtn.setVisibility(View.VISIBLE);

    }

    private void allViewGone() {
        titleWidget.setVisibility(View.GONE);
        searchBtn.setVisibility(View.GONE);
        setBtn.setVisibility(View.GONE);
        leftBack.setVisibility(View.GONE);
    }

    public void init(HomeActivity homeActivity) {
        context=homeActivity;
        homeActivity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.top_title);
        titleWidget= (RelativeLayout) homeActivity.findViewById(R.id.screen);
        tvtitle= (TextView) homeActivity.findViewById(R.id.title_text);
        searchBtn= (ImageButton) homeActivity.findViewById(R.id.search_button);
        setBtn= (ImageButton) homeActivity.findViewById(R.id.set_button);
        leftBack= (ImageButton) homeActivity.findViewById(R.id.left_back_button);
        setListener(homeActivity);
    }

    private void setListener(HomeActivity homeActivity) {
        searchBtn.setOnClickListener(mTitleButtonOnClickListener);
        searchBtn.setImageResource(R.drawable.home_title_search);
        setBtn.setOnClickListener(mTitleButtonOnClickListener);
        leftBack.setOnClickListener(mTitleButtonOnClickListener);
    }

    protected View.OnClickListener mTitleButtonOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search_button:
                    break;
                case R.id.set_button:
                    break;
            }
        }
    };
}
