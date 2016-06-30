package com.json.observer.application.async;

import android.net.Uri;

import com.json.observer.application.utils.MD5Encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class AsyncCuslistViewImageLoader {
    private Uri imageUri=null;


    private Uri loadImageFromUrl(String imageUrl, File localfile) throws Exception {
        HttpURLConnection connection=(HttpURLConnection)new URL(imageUrl).openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode()==200){
            FileOutputStream fileOutputStream=new FileOutputStream(localfile);
            InputStream inputStream=connection.getInputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            while ((len=inputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,len);
            }
            fileOutputStream.close();
            inputStream.close();
        }
        return Uri.fromFile(localfile);
    }

    /**
     * 在子线程中执行的异步下载图像任务
     * @param imageUrl
     * @param cachedir
     * @return
     */
    public Uri loadDrawable(final String imageUrl, final File cachedir){
        //判断缓存中是否存在,存在则从缓存中取出
        File localfile=new File(cachedir, MD5Encrypt.getEncryptedPwd(imageUrl)+imageUrl.substring(imageUrl.lastIndexOf(".")));
        if (localfile.exists()){
            return Uri.fromFile(localfile);
        }else {
            //如果缓存中不存在的话，根据Url下载图片
            try {
                imageUri=loadImageFromUrl(imageUrl,localfile);
                return imageUri;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
