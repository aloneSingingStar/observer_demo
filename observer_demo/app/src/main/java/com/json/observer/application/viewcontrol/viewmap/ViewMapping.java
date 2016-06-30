package com.json.observer.application.viewcontrol.viewmap;

import com.json.observer.application.base.viewImpl.HomeView.HomeConfigView;
import com.json.observer.application.base.viewImpl.PersonalView.PerSonalConfigView;
import com.json.observer.application.base.viewImpl.Product.ProductConfigView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by json on 16/2/2.
 */
public class ViewMapping {
    public static final String HOME ="1-1";
    public static final String PERSONAL="1-2";
    public static final String PRODUCT="1-3";
    public static Map<String,ViewStruct> viewmap=new HashMap<String,ViewStruct>();//必须在前面,如果先实例化mapping,进入ViewMapping构造时,viewmap是空的
    private static ViewMapping mapping=new ViewMapping();
    private ViewMapping(){
        viewmap.put(HOME,getViewStruct(HOME));
        viewmap.put(PERSONAL,getViewStruct(PERSONAL));
        viewmap.put(PRODUCT,getViewStruct(PRODUCT));
    }

    private ViewStruct getViewStruct(String id) {
        ViewStruct viewStruct=null;
        if (ViewMapping.HOME.equals(id)){
            viewStruct=new ViewStruct("首页", HomeConfigView.class);
        }else if (ViewMapping.PERSONAL.equals(id)){
            viewStruct=new ViewStruct("个人主页",PerSonalConfigView.class);
        }else if (ViewMapping.PRODUCT.equals(id)){
            viewStruct=new ViewStruct("商品主页",ProductConfigView.class);
        }
        return viewStruct;
    }

    public static ViewMapping getMapping(){
        return mapping;
    }

    public static Map<String,ViewStruct> getViewMap(){
        return viewmap;
    }

}
