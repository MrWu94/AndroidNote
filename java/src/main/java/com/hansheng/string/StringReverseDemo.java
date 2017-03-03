package com.hansheng.string;

/**
 * Created by hansheng on 17-3-3.
 */

public class StringReverseDemo {

    public String reverseString(String iniString) {
        if(iniString==null){
            return null;
        }

        // write code here
        char[] ch=iniString.toCharArray();
        return reverse(ch,0,iniString.length()-1);
    }

    public static String reverse(char[] ch,int start,int end){
        char temp;
        while(start<end){
            temp=ch[start];
            ch[start]=ch[end];
            ch[end]=temp;
            start++;
            end--;
        }
        return new String(ch);
    }
}
