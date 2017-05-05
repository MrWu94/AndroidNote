package com.hansheng.studynote.sqlite.SQLiteDBMultiTbl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hansheng.studynote.R;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.model.Course;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.model.Major;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.model.Student;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.model.StudentCourse;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.repo.CourseRepo;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.repo.MajorRepo;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.repo.StudentCourseRepo;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.data.repo.StudentRepo;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.model.CourseGradeCount;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.model.CourseNotTakenByStudent;
import com.hansheng.studynote.sqlite.SQLiteDBMultiTbl.model.StudentCourseList;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStuCourseGrade,btnCourseNameGradeTotal,btnCourseNotTakenByStudent,btnFail,btnDelete,btnInsert;

    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_student_main);
        btnStuCourseGrade= (Button)findViewById(R.id.btnStuCourseGrade);
        btnStuCourseGrade.setOnClickListener(this);

        btnCourseNameGradeTotal= (Button) findViewById(R.id.btnCourseNameGradeTotal);
        btnCourseNameGradeTotal.setOnClickListener(this);

        btnCourseNotTakenByStudent= (Button) findViewById(R.id.btnCourseNotTakenByStudent);
        btnCourseNotTakenByStudent.setOnClickListener(this);


        btnFail= (Button) findViewById(R.id.btnFail);
        btnFail.setOnClickListener(this);

        btnDelete= (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        btnInsert= (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);

        insertSampleData();



    }

    private void insertSampleData(){

        StudentRepo studentRepo = new StudentRepo();
        CourseRepo courseRepo   = new CourseRepo();
        MajorRepo majorRepo = new MajorRepo();
        StudentCourseRepo studentCourseRepo = new StudentCourseRepo();


        studentCourseRepo.delete();
        majorRepo.delete();
        courseRepo.delete();
        studentRepo.delete();

        //Insert Sample data if the table is empty
        Course course = new Course();

        course.setName("Intro to Computer Info Systems");
        course.setCourseId("CIS11");
        courseRepo.insert(course);

        course.setName("Internet User/Developer");
        course.setCourseId("CIS44");
        courseRepo.insert(course);

        course.setName("Oracle and SQL");
        course.setCourseId("CIS50");
        courseRepo.insert(course);

        course.setName("Visual Basic");
        course.setCourseId("CIS56");
        courseRepo.insert(course);

        course.setName("Intro to Management");
        course.setCourseId("MAN11");
        courseRepo.insert(course);

        course.setName("Marketing Principles");
        course.setCourseId("MAR11");
        courseRepo.insert(course);

        Major major = new Major();

        major.setName("Business Administration");
        major.setMajorId("BU");
        majorRepo.insert(major);

        major.setName("Computer Information Systems");
        major.setMajorId("CI");
        majorRepo.insert(major);

        Student student = new Student();

        student.setStudentId("1111");
        student.setName("Stephen Daniels");
        student.setMajor("BU");
        studentRepo.insert(student);

        student.setStudentId("1212");
        student.setName("Jennifer Ames");
        student.setMajor("CI");
        studentRepo.insert(student);


        student.setStudentId("2222");
        student.setName("Carl Hersey");
        student.setMajor("BU");
        studentRepo.insert(student);


        student.setStudentId("2345");
        student.setName("Mary Stanton");
        student.setMajor("CI");
        studentRepo.insert(student);

        student.setStudentId("3333");
        student.setName("John Richards");
        student.setMajor("CI");
        studentRepo.insert(student);


        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentId("1111");
        studentCourse.setCourseId("CIS11");
        studentCourse.setGrade("A-");
        studentCourseRepo.insert(studentCourse);

        studentCourse.setStudentId("1111");
        studentCourse.setCourseId("MAR11");
        studentCourse.setGrade("A");
        studentCourseRepo.insert(studentCourse);



        studentCourse.setStudentId("1111");
        studentCourse.setCourseId("CIS44");
        studentCourse.setGrade("A");
        studentCourseRepo.insert(studentCourse);

        studentCourse.setStudentId("1212");
        studentCourse.setCourseId("CIS44");
        studentCourse.setGrade("A");
        studentCourseRepo.insert(studentCourse);


        studentCourse.setStudentId("2222");
        studentCourse.setCourseId("CIS44");
        studentCourse.setGrade("A");
        studentCourseRepo.insert(studentCourse);


        studentCourse.setStudentId("2222");
        studentCourse.setCourseId("MAN11");
        studentCourse.setGrade("A-");
        studentCourseRepo.insert(studentCourse);


        studentCourse.setStudentId("3333");
        studentCourse.setCourseId("CIS44");
        studentCourse.setGrade("B");
        studentCourseRepo.insert(studentCourse);

        studentCourse.setStudentId("3333");
        studentCourse.setCourseId("CIS44");
        studentCourse.setGrade("B");
        studentCourseRepo.insert(studentCourse);


        studentCourse.setStudentId("3333");
        studentCourse.setCourseId("CIS50");
        studentCourse.setGrade("B+");
        studentCourseRepo.insert(studentCourse);


        studentCourse.setStudentId("3333");
        studentCourse.setCourseId("CIS56");
        studentCourse.setGrade("A-");
        studentCourseRepo.insert(studentCourse);



        studentCourse.setStudentId("2345");
        studentCourse.setCourseId("CIS50");
        studentCourse.setGrade("I");
        studentCourseRepo.insert(studentCourse);






    }

    private void ListStudentWithCourseNameAndGrade(){

        StudentCourseRepo studentCourseRepo = new StudentCourseRepo();
        List<StudentCourseList> studentCourseLists= studentCourseRepo.getStudentCourse();

        Log.d(TAG,String.format("%-11s", "Student ID") +
                String.format("%-35s", "Student Name") +
                String.format("%-7s", "Course") +
                String.format("%-31s", "Course Name") +
                String.format("%-6s", "Grade") +
                String.format("%-6s", "Major") +
                String.format("%-35s", "Major Name")
        );

        Log.d(TAG,"=============================================================");
        for (int i= 0; i< studentCourseLists.size();i++ ){
            Log.d(TAG, "0000000000".substring( studentCourseLists.get(i).getStudentId().length())+ studentCourseLists.get(i).getStudentId() +
                    " " + String.format("%-35s", studentCourseLists.get(i).getStudentName())+
            String.format("%-7s", studentCourseLists.get(i).getCourseID())+
            String.format("%-31s", studentCourseLists.get(i).getCourseName())+
            String.format("%-6s", studentCourseLists.get(i).getGrade())+
            String.format("%-6s", studentCourseLists.get(i).getMajorId())+
            String.format("%-35s", studentCourseLists.get(i).getMajorName())


            );


        }
        Log.d(TAG,"=============================================================");
    }

    private void ListCourseNameAndGradeCount(){

        StudentCourseRepo studentCourseRepo = new StudentCourseRepo();
        List<CourseGradeCount> courseGradeCounts= studentCourseRepo.getCourseGradeCount();

        Log.d(TAG,String.format("%-7s", "Course") +
                String.format("%-31s", "Course Name") +
                String.format("%-6s", "Grade") +
                String.format("%-5s", "Total") );
        Log.d(TAG,"=============================================================");
        for (int i= 0; i< courseGradeCounts.size();i++ ){
            Log.d(TAG,String.format("%-7s", courseGradeCounts.get(i).getCourseID())+
                    String.format("%-31s", courseGradeCounts.get(i).getCourseName()) +
                    String.format("%-6s", courseGradeCounts.get(i).getGrade()) +
                    String.format("%-5s", courseGradeCounts.get(i).getCount()));

        }
        Log.d(TAG,"=============================================================");

    }

    private void ListCourseNotTakenByStudent(){

        StudentCourseRepo studentCourseRepo = new StudentCourseRepo();
        List<CourseNotTakenByStudent> courseGradeCounts= studentCourseRepo.getCourseNotTakenByStudent("1212");

        Log.d(TAG,"Course Not taken By Student ID = 1212 (Jennifer Ames )") ;
        Log.d(TAG, String.format("%-7s", "Course") +
                String.format("%-31s", "Course Name"));
        Log.d(TAG,"=============================================================");
        for (int i= 0; i< courseGradeCounts.size();i++ ){
            Log.d(TAG,String.format("%-7s", courseGradeCounts.get(i).getCourseID())+
                    String.format("%-31s", courseGradeCounts.get(i).getCourseName()));
        }
        Log.d(TAG,"=============================================================");

    }

    private void failAllBUStudent(){
        StudentCourseRepo studentCourseRepo = new StudentCourseRepo();
        studentCourseRepo.failALLBUStudent();

    }

    private void deleteAllBUStudent(){
        StudentCourseRepo studentCourseRepo = new StudentCourseRepo();
        studentCourseRepo.deleteAllBUStudent();

    }
 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view ==findViewById(R.id.btnStuCourseGrade)){
            ListStudentWithCourseNameAndGrade();
        }else if (view ==findViewById(R.id.btnCourseNameGradeTotal)){
            ListCourseNameAndGradeCount();
        }else if (view ==findViewById(R.id.btnCourseNotTakenByStudent)){
            ListCourseNotTakenByStudent();
        }else if (view ==findViewById(R.id.btnFail)){
           failAllBUStudent();
        }else if (view ==findViewById(R.id.btnDelete)) {
            deleteAllBUStudent();
        }else if (view ==findViewById(R.id.btnInsert)) {
            insertSampleData();
        }
    }
}
