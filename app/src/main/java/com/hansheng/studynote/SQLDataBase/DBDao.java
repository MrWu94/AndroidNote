package com.hansheng.studynote.SQLDataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 17-1-9.
 */

public class DBDao {

    private static final String TAG = "DBDao";

    private Context context;
    private DBHelper dbHelper;


    private final String[] ORDER_COLUMNS = new String[]{"_id", "name", "phone"};

    public DBDao(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);

    }

    private boolean idDataExit() {
        int count = 0;

        SQLiteDatabase db = null;

        Cursor cursor = null;

        try {

            db = dbHelper.getReadableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME, new String[]{"COUNT(ID)"}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        } catch (Exception e) {
            Log.d(TAG, "idDataExit: ");
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }


    public void initTable() {
        SQLiteDatabase db = null;
        try {db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (_id,name,phone) values (1,'三国','施老')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (_id,name,phone) values (2,'西游记','施')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (_id,name,phone) values (3,'孙悟空','施')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (_id,name,phone) values (4,'猪八戒','施')");
            db.execSQL("insert into " + DBHelper.TABLE_NAME + " (_id,name,phone) values (5,'沙和尚','施')");

            db.setTransactionSuccessful();
            Log.d(TAG, "initTable: ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }
    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(dbHelper.TABLE_NAME, new String[]{"COUNT(_id)"}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }
    public List<Book> getBook(String name) {
        SQLiteDatabase db = null;

        Cursor cursor = null;
        try {

            db = dbHelper.getReadableDatabase();
            cursor = db.query(DBHelper.TABLE_NAME, ORDER_COLUMNS, "name=?",
                    new String[]{name}, null, null, null
            );

            if (cursor.getCount() > 0) {
                List<Book> bookList = new ArrayList<Book>(cursor.getCount());
                while (cursor.moveToFirst()) {
                    Book book = parseOrder(cursor);
                    bookList.add(book);

                }
                return bookList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return null;
    }

    private Book parseOrder(Cursor cursor) {

        Book book = new Book();
        book.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        book.setName(cursor.getString(cursor.getColumnIndex("name")));
        book.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
        return book;
    }
}
