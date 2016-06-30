package com.json.observer.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.json.observer.R;
import com.json.observer.application.activity.productActivity.ProductDetailActivity;
import com.json.observer.application.async.AsyncCuslistViewImageLoader;
import com.json.observer.application.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thinkive.mobile.account.open.App;
import com.thinkive.mobile.account.open.LauncherActivity;
import com.thinkive.mobile.account.open.OpenAccountActivity;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public   class ProductListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Product> info=null;
    private Map<Integer,View> rowViews=new HashMap<Integer,View>();
    private AsyncCuslistViewImageLoader imageLoader=new AsyncCuslistViewImageLoader();
    private File cache;
    public ProductListViewAdapter(Context context, List<Product> info, File cache) {
        this.context = context;
        this.info=info;
        this.cache=cache;
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public Object getItem(int position) {
        return info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("postion-------------->", String.valueOf(position));
        //相反
        ViewHolder itemView;
        if (convertView==null){
            itemView=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.product_listview_item,null);
            itemView.authorview=(TextView)convertView.findViewById(R.id.emotion_author);
            itemView.contentview=(TextView)convertView.findViewById(R.id.emotion_content);
            itemView.imageview=(ImageView)convertView.findViewById(R.id.emotion_image);
            convertView.setTag(itemView);
        }else {
            itemView=(ViewHolder)convertView.getTag();
            itemView.resetViewHolder();
        }
            itemView.authorview.setText(info.get(position).getName());
            itemView.contentview.setText(info.get(position).getDescription());
            String imageUrl=info.get(position).getImgUrl();
        Log.i("ImageLoader", "imageUrl:" + info.get(position).getName() + ":-->" + imageUrl);
        //实现方式1：使用ImageLoader框架加载图片，在AndroidManifest.xml中配置:<application android:name=".application.MyApplication"
        ImageLoader.getInstance().displayImage(imageUrl, itemView.imageview);
      //实现方式2:使用异步加载图片
//      asyncImageLoad(imageUrl,itemView.imageview);

        Product product=info.get(position);
        convertView.setOnClickListener(new OnItemClickListener(product));
        return convertView;
    }

    /**
     * 异步加载图片
     * @param imageUrl
     * @param imageview
     */
    private void asyncImageLoad(String imageUrl, ImageView imageview) {
        AsyncImageTask asyncImageTask=new AsyncImageTask(imageview);
        asyncImageTask.execute(imageUrl);
    }

    private class ViewHolder {
        public TextView authorview;
        public TextView contentview;
        public ImageView imageview;
        private void resetViewHolder() {
            this.imageview.setImageDrawable(null);
            this.authorview.setText("");
            this.contentview.setText("");
        }
    }

    private final class AsyncImageTask extends AsyncTask<String, Integer, Uri> {
        private ImageView imageview;
        private AsyncImageTask(ImageView imageview) {
            this.imageview=imageview;
        }

        @Override
        protected Uri doInBackground(String... params) {
            return imageLoader.loadDrawable(params[0],cache);
        }

        @Override
        protected void onPostExecute(Uri uri) {
            if (uri!=null&&imageview!=null){
                imageview.setImageURI(uri);
            }
            super.onPostExecute(uri);
        }
    }

    class OnItemClickListener implements View.OnClickListener{

        private Product product;

        public OnItemClickListener(Product product){
            this.product = product;
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent();
//            intent.putExtra("productId", product.getId());
//            intent.putExtra("productName", product.getName());
//            intent.putExtra("productUrl", product.getImgUrl());
//            intent.putExtra("productDes", product.getDescription());
//            intent.setClass(context, ProductDetailActivity.class);
//            context.startActivity(intent);

            Intent intent = new Intent();
            intent.putExtra("productId", product.getId());
            intent.putExtra("productName", product.getName());
            intent.putExtra("productUrl", product.getImgUrl());
            intent.putExtra("productDes", product.getDescription());
            intent.setClass(context, LauncherActivity.class);
            context.startActivity(intent);

        }
    }
}
