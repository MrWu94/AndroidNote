package com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.model;

/**
 * Created by Tan on 1/28/2016.
 */
public class CourseNotTakenByStudent {
    private String courseID;
    private String courseName;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
