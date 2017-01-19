package com.hansheng.studynote.SQLDataBase.searchview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作管理类
 * Created by Administrator on 2016-11-19.
 */

public class DBManager {
    private static volatile DBManager dbManager;
    private SQLiteDatabase sqLiteDatabase;

    private DBManager() {
        openDataBase();
        createTable();
    }

    public static DBManager getDBManager() {
        if (dbManager == null) {
            synchronized (DBManager.class) {
                if (dbManager == null) {
                    dbManager = new DBManager();
                }
            }
        }
        return dbManager;
    }

    /**
     * 数据库名称
     */
    private final String DATABASE_NAME = "info.db";
    /**
     * 表名
     */
    private final String TABLE_NAME = "history";

    /**
     * 表格所包含的字段
     */
    private class HistoryDbColumn {

        /**
         * 字段一 id
         */
        public static final String ID = "id";
        /**
         * 字段二 内容
         */
        public static final String CONTENT = "name";
        /**
         * 字段三 时间
         */
        public static final String TIME = "time";
    }

    /**
     * 1.创建或打开数据库连接
     **/
    private void openDataBase() {
        File dataBaseFile = new File(Environment.getExternalStorageDirectory() + "/sqlite", DATABASE_NAME);
        if (!dataBaseFile.getParentFile().exists()) {
            dataBaseFile.mkdirs();
        }
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dataBaseFile, null);
    }

    /****
     * 2.创建表
     */
    private void createTable() {
        String sql = "CREATE TABLE " +
                "IF NOT EXISTS " +
                TABLE_NAME + "(" +
                HistoryDbColumn.ID + " Integer PRIMARY KEY AUTOINCREMENT," +
                HistoryDbColumn.CONTENT + " varchar," +
                HistoryDbColumn.TIME + " datetime)";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 插入一条数据
     *
     * @param history
     * @return
     */
    public long insert(History history) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HistoryDbColumn.CONTENT, history.getContent());
        contentValues.put(HistoryDbColumn.TIME, history.getTime());
        long num = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return num;
    }

    /**
     * 根据id删除一条数据
     *
     * @param id
     * @return
     */
    public long delete(int id) {
        long num = sqLiteDatabase.delete(TABLE_NAME, HistoryDbColumn.ID + "=?", new String[]{String.valueOf(id)});
        return num;
    }

    /**
     * 根据id修改一条数据
     *
     * @param id
     * @return
     */
    public long update(History history, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HistoryDbColumn.CONTENT, history.getContent());
        contentValues.put(HistoryDbColumn.TIME, history.getTime());
        long num = sqLiteDatabase.update(TABLE_NAME, contentValues, HistoryDbColumn.ID + "=?", new String[]{String.valueOf(id)});
        return num;
    }

    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    public History qurey(int id) {
        History history = null;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, HistoryDbColumn.ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                history = new History();
                history.setId(cursor.getInt(cursor.getColumnIndex(HistoryDbColumn.ID)));
                history.setContent(cursor.getString(cursor.getColumnIndex(HistoryDbColumn.CONTENT)));
                history.setTime(cursor.getString(cursor.getColumnIndex(HistoryDbColumn.TIME)));
            }
        }

        return history;
    }

    /**
     * 根据id查询一条数据
     * 倒序
     *
     * @return
     */
    public List<History> queryAll() {
        List<History> historys = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, HistoryDbColumn.TIME + " desc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                History history = new History();
                history.setId(cursor.getInt(cursor.getColumnIndex(HistoryDbColumn.ID)));
                history.setContent(cursor.getString(cursor.getColumnIndex(HistoryDbColumn.CONTENT)));
                history.setTime(cursor.getString(cursor.getColumnIndex(HistoryDbColumn.TIME)));
                historys.add(history);
            }
        }
        return historys;
    }

    /**
     * 根据内容查询一条数据
     *
     * @return
     */
    public History queryByContent(String content) {
        History history = null;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, HistoryDbColumn.CONTENT + "=?", new String[]{content}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                history = new History();
                history.setId(cursor.getInt(cursor.getColumnIndex(HistoryDbColumn.ID)));
                history.setContent(cursor.getString(cursor.getColumnIndex(HistoryDbColumn.CONTENT)));
                history.setTime(cursor.getString(cursor.getColumnIndex(HistoryDbColumn.TIME)));
            }
        }
        return history;
    }
}
