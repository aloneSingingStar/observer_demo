package com.json.observer.application.base;

import android.app.Application;
import android.os.Environment;

import com.json.observer.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by json on 16/2/1.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        File Mycache= new File(Environment.getExternalStorageDirectory(),"Mycache");
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.arrow_down)
                .cacheInMemory(true)// 1.8.6包使用时候，括号里面传入参数true
                .cacheOnDisc(true)// 1.8.6包使用时候，括号里面传入参数true
                .build();
        ImageLoaderConfiguration config;
        config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .discCache(new UnlimitedDiscCache(Mycache))// 自定义缓存路径
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}
