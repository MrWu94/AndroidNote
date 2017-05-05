package com.hansheng.studynote.sqlite.search;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 17-1-19.
 */


public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "booksave";

    // Table Names
    private static final String TABLE_BOOK = "book";

    // column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_BACKGROUND = "background";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_BOOK = "CREATE TABLE "
            + TABLE_BOOK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_NUMBER + " INTEGER," + KEY_BACKGROUND
            + " TEXT" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);

        onCreate(db);
    }

    public long createBook(BookSave booksave) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, booksave.getBookName());
        values.put(KEY_NUMBER, booksave.getBookNumber());
        values.put(KEY_BACKGROUND, booksave.getBackground());
        long id = db.insert(TABLE_BOOK, null, values);
        return id;

    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * 根据id删除一条数据
     *
     * @param id
     * @return
     */
    public long delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long num = db.delete(TABLE_BOOK, KEY_ID + "=?", new String[]{String.valueOf(id)});
        return num;
    }

    /**
     * 根据name查询一条数据
     *
     * @param name
     * @return
     */
    public List<BookSave> qurey(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<BookSave> bookSaves = new ArrayList<>();
        BookSave bookSave = null;
        Cursor cursor = db.query(TABLE_BOOK, null, KEY_NAME + "=?", new String[]{String.valueOf(name)}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                bookSave = new BookSave();
                bookSave.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                bookSave.setBookName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                bookSave.setBookNumber(cursor.getInt(cursor.getColumnIndex(KEY_NUMBER)));
                bookSave.setBackground(cursor.getString(cursor.getColumnIndex(KEY_BACKGROUND)));
                bookSaves.add(bookSave);
            }
        }

        return bookSaves;
    }


}

