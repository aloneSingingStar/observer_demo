package com.json.observer.application.viewcontrol.manage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.json.observer.application.base.BaseView;
import com.json.observer.application.viewcontrol.viewmap.ViewMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

/**
 * Created by json on 16/2/1.
 */
public class UiManager extends Observable {
    private Class<? extends BaseView> targetClass;
    private static UiManager manager=new UiManager();
    private UiManager(){

    }
    public static UiManager getInstance(){
        return manager;
    }
    private LinearLayout middleContainer;
    public void setMiddleContainer(LinearLayout middleContainer){
        this.middleContainer=middleContainer;
    }

    /**
     * 切换视图
     * @param id
     * @param bundle
     */
    public void changeView(String id, Bundle bundle) {
        targetClass= ViewMapping.getMapping().getViewMap().get(id).getTargetClass();
        BaseView targetView=null;
        try {
            Constructor<? extends BaseView> constructor=targetClass.getConstructor(Context.class, String.class, Bundle.class);
            targetView=constructor.newInstance(middleContainer.getContext(),id,bundle);
            View view=targetView.getView();
            middleContainer.removeAllViews();
            middleContainer.addView(view);
            changeTitleandBottom(id);
            
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换顶部标题和底部菜单栏
     * @param id
     */
    private void changeTitleandBottom(String id) {
        setChanged();
        notifyObservers(id);
    }
}
