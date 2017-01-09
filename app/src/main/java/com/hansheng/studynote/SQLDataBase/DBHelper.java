package com.hansheng.studynote.SQLDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hansheng on 16-12-2.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "sql.db";

    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "addressbook";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE  TABLE IF NOT EXISTS" + TABLE_NAME + "(_id INTEGER PRIMARY KEY , name VARCHAR, phone VARCHAR)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
}
