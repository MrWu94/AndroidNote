package com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.DatabaseManager;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.model.Course;

/**
 * Created by Tan on 1/26/2016.
 */
public class CourseRepo  {

    private Course course;

    public CourseRepo(){

        course= new Course();

    }


    public static String createTable(){
        return "CREATE TABLE " + Course.TABLE  + "("
                + Course.KEY_CourseId  + "   PRIMARY KEY    ,"
                + Course.KEY_Name + " TEXT )";
    }


    public int insert(Course course) {
        int courseId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Course.KEY_CourseId, course.getCourseId());
        values.put(Course.KEY_Name, course.getName());

        // Inserting Row
        courseId=(int)db.insert(Course.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return courseId;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Course.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }






}
