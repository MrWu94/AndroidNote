package com.hansheng.GenericImpl;

/**
 * Created by hansheng on 2016/7/14.
 */
public class GenericImpl {
    public static void main(String... args){
            Person<Contact> person=new Person<Contact>(new Contact("guangdong","10086","10000"));
            System.out.println(person.getInfo());

            Person<Introduction> person1=new Person<Introduction>(new Introduction("hansheng","nan",22));
            System.out.println(person1.getInfo());

     }
}
