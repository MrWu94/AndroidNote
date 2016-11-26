package com.hansheng.studynote.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by hansheng on 16-11-26.
 */

public class MyProvider extends ContentProvider {
    // 第一次创建调用
    @Override
    public boolean onCreate() {
        System.out.println("onCreate");
        return true;
    }
    // 返回值表示该ContentProvider所提供数据的MIME类型
    @Override
    public String getType(Uri uri) {
        System.out.println("getType");
        return null;
    }
    // 查询方法，返回查询得到的Cursor
    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] whereArgs, String sortOrder) {
        System.out.println(uri + "-query");
        System.out.println("where参数：" + where);
        return null;
    }
    // 插入方法，返回新插入记录的Uri
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        System.out.println(uri + "-insert");
        System.out.println("values参数：" + values);
        return null;
    }
    // 删除方法，返回被删除的记录条数
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        System.out.println(uri + "-delete");
        System.out.println("where参数：" + where);
        return 0;
    }
    // 删除方法，返回被更新的记录条数
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        System.out.println(uri + "-update");
        System.out.println("where参数：" + where + ",values参数：" + values);
        return 0;
    }
}

