package com.json.observer.application.activity.homeActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

import com.igexin.sdk.PushManager;
import com.json.observer.R;
import com.json.observer.application.activity.base.BaseActivity;
import com.json.observer.application.model.event.ToastShowEvent;
import com.json.observer.application.viewcontrol.manage.BottomManager;
import com.json.observer.application.viewcontrol.manage.TopManager;
import com.json.observer.application.viewcontrol.manage.UiManager;
import com.json.observer.application.viewcontrol.viewmap.ViewMapping;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by json on 16/2/1.
 */
public class HomeActivity extends BaseActivity {
    private LinearLayout maincenter;
    private LinearLayout bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushManager.getInstance().initialize(this.getApplicationContext());//初始化个推SDK
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//定制Activity标题栏,顺序不能错
        setContentView(R.layout.home_activity);
        init();
    }

    private void init() {
        maincenter=(LinearLayout)findViewById(R.id.ll_center);
        bottom= (LinearLayout) findViewById(R.id.ll_bottom);
        UiManager.getInstance().setMiddleContainer(maincenter);
        TopManager.getTopManager().init(this);
        BottomManager.getBottomManager().init(this);
        UiManager.getInstance().addObserver(TopManager.getTopManager());
        UiManager.getInstance().addObserver(BottomManager.getBottomManager());
        UiManager.getInstance().changeView(ViewMapping.HOME,null);
    }

    public void onEvent(ToastShowEvent event){

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
