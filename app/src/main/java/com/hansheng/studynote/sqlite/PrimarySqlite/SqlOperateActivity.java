package com.hansheng.studynote.sqlite.PrimarySqlite;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hansheng.studynote.R;

import java.io.File;

/**
 * 通过sql语句操作SQLite
 * Created by Administrator on 2016-11-19.
 */

public class SqlOperateActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("通过sql语句操作SQLite");
        setContentView(R.layout.ac_sql_operate);
        File dataBaseFile = new File(Environment.getExternalStorageDirectory() + "/sqlite", Contacts.DATABASE_NAME);
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
     *
     * @param v
     */
    public void insert(View v) {
        String sql = "INSERT INTO" +
                " user(name,age) " +
                " VALUES('张三',25)";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 4.修改user表中id为2的名字改成“李四”
     *
     * @param v
     */
    public void update(View v) {
        String sql = "UPDATE" +
                " user SET" +
                " name='李四' " +
                " WHERE id=2";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 5.删除user表中id为2的记录
     *
     * @param v
     */
    public void delete(View v) {
        String sql = "DELETE FROM user WHERE id=2";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 6.查询数据
     *
     * @param v
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void query(View v) {
        String sql = "SELECT * FROM user";
        /***这里得到的是一个游标*/
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null, null);
        if (cursor == null) {
            return;
        }
        /***循环游标得到数据*/
        while (cursor.moveToNext()) {
            Log.d(Contacts.TAG, "id=" + cursor.getInt(0) + "，name=" + cursor.getString(1) + "，age=" + cursor.getInt(2));
        }
        /***记得操作完将游标关闭*/
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sqLiteDatabase!=null&&sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }
}
