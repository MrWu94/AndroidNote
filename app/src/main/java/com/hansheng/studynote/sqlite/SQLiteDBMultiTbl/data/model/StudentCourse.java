package com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.model;

/**
 * Created by Tan on 1/27/2016.
 */
public class StudentCourse {
    public static final String TAG = StudentCourse.class.getSimpleName();
    public static final String TABLE = "StudentCourse";

    // Labels Table Columns names
    public static final String KEY_RunningID = "RunningID";
    public static final String KEY_StudID = "StudentId";
    public static final String KEY_CourseId = "CourseId";
    public static final String KEY_Grade = "Grade";

    public   String studentId;
    public   String courseId;
    public   String grade;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }





}
