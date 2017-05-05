package com.hansheng.studynote.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-2.
 */

public class SqlActivity extends AppCompatActivity {
    SQLiteDatabase sqldb;
    private static final String TAG = "SqlActivity";
    final DBHelper helper = new DBHelper(this);

    // DbHelper类在DbHelper.java文件里面创建的

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_main);
        sqldb = helper.getWritableDatabase();
        // 通过helper的getWritableDatabase()得到SQLiteOpenHelper所创建的数据库
        Button insert = (Button) findViewById(R.id.insert);
        Button delete = (Button) findViewById(R.id.delete);
        Button update = (Button) findViewById(R.id.update);
        Button query = (Button) findViewById(R.id.query);
        final ContentValues cv = new ContentValues();
        // ContentValues是“添加”和“更新”两个操作的数据载体
        updatelistview();// 更新listview
        // 添加insert
        insert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText et_name = (EditText) findViewById(R.id.name);
                EditText et_phone = (EditText) findViewById(R.id.phone);
                cv.put("name", et_name.getText().toString());
                cv.put("phone", et_phone.getText().toString());
                // name和phone为列名
                long res = sqldb.insert(DBHelper.TABLE_NAME, null, cv);// 插入数据
                if (res == -1) {
                    Log.d(TAG, "添加失败");
                } else {
                    Log.d(TAG, "添加成功: ");
                }
                updatelistview();// 更新listview
            }
        });
        // 删除
        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int res = sqldb.delete(DBHelper.TABLE_NAME, "name='大钟'", null);
                // 删除列名name，行名为“大钟”的，这一行的所有数据，null表示这一行的所有数据
                // 若第二个参数为null，则删除表中所有列对应的所有行的数据，也就是把table清空了。
                // name='大钟'，大钟要单引号的
                // 返回值为删除的行数
                if (res == 0) {
                    Log.d(TAG, "删除失败");
                } else {
                    Log.d(TAG, "删除了" + res + "行的数据");
                }
                updatelistview();// 更新listview

            }
        });
        // 更改
        update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                cv.put("name", "大钟");
                cv.put("phone", "1361234567");
                int res = sqldb.update(DBHelper.TABLE_NAME, cv, "name='张三'", null);
                // 把name=张三所在行的数据，全部更新为ContentValues所对应的数据
                // 返回时为成功更新的行数
                Log.d(TAG, "成功更新了" + res + "行的数据");

                updatelistview();// 更新listview
            }
        });
        // 查询
        query.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor cr = sqldb.query(DBHelper.TABLE_NAME, null, null, null, null,
                        null, null);
                // 返回名为addressbook的表的所有数据
                Log.d(TAG, "一共有" + cr.getCount() + "条记录");

                updatelistview();// 更新listview
            }
        });

    }

    // 更新listview
    public void updatelistview() {
        ListView lv = (ListView) findViewById(R.id.lv);

        final Cursor cr = sqldb.query(DBHelper.TABLE_NAME, null, null, null, null,
                null, null);
        String[] ColumnNames = cr.getColumnNames();
        // ColumnNames为数据库的表的列名，getColumnNames()为得到指定table的所有列名

        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.sql_listview,
                cr, ColumnNames, new int[]{R.id.tv1, R.id.tv2, R.id.tv3});
        // layout为listView的布局文件，包括三个TextView，用来显示三个列名所对应的值
        // ColumnNames为数据库的表的列名
        // 最后一个参数是int[]类型的，为view类型的id，用来显示ColumnNames列名所对应的值。view的类型为TextView
        lv.setAdapter(adapter);
    }
}
