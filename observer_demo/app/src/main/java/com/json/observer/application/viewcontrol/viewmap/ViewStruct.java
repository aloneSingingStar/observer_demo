package com.json.observer.application.viewcontrol.viewmap;

import com.json.observer.application.base.BaseView;

import lombok.Getter;

/**
 * Created by json on 16/2/2.
 */
public class ViewStruct {
    @Getter
    private String title;
    @Getter
    private Class<? extends BaseView> targetClass;

    public ViewStruct(String title,Class<? extends BaseView> targetClass){
        this.title=title;
        this.targetClass=targetClass;
    }

}
