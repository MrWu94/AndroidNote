package com.hansheng.studynote.SQLDataBase.SQLiteDBMultiTbl.model;

/**
 * Created by Tan on 1/28/2016.
 */
public class CourseGradeCount {

    private String courseID;
    private String courseName;
    private String grade;
    private Integer count;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
