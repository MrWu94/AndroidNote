package com.hansheng.studynote.SQLDataBase.SQLiteDBMultiTbl.data.model;

/**
 * Created by Tan on 1/26/2016.
 */
public class Course {

    public static final String TAG = Course.class.getSimpleName();
    public static final String TABLE = "Course";
    // Labels Table Columns names
    public static final String KEY_CourseId = "CourseId";
    public static final String KEY_Name = "Name";

    private String courseId;
    private String name;


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
