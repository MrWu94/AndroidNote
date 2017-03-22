package com.hansheng.writejson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hansheng on 17-3-21.
 */

public class JsonArrayTest {
    static File file = new File("/home/hansheng/cover.json");

    public static void constructorTest() throws IOException {

        String jsonStr = "[{'name':'JTZen9','age':21}]";
        JSONArray strJson = new JSONArray(jsonStr);     // 传入字符串
        System.out.println("构造参数为String类：" + strJson);

        List<Object> list = new ArrayList<Object>();
        for (int i = 1; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "java程序设计 第" + i + "版");
            map.put("price", i * 20);
            list.add(map);
        }
        JSONArray mapJson = new JSONArray(list);        // 传入Collection类型
        System.out.println("构造参数为Collection类：" + mapJson);

        int[] numlist = new int[10];
        for (int i = 0; i < numlist.length; i++) {
            numlist[i] = i;
        }
        JSONArray arrayJson = new JSONArray(numlist);   // 传入Array类型，实例1
        System.out.println(arrayJson);

        Student[] student = {new Student(), new Student()};
        student[0].setAge(21);
        student[0].setName("JTZen9");
        student[0].setSex("male");
        student[1].setAge(21);
        student[1].setName("heiheihei");
        student[1].setSex("female");
        JSONArray beanJson = new JSONArray(student);    // 传入Array类型，实例2
        JSONTokener jsonTokener = new JSONTokener(new FileReader(new File("/home/hansheng/cover.json")));
        JSONArray jsonArray = new JSONArray(jsonTokener);//获取整个json文件的内容，因为最外层是数组，所以用JSONArray来构造
//        String str = jsonArray.toString();
//        JSONObject object = new JSONObject();
//        object.put("images", jsonArray);
//        writeToJson(object);
      jsonArray.put(beanJson);
       writeToJson(jsonArray);

        System.out.println("构造参数为Array类：" + beanJson);
    }

    public static void writeToJson(JSONArray object) throws IOException {

        char[] stack = new char[1024];
        int top = -1;

        String string = object.toString();

        StringBuffer sb = new StringBuffer();
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if ('{' == c || '[' == c) {
                stack[++top] = c;
                sb.append("\n" + charArray[i] + "\n");
                for (int j = 0; j <= top; j++) {
                    sb.append("\t");
                }
                continue;
            }
            if ((i + 1) <= (charArray.length - 1)) {
                char d = charArray[i + 1];
                if ('}' == d || ']' == d) {
                    top--;
                    sb.append(charArray[i] + "\n");
                    for (int j = 0; j <= top; j++) {
                        sb.append("\t");
                    }
                    continue;
                }
            }
            if (',' == c) {
                sb.append(charArray[i] + "");
                for (int j = 0; j <= top; j++) {
                    sb.append("");
                }
                continue;
            }
            sb.append(c);
        }
        Writer write = new FileWriter(file);
        write.write(sb.toString());
        write.flush();
        write.close();
    }

    public static void main(String[] args) throws IOException {
        constructorTest();
    }
}
