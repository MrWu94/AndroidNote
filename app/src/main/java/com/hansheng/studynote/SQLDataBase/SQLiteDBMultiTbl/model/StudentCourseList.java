package com.hansheng.studynote.SQLDataBase.SQLiteDBMultiTbl.model;

/**
 * Created by Tan on 1/27/2016.
 */
public class StudentCourseList {
    private String studentId;
    private String studentName;
    private String courseID;
    private String courseName;
    private String MajorId;
    private String MajorName;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String grade;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getMajorId() {
        return MajorId;
    }

    public void setMajorId(String majorId) {
        MajorId = majorId;
    }

    public String getMajorName() {
        return MajorName;
    }

    public void setMajorName(String majorName) {
        MajorName = majorName;
    }
}
