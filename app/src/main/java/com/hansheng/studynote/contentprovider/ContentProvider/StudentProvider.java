package com.hansheng.studynote.contentprovider.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by hansheng on 17-2-15.
 */

public class StudentProvider extends ContentProvider {

    private final String TAG = "DBOpenHelper";
    private DBOpenHelper helper = null;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    /**
     * 这里为何要做这种 操作单条记录 或者 操作多条记录的标志位呢?
     * 原因是因为 外部程序 操作 ContentProvider 的方法，仅仅只能通过一个 URI 来访问，
     * 所以要定义两个标志位来识别外部需要操作的是单条记录还是多条记录 (比如删除单条记录或者删除多条记录)
     */
    private static final int STUDENT = 1; // 操作单条记录
    private static final int STUDENTS = 2; // 操作多条记录

    // 添加对外部的匹配规则
    static {
        URI_MATCHER.addURI("com.android.contentproviderdemo.StudentProvider",
                "student", STUDENTS);
        URI_MATCHER.addURI("com.android.contentproviderdemo.StudentProvider",
                "student/#", STUDENT);
    }

    public StudentProvider() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onCreate() {
        // 初始化的时候实例化 helper 对象
        helper = new DBOpenHelper(getContext());
        return true;
    }

    // query() 方法返回的是一个 Cursor 的游标,详细方法参考 Android API文档
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor cursor = null;
        try {
            SQLiteDatabase databse = helper.getReadableDatabase();
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case STUDENT:
                    long id = ContentUris.parseId(uri);
                    String where_value = " id = " + id;
                    if (selection != null && !selection.equals("")) {
                        where_value += " and " + selection;
                    }
                    // 这边具体查询方式如果有不懂，可以参考前面即将 SQLite的查询。
                    cursor = databse.query("student", null, where_value, selectionArgs, null, null,
                            null, null);
                    break;

                case STUDENTS:
                    cursor = databse.query("student", null, selection, selectionArgs, null, null,
                            null, null);
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return cursor;
    }

    /**
     * 它的作用是根据URI返回该URI所对应的数据的MIME类型字符串。
     * 这个MIME类型字符串的作用是要匹配AndroidManifest.xml文件
     * <activity>标签下<intent-filter>标签的子标签<data>的属性 android:mimeType。
     * 如果不一致，则会导致对应的Activity无法启动。
     */
    @Override
    public String getType(Uri uri) {
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case STUDENT:
                return "vnd.android.cursor.item";
            case STUDENTS:
                return "vnd.android.cursor.dir/";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri resultUri = null;
        /**
         * URI_MATCHER.match(uri) 上面已经在定义了匹配规则，所以这里是用外部传过来的URI匹配内部定义好的规则，
         * 如果匹配成功则进行操作,反之不进行操作。
         */
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case STUDENTS:
                SQLiteDatabase database = helper.getWritableDatabase();
                // 注意这边我们使用的是数据库的 database.insert()方法，所以要用 withAppendedId()
                // 这种方式来返回URI
                long id = database.insert("student", null, values); // 插入当前行的行号
                resultUri = ContentUris.withAppendedId(uri, id);
                break;
        }
        Log.i(TAG, "ahuier----->" + resultUri.toString());
        // 返回新插入选项的URI，可以给其他用户去使用
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = -1; // count作为影响数据库的行数
        try {
            int flag = URI_MATCHER.match(uri);
            SQLiteDatabase database = helper.getWritableDatabase();
            switch (flag) {
                case STUDENT:
                    /**
                     * 传递过来的URI的格式：content://com.android.contentproviderdemo.StudentProvider/student/1
                     * 以下代码其实就是用来构建 SQL 中删除单挑记录的语句
                     * delete from student where id = ? // id 通过客户端传递过来的。
                     */
                    long id = ContentUris.parseId(uri); // 解析出 URI 末尾的ID号
                    String where_value = " id = " + id;
                    if (selection != null && !selection.equals("")) {
                        where_value += " and " + selection;
                    }
                    // 注意这边的count的是SQLite中的delete()方法,返回的是一个影响数据库的数目
                    count = database.delete("student", where_value, selectionArgs);
                    break;

                case STUDENTS:
                    // 传递过来的 URI格式：content://com.android.contentproviderdemo.StudentProvider/student
                    // 删除多条记录
                    count = database.delete("studuent", selection, selectionArgs);
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return count;
    }

    // 更新的方法与删除方法类似，读者可以自己去查 Android官方文档
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = -1;
        try {
            // 更新数据库的语句 ： update table set name = ?, address = ? where id = ?
            SQLiteDatabase database = helper.getWritableDatabase();
            long id = ContentUris.parseId(uri);
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case STUDENT:
                    String where_value = " id = " + id;
                    if (selection != null && !selection.equals("")) {
                        where_value += " and " + selection;
                    }
                    count = database.update("student", values, where_value, selectionArgs);
                    break;

                case STUDENTS:
                    // TODO 这里一般情况下, 不会去更新全部表格
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return count;
    }
}
