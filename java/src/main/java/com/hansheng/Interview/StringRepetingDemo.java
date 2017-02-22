package com.hansheng.Interview;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hansheng on 17-2-22.
 */

public class StringRepetingDemo {
    public static void main(String... args) {
        stringRepeting("google");
    }

    public static void stringRepeting(String str) {
        if (str == null || str.length() < 1) {
            throw new IllegalArgumentException("Arg should not be null or empty");
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (!map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), i);
            } else {
                map.put(str.charAt(i), -2);
            }

        }

//        Map<Character, Integer> map = new LinkedHashMap<>();
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if (map.containsKey(c)) {
//                map.put(c, -2);
//            } else {
//                map.put(c, i);
//            }
//        }

//        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
//        int idx = Integer.MAX_VALUE;
//        for (Map.Entry<Character, Integer> extry : entrySet) {
//
//            if (extry.getValue() >=0 && extry.getValue() < idx) {
//                idx = extry.getValue();
//                System.out.println(extry.getKey());
//
//            }
//
//        }

        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        // 记录只出现一次的字符的索引
        int idx = Integer.MAX_VALUE;
        // 记录只出现一次的字符
        char result = '\0';
        // 找最小索引对应的字符
        for (Map.Entry<Character, Integer> entry : entrySet) {
            if (entry.getValue() >= 0 && entry.getValue() < idx) {
                idx = entry.getValue();
                result = entry.getKey();System.out.println(result);

            }
        }
//       return result;


    }
}
