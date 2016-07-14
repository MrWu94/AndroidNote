package com.hansheng.GenericImpl;

/**
 * Created by hansheng on 2016/7/14.
 */
public class Introduction implements Info{
    private String name;                // 姓名
    private String sex;                 // 性别
    private int age;                        // 年龄

    @Override
    public String toString() {
        return "Introduction{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public Introduction(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
