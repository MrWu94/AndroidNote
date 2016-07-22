package com.hansheng.studynote.MutilThreadDownload.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hansheng on 2016/7/21.
 * Range: bytes=0-// 请求内容字节范围
 * Content-Length: 4827684// 这个表示文件大小
 * <p/>
 * Content-Range: bytes 0-4827683/4827684// 文件字节范围
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "eric.db";
    private static final int VERSION = 1;

    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建filedownlog表
        db.execSQL("CREATE TABLE IF NOT EXISTS filedownlog (id integer primary key autoincrement, downpath varchar(100), threadid INTEGER, downlength INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS filedownlog");
        onCreate(db);
    }
}