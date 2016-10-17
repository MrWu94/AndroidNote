package com.hansheng.reflect.reflectdeom;

/**
 * Created by hansheng on 16-10-17.
 */

public class Person{
    String mName;
    String mSex;
    public int mAge;

    public Person(String aName, String aSex, int aAge) {
        mName = aName;
        mSex = aSex;
        mAge = aAge;
    }

    public int getmAge(){
        return mAge;
    }

    public void setmAge(int mAge){
        this.mAge = mAge;
    }

    public String getmName(){
        return mName;
    }

    public void setmName(String mName){
        this.mName = mName;
    }

    public String getmSex(){
        return mSex;
    }

    public void setmSex(String mSex){
        this.mSex = mSex;
    }

    private String getDescription(){
        return "黄种人";
    }
}