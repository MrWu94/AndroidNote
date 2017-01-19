package com.hansheng.studynote.SQLDataBase.PrimarySqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.SQLDataBase.searchview.SearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_main_primary);


//        SQLiteDatabase s= openOrCreateDatabase("mytest.db",MODE_PRIVATE,null);

//        SQLiteDatabase.openOrCreateDatabase()
    }

    /**
     * Android Api创建SQLite数据库的三种方式
     *
     * @param view
     */
    public void createDataBase(View view) {
        startActivity(new Intent(this, CreateOrOpenActivity.class));
    }

    /**
     * Android 通过Sql语句操作数据库
     *
     * @param view
     */
    public void sql(View view) {
        startActivity(new Intent(this, SqlOperateActivity.class));
    }

    /**
     * Android 通过Sql语句操作数据库
     *
     * @param view
     */
    public void api(View view) {
        startActivity(new Intent(this, ApiOperateActivity.class));
    }

    /**
     * SQLite优化
     *
     * @param view
     */
    public void optimize(View view) {
        startActivity(new Intent(this, OptimizeActivity.class));
    }

    /**
     * SQLite应用案例
     *
     * @param view
     */
    public void search(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }





}
