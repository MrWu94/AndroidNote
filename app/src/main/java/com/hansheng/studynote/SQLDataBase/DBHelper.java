package com.hansheng.studynote.SQLDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hansheng on 16-12-2.
 */

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE  TABLE addressbook (_id INTEGER PRIMARY KEY , name VARCHAR, phone VARCHAR)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
