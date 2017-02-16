package com.hansheng.studynote.contentprovider.ContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hansheng on 17-2-15.
 */


public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBOpenHelper";
    private static String name = "mydb.db";
    private static int version = 1; // 初始版本号是一
    String sql = "create table student (id integer primary key autoincrement, name varchar(64), address varchar(64))";

    public DBOpenHelper(Context context) {
        super(context, name, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // TODO Auto-generated method stub

        database.execSQL(sql); //对数据库的表的创建
        Log.i(TAG, "ahuier--> SQLite create succeed!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS student ");

    }

}