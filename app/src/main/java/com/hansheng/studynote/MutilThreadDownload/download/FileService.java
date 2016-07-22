package com.hansheng.studynote.MutilThreadDownload.download;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hansheng.studynote.MutilThreadDownload.db.DBOpenHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hansheng on 2016/7/21.
 */
public class FileService {

    private DBOpenHelper openHelper;

    public FileService(Context context){
        openHelper=new DBOpenHelper(context);
    }
    /**
     * 获取每条线程已经下载的文件长度
     *
     * @param path
     * @return
     */
    public Map<Integer,Integer> getData(String path){
        SQLiteDatabase db=openHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select threadid, downlength from filedownlog where downpath=?",
                new String[] { path });
        Map<Integer,Integer> data=new HashMap<>();
        while (cursor.moveToNext()){
            data.put(cursor.getInt(0),cursor.getInt(1));
        }
        cursor.close();
        db.close();
        return data;
    }
    /**
     * 保存每条线程已经下载的文件长度
     *
     * @param path
     * @param map
     */
    public void save(String path, Map<Integer,Integer> map){

        SQLiteDatabase db=openHelper.getReadableDatabase();
        db.beginTransaction();

        try {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                db.execSQL(
                        "insert into filedownlog(downpath, threadid, downlength) values(?,?,?)",
                        new Object[] { path, entry.getKey(), entry.getValue() });
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        db.close();
    }
    /**
     * 实时更新每条线程已经下载的文件长度
     *
     * @param path

     */
    public void updata(String path,int threadId,int pos){
        SQLiteDatabase db=openHelper.getWritableDatabase();
        db.execSQL("update filedownlog set downlength=? where downpath=? and threadid=?",
                new Object[] { pos, path, threadId });
    }
    public void delete(String path){
        SQLiteDatabase db=openHelper.getWritableDatabase();
        db.execSQL("delete from filedownlog where downpath=?",
                new Object[] { path });
        db.close();
    }
}
