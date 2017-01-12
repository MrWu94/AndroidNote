package com.hansheng.studynote.SQLDataBase.PrimarySqlite;

import android.content.ContentValues;
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
 * SQLite优化
 * Created by Administrator on 2016-11-20.
 */

public class OptimizeActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("SQLite优化");
        setContentView(R.layout.ac_optimize);
        openDataBase();

        createTable();

    }

    /**
     * 1.创建或打开数据库连接
     **/
    private void openDataBase() {
        File dataBaseFile = new File(Environment.getExternalStorageDirectory() + "/sqlite", Contacts.DATABASE_NAME3);
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
                "user(" +
                "id Integer PRIMARY KEY AUTOINCREMENT," +
                "name varchar," +
                "age Integer)";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 一般的插入数据，批量插入100条数据
     *
     * @param v
     */
    public void general(View v) {
        long startTime = System.currentTimeMillis();
        String table = "user";
        for (int i = 0; i < 100; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "张三" + i);
            contentValues.put("age", i);
            sqLiteDatabase.insert(table, null, contentValues);
        }
        long endTime = System.currentTimeMillis();
        String message = "总共耗时" + (endTime - startTime);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d(Contacts.TAG, message);
    }

    /**
     * 优化后，批量插入100条数据
     *
     * @param v
     */
    public void optimize(View v) {
        long startTime = System.currentTimeMillis();
        String table = "user";
        /**开启一个事务**/
        sqLiteDatabase.beginTransaction();
        try {
            for (int i = 0; i < 100; i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "张三" + i);
                contentValues.put("age", i);
                sqLiteDatabase.insertOrThrow(table, null, contentValues);
            }
            /**将数据库事务设置为成功**/
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /**结束数据库事务**/
            sqLiteDatabase.endTransaction();
        }
        long endTime = System.currentTimeMillis();
        String message = "总共耗时" + (endTime - startTime);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d(Contacts.TAG, message);
    }
}
