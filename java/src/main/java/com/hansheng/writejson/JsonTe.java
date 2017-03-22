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

public class JsonTe {
    static File file = new File("/home/hansheng/cover.json");

    public static void main(String[] args) throws IOException {
        JSONObject jsonObject = new JSONObject();
        List<Object> list = new ArrayList<Object>();
        for (int i = 1; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "java程序设计 第" + i + "版");
            map.put("price", i * 20);
            list.add(map);
        }
        jsonObject.put("images", list);
//        JSONTokener jsonTokener = new JSONTokener(new FileReader(new File("/home/hansheng/cover.json")));
//        JSONObject jsonArray = new JSONObject(jsonTokener);//获取整个json文件的内容，因为最外层是数组，所以用JSONArray来构造
//        JSONArray array= (JSONArray) jsonArray.get("images");
//        JSONObject append=new JSONObject();
//        append.put("image","hansheng");
//        array.put(array.length(),append);
////        System.out.println(jsonArray.get("images"));
//        array.put(list);
//        writeToJson(array);

        writeToJson(jsonObject);

        System.out.println(jsonObject);

    }
    public static void writeToJson(JSONObject object) throws IOException {

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


}
