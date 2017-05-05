package com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.DatabaseManager;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.model.Major;

/**
 * Created by Tan on 1/26/2016.
 */
public class MajorRepo   {

    private Major major;

    public MajorRepo(){

        major= new Major();

    }


    public static String createTable(){
        return "CREATE TABLE " + Major.TABLE  + "("
                + Major.KEY_MajorId + " TEXT  PRIMARY KEY, "
                + Major.KEY_Name + " TEXT )";
    }



    public int insert(Major major) {
        int majorId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Major.KEY_MajorId, major.getMajorId());
        values.put(Major.KEY_Name, major.getName());

        // Inserting Row
        majorId=(int)db.insert(Major.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return majorId;

    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Major.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }








}
