package com.hansheng.writejson;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hansheng on 17-3-21.
 */

public class JsonDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "attribute a");
        map.put("id", "dingdangmao");

//        people p1 = new people("zhangsan","310504","552339",259034);
//        people p2 = new people("zhangsi","52083","85640",42153);
//        people p3 = new people("zhangwu","515910","828669",362642);
//        people p4 = new people("zhangliu","315910","728669",562642);
//        people p5 = new people("zhangqi","415910","928669",162642);
//
//        ArrayList<String> m = new ArrayList<String>();
//        m.add(new String(p1.toString()));
//        m.add(new String(p2.toString()));
//        m.add(new String(p3.toString()));
//        m.add(new String(p4.toString()));
//        m.add(new String(p5.toString()));
//
//        JSONArray j = JSONArray.fromObject(m);
//        System.out.println(j);

        JSONArray jsonArray = JSONArray.fromObject(map);

        boolean[] boolArray = new boolean[]{true, false, true};
        JSONArray jsonArray1 = JSONArray.fromObject(boolArray);
        System.out.println(jsonArray1);
        System.out.println(jsonArray.toString());
    }
}
