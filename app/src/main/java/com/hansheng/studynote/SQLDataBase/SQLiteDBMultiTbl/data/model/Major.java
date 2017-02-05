package com.hansheng.studynote.SQLDataBase.SQLiteDBMultiTbl.data.model;

/**
 * Created by Tan on 1/26/2016.
 */
public class Major {

    public static final String TAG = Major.class.getSimpleName();
    public static final String TABLE = "Major";
    // Labels Table Columns names
    public static final String KEY_MajorId = "MajorId";
    public static final String KEY_Name = "Name";

    private String majorId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String ref) {
        this.majorId = ref;
    }
}
