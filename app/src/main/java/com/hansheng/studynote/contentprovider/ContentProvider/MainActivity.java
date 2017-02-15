package com.hansheng.studynote.contentprovider.ContentProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-2-15.
 */


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private Button button1, button2, button3, button4, button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_main);
        initComponent();
        // 创建数据库
        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DBOpenHelper helper = new DBOpenHelper(MainActivity.this);
                // 调用 getWritableDatabase()或者 getReadableDatabase()其中一个方法将数据库建立
                helper.getWritableDatabase();
            }
        });

        // 插入数据
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity.this.insert();
            }
        });

        // 删除数据
        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity.this.delete();
            }
        });

        // 修改数据
        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity.this.update();
            }
        });

        // 查询数据
        button5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity.this.query();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initComponent(){
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
    }

    private void insert(){
        // 访问内容提供者的步骤:
        // 1. 需要一个内容解析者
        ContentResolver contentResolver = MainActivity.this.getContentResolver();
        /*
         * URI的构成
         * 它是通过 Uri.parse("content://")方法来构成的
         * 构成结构是 ："content://" + "授权路径"  + "/" + "标识符 "
         */
        Uri uri = Uri.parse("content://com.android.contentproviderdemo.StudentProvider/student");
        ContentValues values = new ContentValues();
        values.put("name", "AHuier");
        values.put("address", "XIAMEN");
        contentResolver.insert(uri, values);
    }

    private void delete() {
        ContentResolver contentResolver = MainActivity.this.getContentResolver();
        // 删除单条记录，如果要删除多行记录 ：content://com.android.contentproviderdemo.StudentProvider/student
        /*
         * 1 表示当前删除 id = 1的这条记录，这个 id = 1 会传递到 StudentProvider 中去匹配URI规则后解析出 ID ，然后对其进行执行SQL的语句
         */
        Uri uri = Uri.
                parse("content://com.android.contentproviderdemo.StudentProvider/student/1");
        contentResolver.delete(uri, null, null);
    }

    private void update() {
        ContentResolver contentResolver = MainActivity.this.getContentResolver();
        Uri uri = Uri.
                parse("content://com.android.contentproviderdemo.StudentProvider/student/2");
        ContentValues values = new ContentValues();
        values.put("name", "HUI");
        values.put("address", "Beijing");
        contentResolver.update(uri, values, null, null);
    }

    //查询的结果是一个游标，也就是返回的查询记录，查询可能返回一条记录，也可能返回多条记录
    private void query() {
        ContentResolver contentResolver = MainActivity.this.getContentResolver();
        // 查询单条记录 : content://com.android.contentproviderdemo.StudentProvider/student/2
        // 查询多条记录 : content://com.android.contentproviderdemo.StudentProvider/student
        Uri uri = Uri.parse("content://com.android.contentproviderdemo.StudentProvider/student/2");
        // select * from student where id = 2;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while(cursor.moveToNext()){
            Log.i(TAG, "ahuier---->" + cursor.getString(cursor.getColumnIndex("name")));
        }
    }
}