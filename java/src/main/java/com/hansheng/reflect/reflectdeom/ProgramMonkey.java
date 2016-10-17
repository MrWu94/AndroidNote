package com.hansheng.reflect.reflectdeom;

/**
 * Created by hansheng on 16-10-17.
 */

public class ProgramMonkey extends Person implements ICompany{
    String mLanguage = "C#";
    String mCompany = "BBK";

    public ProgramMonkey(String aName, String aSex, int aAge){
        super(aName, aSex, aAge);
    }

    public ProgramMonkey(String language, String company, String aName, String aSex, int aAge){
        super(aName, aSex, aAge);
        mLanguage = language;
        mCompany = company;
    }

    public String getmLanguage(){
        return mLanguage;
    }

    public void setmLanguage(String mLanguage){
        this.mLanguage = mLanguage;
    }

    private int getSalaryPerMonth(){
        return 12306;
    }

    @Override
    public String getCompany(){
        return mCompany;
    }
}