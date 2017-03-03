package com.hansheng.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hansheng on 17-3-3.
 */

public class StringDifferent {

    public boolean checkDifferent(String iniString) {
        // write code here
        char[] ch = iniString.toCharArray();
        for (int i = 0; i < iniString.length(); i++) {
            Map<Character,Integer> map=new HashMap<>();
            if(map.containsKey(ch[i])){
                return false;
            }
            else{
                map.put(ch[i],1);
            }

        }
        return true;

    }

    public static void main(String... args) {
    }
}
