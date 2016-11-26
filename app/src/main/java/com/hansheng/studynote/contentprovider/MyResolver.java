package com.hansheng.studynote.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-11-26.
 * <p>
 * ContentProvider（内容提供者）
 * ContentProvider是不同应用程序之间进行数据交换的标准API，只提供数据的访问接口。
 * ContentProvider以某种Uri形式对外提供数据，允许其他应用访问或修改数据，其他应用程序通过ContentResolver根据Uri去访问操作指定数据。
 * ContentResolver（内容观察者）
 * <p>
 * Content提供了如下方法来获取ContentResolver：
 * <p>
 * getContentResolver();
 */

public class MyResolver extends Activity {
    ContentResolver resolver;
    Uri uri = Uri.parse("content://com.hansheng.contentprovider.myprovider/");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        // 获取系统的ContentResolver对象
        resolver = getContentResolver();
    }

    public void query(View source) {
        // 调用ContentResolver的query()方法，实际返回的是该Uri对应的ContentProvider的query()的返回值
        Cursor c = resolver.query(uri, null, "query_where", null, null);
        System.out.println("远程ContentProvider返回的Cursor：" + c);
    }

    public void insert(View source) {
        ContentValues values = new ContentValues();
        values.put("name", "android");
        // 调用ContentResolver的insert()方法，实际返回的是该Uri对应的ContentProvider的insert()的返回值
        Uri newUri = resolver.insert(uri, values);
        System.out.println("远程ContentProvider新插入记录的Uri：" + newUri);
    }

    public void update(View source) {
        ContentValues values = new ContentValues();
        values.put("name", "android");
        // 调用ContentResolver的update()方法，实际返回的是该Uri对应的ContentProvider的update()的返回值
        int count = resolver.update(uri, values, "update_where", null);
        System.out.println("远程ContentProvider更新记录数：" + count);
    }

    public void delete(View source) {
        // 调用ContentResolver的delete()方法，实际返回的是该Uri对应的ContentProvider的delete()的返回值
        int count = resolver.delete(uri, "delete_where", null);
        System.out.println("远程ContentProvider删除记录数：" + count);
    }
}