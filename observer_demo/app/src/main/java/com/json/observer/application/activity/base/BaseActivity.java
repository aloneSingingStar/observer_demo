package com.json.observer.application.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import de.greenrobot.event.EventBus;

/**
 * Created by json on 16/2/1.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
