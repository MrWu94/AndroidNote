package com.hansheng.studynote.SQLDataBase.PrimarySqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.io.File;

/**
 * 通过Android提供的API操作SQLite
 * Created by Administrator on 2016-11-19.
 */

public class ApiOperateActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("通过Android提供的API操作SQLite");
        setContentView(R.layout.ac_api_operate);
        File dataBaseFile = new File(Environment.getExternalStorageDirectory() + "/sqlite", Contacts.DATABASE_NAME2);
        if (!dataBaseFile.getParentFile().exists()) {
            dataBaseFile.mkdirs();
        }
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dataBaseFile, null);
    }

    /**
     * 1.创建数据表user
     * 表名 user
     * *数据表user表结构字段
     * 主键：id
     * 名字：name
     * 年龄：age:
     *
     * @param v
     */
    public void create(View v) {
        String sql = "CREATE TABLE " +
                "IF NOT EXISTS " +
                "user(" +
                "id Integer PRIMARY KEY AUTOINCREMENT," +
                "name varchar," +
                "age Integer)";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 2.删除数据表user
     *
     * @param v
     */
    public void drop(View v) {
        String sql = "DROP TABLE " +
                "IF EXISTS " +
                "user";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 3.给user表中新增一条数据
     * <p>
     * long insert(String table, String nullColumnHack, ContentValues values)
     * 第一个参数：数据库表名
     * 第二个参数：当values参数为空或者里面没有内容的时候，
     * insert是会失败的(底层数据库不允许插入一个空行)，
     * 为了防止这种情况，要在这里指定一个列名，
     * 到时候如果发现将要插入的行为空行时，
     * 就会将你指定的这个列名的值设为null，然后再向数据库中插入。
     * 第三个参数：要插入的值
     * 返回值：成功操作的行号，错误返回-1
     *
     * @param v
     */
    public void insert(View v) {
        String table = "user";
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "张三");
        contentValues.put("age", 25);
        long num = sqLiteDatabase.insert(table, null, contentValues);
        if (num == -1) {
            Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "成功插入到第" + num + "行", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 4.修改user表中id为2的名字改成“李四”
     * <p>
     * int update(String table, ContentValues values, String whereClause, String[] whereArgs)
     * 第一个参数：表名
     * 第二个参数：所要修改该的字段对应的值
     * 第三个参数：修改的条件字段
     * 第四个参数：修改的条件字段对应的值
     * 返回值：影响的行数
     *
     * @param v
     */
    public void update(View v) {

        String table = "user";
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "李四");
        String where = "id=2";
        /**
         * 方式一
         */
        sqLiteDatabase.update(table, contentValues, where, null);
        /**
         * 方式二
         */
        where = "id=?";
        String[] whereArgs = new String[]{"2"};
        int num = sqLiteDatabase.update(table, contentValues, where, whereArgs);
        Toast.makeText(this, "修改了" + num + "行", Toast.LENGTH_SHORT).show();
    }

    /**
     * 5.删除user表中id为2的记录
     * <p>
     * int delete(String table, String whereClause, String[] whereArgs)
     * 第一个参数：删除的表名
     * 第二个参数：修改的条件的字段
     * 第三个参数：修改的条件字段对应的值
     * 返回值：影响的行数
     *
     * @param v
     */
    public void delete(View v) {
        String table = "user";
        String where = "id=2";
        sqLiteDatabase.delete(table, where, null);
        where = "id=?";
        String[] whereArgs = new String[]{"3"};
        int num = sqLiteDatabase.delete(table, where, whereArgs);
        Toast.makeText(this, "删除了" + num + "行", Toast.LENGTH_SHORT).show();
    }

    /**
     * 6.查询数据
     * <p>
     * Cursor query(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy)
     * 第一个参数：表名
     * 第二个参数：要查询的字段名
     * 第三个参数：要查询的条件字段
     * 第四个参数：要查询的条件字段对应的值
     * 第五个参数：分组的字段
     * 第六个参数：筛选的字段
     * 第七个参数：排序的字段
     * 返回值：游标
     *
     * @param v
     */
    public void query(View v) {
        String table = "user";
        Cursor cursor = sqLiteDatabase.query(table, null, null, null, null, null, null);
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
            Log.d(Contacts.TAG, "id=" + cursor.getInt(0) + "，name=" + cursor.getString(1) + "，age=" + cursor.getInt(2));
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }
}
