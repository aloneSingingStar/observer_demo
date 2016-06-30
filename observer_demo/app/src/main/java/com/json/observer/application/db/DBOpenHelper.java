package com.json.observer.application.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.json.observer.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by json on 16/2/18.
 */
public class DBOpenHelper {
    //上下文对象
    private Context mcontext;
    //数据库手机中存放的位置的路径 /data/data/%s%/files/
    private String mFilesPath;
    //数据库在手机中存放的名称
    private String mDbName;
    //手机中的数据库文件对象
    private File mDbFile=null;
    //数据库对象
    private static SQLiteDatabase mDb=null;

    public DBOpenHelper(Context mcontext) {
        this.mcontext = mcontext;
        //获得数据库文件在手机中的存储位置
        mFilesPath=mcontext.getFilesDir().getAbsolutePath();
        mDbName="weibo.db";
    }

    /**
     * 获得SQLiteDatabase实例
     * @return
     */
    private SQLiteDatabase getDBInstance(){
        mDbFile=new File(mFilesPath,mDbName);
        //手机存储位置中数据库文件存在
        if (mDbFile.exists()){
            if (mDb==null){
                mDb= SQLiteDatabase.openOrCreateDatabase(mDbFile, null);
            }
            return mDb;
            //
        }else {
            File path=new File(mFilesPath);
            path.mkdirs();
            copyDBToSystemDocumentFromRaw();
            return getDBInstance();
        }
    }

    /**
     * 将数据库文件从raw文件夹中复制到file中
     */
    private void copyDBToSystemDocumentFromRaw() {
        FileOutputStream outputStream=null;
        InputStream is=null;
        byte[] buffer=new byte[1024];
        int len=-1;
        try {
            outputStream=new FileOutputStream(mDbFile);
            //读取res/raw下的文件资源，通过以下方式获取输入流来进行写操作
            is=mcontext.getResources().openRawResource(R.raw.weibo);
            while ((len=is.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SQLiteDatabase getWritableDatabase(){
        synchronized (Object.class){
            if (mDb!=null){
                return mDb;
            }else {
                return getDBInstance();
            }
        }
    }

    public SQLiteDatabase getReadableDatabase() {
        synchronized (Object.class){
            if(mDb != null){
                return  mDb;
            } else {
                return getDBInstance();
            }
        }
    }
}
