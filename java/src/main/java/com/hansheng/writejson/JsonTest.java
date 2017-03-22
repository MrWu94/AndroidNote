package com.hansheng.writejson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hansheng on 17-3-21.
 */

public class JsonTest {
    public static void constructorTest() {

        String jsonStr = "{'name':'JTZen9','age':21}";
        JSONObject strJson = new JSONObject(jsonStr); // 传入字符串
        System.out.println("构造参数为String类：" + strJson);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("age", 21);
        map.put("sex", "male");
        map.put("name", "JTZen9");
        JSONObject mapJson = new JSONObject(map); // 传入Map类型
        System.out.println("构造参数为Map类：" + mapJson);

        Student student = new Student();
        student.setAge(21);
        student.setName("JTZen9");
        student.setSex("male");
        JSONObject beanJson = new JSONObject(student); // 传入Bean类型
        System.out.println("构造参数为Bean类：" + beanJson);
    }

    public static void putMethodTest() {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("bookName", "JTZen9");
        jsonObject1.put("age", 21);
        System.out.println(jsonObject1);

        JSONObject jsonObject2 = new JSONObject();
        List<Object> list = new ArrayList<Object>();
        for (int i = 1; i < 3; i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("title", "java程序设计 第" + i + "版");
            map.put("price", i*20);
            list.add(map);
        }
        jsonObject2.put("book", list);
        System.out.println(jsonObject2);

        Student student = new Student();
        student.setAge(21);
        student.setName("JTZen9");
        student.setSex("male");
        jsonObject2 = new JSONObject(student);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("people", jsonObject2);   //不可以直接传bean类对象put("people",student)
        System.out.println(jsonObject3);
    }

    public static void main(String[] args) throws Exception {
        constructorTest();
        System.out.println("---------------------------------------------------------");
        putMethodTest();
    }
}
