package com.json.observer.application.viewcontrol.menu;

import com.json.observer.R;
import com.json.observer.application.model.bottommenu.MenuItem;
import com.json.observer.application.viewcontrol.activityMap.ActivityID;

/**
 * Created by json on 16/2/3.
 */

/**
 * 底部菜单
 */
public class Menus{
    /*首页*/
    public static final MenuItem mt_Home=new MenuItem(R.string.menu_home,R.drawable.bottom_menu_home, ActivityID.MENU_HOME);
    /*个人设置*/
    public static final MenuItem mt_Personal=new MenuItem(R.string.menu_personal,R.drawable.bottom_menu_personal,ActivityID.MENU_PERSONAL);
    /*商品主页*/
    public static final MenuItem mt_Product=new MenuItem(R.string.menu_product,R.drawable.bottom_menu_personal,ActivityID.MENU_PRODUCT);
    /*字体大小*/
    public static final int MENU_TEXT_SIZE = 12;
}
