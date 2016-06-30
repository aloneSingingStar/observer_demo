package com.json.observer.application.model.bottommenu;

import lombok.Getter;

/**
 * Created by json on 16/2/3.
 */
public class MenuItem {
    @Getter
    public String jumpId;//跳转页面ID
    @Getter
    public int title;//菜单名称
    @Getter
    public int drableId;//图片资源ID

    public MenuItem(int title,int drableId,String jumpId){
        this.title=title;
        this.drableId=drableId;
        this.jumpId=jumpId;
    }

}
