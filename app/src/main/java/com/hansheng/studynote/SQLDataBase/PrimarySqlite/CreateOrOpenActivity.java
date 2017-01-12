package com.hansheng.studynote.SQLDataBase.PrimarySqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.hansheng.studynote.R;

import java.io.File;
import java.util.List;

/**
 * 创建或打开数据库的三种方式
 * Created by Administrator on 2016-11-19.
 */

public class CreateOrOpenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("创建或打开数据库的三种方式");
        setContentView(R.layout.ac_create_or_open);
    }

    /**
     * 第一种方式：继承SQLiteOpenHelper打开或创建数据库
     * 特点：可以在升级数据库版本的时候在回调函数里面做相应的操作
     *
     * @param v
     */
    public void sQLiteOpenHelper(View v) {
        /**指定数据库的表名为info.db，版本号为1**/
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this, Contacts.DATABASE_NAME, null, Contacts.DATABASE_VERSION);
        /**得到一个可写的数据库SQLiteDatabase对象**/
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Log.d(Contacts.TAG, sqLiteDatabase.getPath());
        /**查看改对象所创建的数据库**/
        showDataBase(sqLiteDatabase);
    }

    /**
     * 第二种方式：Context.openOrCreateDatabase打开或创建数据库
     * 特点：可以指定数据库文件的操作模式
     *
     * @param v
     */
    public void context(View v) {
        /**指定数据库的名称为info2.db,并指定数据文件的操作模式为MODE_PRIVATE**/
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase(Contacts.DATABASE_NAME2, MODE_PRIVATE, null);
        /**查看改对象所创建的数据库**/
        showDataBase(sqLiteDatabase);
    }

    /**
     * 第三种方式：SQLiteDatabase.openOrCreateDatabase打开或创建数据库
     * 特点：可以指定数据库文件的路径
     *
     * @param v
     */
    public void sQLiteDatabase(View v) {
        File dataBaseFile = new File(Environment.getExternalStorageDirectory() + "/sqlite", Contacts.DATABASE_NAME3);
        if (!dataBaseFile.getParentFile().exists()) {
            dataBaseFile.mkdirs();
        }
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dataBaseFile, null);
        showDataBase(sqLiteDatabase);
    }

    /**
     * 查看手机中由SQLiteDatabase创建的的数据库文件
     */
    public void showDataBase(SQLiteDatabase sqLiteDatabase) {
        List<Pair<String, String>> ll = sqLiteDatabase.getAttachedDbs();
        for (int i = 0; i < ll.size(); i++) {
            Pair<String, String> p = ll.get(i);
            Log.d(Contacts.TAG, p.first + "=" + p.second);
        }
    }
}
