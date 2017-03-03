package com.hansheng.string;

/**
 * Created by hansheng on 17-3-3.
 */

public class StringZipper {
    public static String zipString(String iniString) {
        // write code here
        char[] ch = iniString.toCharArray();
        int length = iniString.length();
        int j = 0;
        int i=0;
        int count = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (i < iniString.length()) {
            count = 1;
            j = i+1;
            while (j < iniString.length()) {
                if (ch[i] == ch[j]) {
                    count++;
                    j++;
                } else {
                    i=j;
                    j = iniString.length();

                }
            }
            stringBuffer.append(ch[i]).append(count);
        }
        if (stringBuffer.length() == iniString.length()) {
            return iniString;
        } else return stringBuffer.toString();


    }

    public static void main(String... args) {
        System.out.println(zipString("aabbddjj"));

    }
}
