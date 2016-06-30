package com.json.observer.application.base.viewImpl.Product;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.json.observer.R;
import com.json.observer.application.adapter.ProductListViewAdapter;
import com.json.observer.application.base.BaseView;
import com.json.observer.application.db.ProductDAO;
import com.json.observer.application.model.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by json on 16/2/15.
 */
public class ProductConfigView extends BaseView {
    private ListViewInScrollView listViewInScrollView;
    List<Product> products=new ArrayList<Product>();
    private ProductDAO productDAO;
    private File cache;
    private int limit=5;
    private int offset=0;
    private int currentPage=0;
    private ProductListViewAdapter productListViewAdapter;
    public ProductConfigView(Context context, String id, Bundle bundle) {
        super(context, id, bundle);
        init();
    }

    private void init() {
        LinearLayout rootview=new LinearLayout(context);
        rootview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        container=(LinearLayout)inflater.inflate(R.layout.product_list_view,rootview);
        listViewInScrollView= (ListViewInScrollView) container.findViewById(R.id.list_product);
        productDAO=new ProductDAO(context);
        cache=new File(Environment.getExternalStorageDirectory(),"AsyncCache");
        if (!cache.exists()){
            cache.mkdirs();
        }
        initData();
    }

    private void initData() {
        products.clear();
        //初始化5条数据
        products.addAll(productDAO.query(limit,offset));
        productListViewAdapter=new ProductListViewAdapter(context,products,cache);
        listViewInScrollView.setAdapter(productListViewAdapter);
    }
}
