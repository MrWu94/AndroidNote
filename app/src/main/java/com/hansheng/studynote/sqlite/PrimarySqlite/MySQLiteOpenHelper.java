package com.hansheng.studynote.sqlite.PrimarySqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Android操作使用SQLite的帮助类
 * Created by dylan on 2016-11-19.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private final String TAG = "MySQLiteOpenHelper";

    /**
     * 构造函数
     * 一般用于在这里创建数据库，指定表名和版本号等
     *
     * @param context 上下文对象
     * @param name    数据库名称
     * @param factory 游标工程
     * @param version 数据版本号 >=1
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(TAG, "MySQLiteOpenHelper");
    }

    /**
     * 创建数据库时调用
     * <p>
     * 一般用于在创建数据表
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
    }

    /**
     * 数据库升级更新时调用
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
    }
}
