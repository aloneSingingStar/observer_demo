package com.json.observer.application.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.json.observer.application.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by json on 16/2/18.
 */
public class ProductDAO {
    private static ProductDAO emotionInfoDao;
    private Context context;
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase mDb;
    public ProductDAO(Context context){
        this.context=context;
        this.dbOpenHelper=new DBOpenHelper(context);
    }
    //单例模式获得实例
    public static ProductDAO getInstance(Context context){
        if (emotionInfoDao==null){
            synchronized (ProductDAO.class){
                if(emotionInfoDao==null){
                    emotionInfoDao=new ProductDAO(context);
                }
            }
        }
        return emotionInfoDao;
    }

    /**\
     * 查询所有数据
     * @return
     */
    public List<Product> query(){
        List<Product> emotionList=new ArrayList<Product>();
        Product emotion=null;
        String sql="select * from weibo";
        synchronized (Object.class){
            mDb=dbOpenHelper.getWritableDatabase();
            mDb.beginTransaction();
            Cursor cursor=null;
            try {
                cursor=mDb.rawQuery(sql,null);
//            cursor=mDb.rawQuery(sql,params);
                cursor.moveToFirst();
                do{
                    emotion=new Product();
                    emotion.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    emotion.setName(cursor.getString(cursor.getColumnIndex("author")));
                    emotion.setDescription(cursor.getString(cursor.getColumnIndex("content")));
                    emotion.setImgUrl(cursor.getString(cursor.getColumnIndex("imageurl")));
                    emotionList.add(emotion);
                }while (cursor.moveToNext());
                mDb.setTransactionSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                mDb.endTransaction();
                if (cursor!=null){
                    cursor.close();
                }
                //关掉后，再次查询会报错：java.lang.IllegalStateException: attempt to re-open an already-closed object: SQLiteDatabase: /data/data/com.founder.mp3player/files/weibo.db
                // mDb.close();
            }
        }
        return emotionList;
    }

    /**
     * 分页查询数据
     * @param limit
     * @param offset
     * @return
     */
    public List<Product> query(int limit,int offset){
        List<Product> emotionList=new ArrayList<Product>();
        Product emotion=null;
        Cursor cursor=null;
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT * FROM weibo WHERE 1=1 ");
        sql.append("LIMIT "+limit+" OFFSET "+offset);
        synchronized (Object.class){
            mDb=dbOpenHelper.getReadableDatabase();
            mDb.beginTransaction();
            try{
                cursor= mDb.rawQuery(sql.toString(),null);
                cursor.moveToFirst();
                do {
                    emotion=new Product();
                    emotion.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    emotion.setName(cursor.getString(cursor.getColumnIndex("author")));
                    emotion.setDescription(cursor.getString(cursor.getColumnIndex("content")));
                    emotion.setImgUrl(cursor.getString(cursor.getColumnIndex("imageurl")));
                    emotionList.add(emotion);
                }while (cursor.moveToNext());
                mDb.setTransactionSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                mDb.endTransaction();
                if (cursor!=null){
                    cursor.close();
                }
            }
        }
        return emotionList;
    }
    public void insert(Product emotion){
        synchronized (Object.class){
            mDb=dbOpenHelper.getWritableDatabase();
            mDb.execSQL("replace into weibo(id,author,content,imageurl) values(?,?,?,?)",new Object[]{emotion.getId(),emotion.getName(),emotion.getDescription(),emotion.getImgUrl()});
        }
    }
}
