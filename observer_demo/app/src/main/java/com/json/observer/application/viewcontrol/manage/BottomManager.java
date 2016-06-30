package com.json.observer.application.viewcontrol.manage;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.json.observer.R;
import com.json.observer.application.activity.homeActivity.HomeActivity;
import com.json.observer.application.model.bottommenu.MenuItem;
import com.json.observer.application.viewcontrol.activityMap.ActivityID;
import com.json.observer.application.viewcontrol.menu.Menus;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by json on 16/2/1.
 */
public class BottomManager implements Observer {
    private Activity activity;
    private LinearLayout bottom;
    private List<MenuItem> menuItems;
    private Button[] menuButtons;
    private static BottomManager bottomManager=new BottomManager();
    private BottomManager(){

    }
    public static BottomManager getBottomManager(){
        return bottomManager;
    }
    @Override
    public void update(Observable observable, Object data) {

    }

    public void init(HomeActivity homeActivity) {
        this.activity=homeActivity;
        bottom= (LinearLayout) homeActivity.findViewById(R.id.ll_bottom);

        ArrayList<MenuItem> bottomMenuItems=new ArrayList<MenuItem>();
        String menuStr="1-1,1-2,1-3";
        String[] menus = menuStr.split(",");

        for(String menu : menus)
        {
            bottomMenuItems.add(getBottomMenu(menu));
        }

//        MenuItem[] menuItems=bottomMenuItems.toArray(new MenuItem[bottomMenuItems.size()]);
        menuButtons=new Button[bottomMenuItems.size()];
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(1,1,1,1);
        params.weight=1;
        for (int i=0;i<bottomMenuItems.size();i++){
            menuButtons[i]=new Button(activity);
            menuButtons[i].setLayoutParams(params);
            menuButtons[i].setPadding(4, 4, 4, 4);
            menuButtons[i].setTextSize(TypedValue.COMPLEX_UNIT_DIP, Menus.MENU_TEXT_SIZE);
            try {
                menuButtons[i].setTextColor(ColorStateList.createFromXml(activity.getResources(),activity.getResources().getXml(R.drawable.menu_text_color)));
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            menuButtons[i].setOnClickListener(mDefaultMenuListener);
            bottom.addView(menuButtons[i]);
        }

        for (int i=0;i<bottomMenuItems.size();i++){
            MenuItem menuItem=bottomMenuItems.get(i);
            menuButtons[i].setId(menuItem.getTitle());
            menuButtons[i].setCompoundDrawablesWithIntrinsicBounds(0,menuItem.getDrableId(),0,0);
            menuButtons[i].setTag(menuItem);
        }
        menuButtons[0].setSelected(true);
        menuButtons[0].setEnabled(true);
    }

    private MenuItem getBottomMenu(String menu) {
        if(menu.equals(ActivityID.MENU_HOME))
        {
            return Menus.mt_Home;
        }else if(menu.equals(ActivityID.MENU_PERSONAL))
        {
            return Menus.mt_Personal;
        }else if (menu.equals(ActivityID.MENU_PRODUCT)){
            return Menus.mt_Product;
        }
        else{
            return null;
        }
    }

    protected View.OnClickListener mDefaultMenuListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MenuItem menuItem= (MenuItem) v.getTag();
            String jumpId=menuItem.getJumpId();
            UiManager.getInstance().changeView(jumpId,null);
        }
    };
}
