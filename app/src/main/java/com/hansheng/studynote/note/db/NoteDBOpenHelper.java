package com.hansheng.studynote.note.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hansheng on 2016/7/24.
 */
public class NoteDBOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "note";
    public static final int VERSION = 1;
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String TIME = "time";
    public static final String ID = "_id";

    public NoteDBOpenHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " ("
//                + ID + " integer primary key autoincrement  ,"
//                + CONTENT + " TEXT NOT NULL,"
//                + TITLE + " TEXT,"
//                + TIME + " TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS note (_id integer primary key autoincrement, " +
                "title TEXT NOT NULL, content TEXT NOT NULL, time TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}