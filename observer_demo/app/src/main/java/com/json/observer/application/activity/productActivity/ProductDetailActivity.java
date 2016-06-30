package com.json.observer.application.activity.productActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.json.observer.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by json on 16/2/18.
 */
public class ProductDetailActivity extends Activity{
    private TextView name;
    private TextView des;
    private ImageView url;
    private String productId;
    private String productName;
    private String productUrl;
    private String productDes;

    private TextView tvtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//定制Activity标题栏,顺序不能错
        setContentView(R.layout.product_listview_item);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.top_title);
        tvtitle= (TextView)findViewById(R.id.title_text);
        tvtitle.setText("商品详情");

        name=(TextView)findViewById(R.id.emotion_author);
        des=(TextView)findViewById(R.id.emotion_content);
        url=(ImageView)findViewById(R.id.emotion_image);
        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        productName = intent.getStringExtra("productName");
        productUrl = intent.getStringExtra("productUrl");
        productDes = intent.getStringExtra("productDes");
        name.setText(productName);
        des.setText(productDes);
        ImageLoader.getInstance().displayImage(productUrl,url);
    }
}
