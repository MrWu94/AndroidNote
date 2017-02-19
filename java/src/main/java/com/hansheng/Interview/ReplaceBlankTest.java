package com.hansheng.Interview;

/**
 * Created by mrwu on 2017/2/19.
 * 请实现一个函数，把字符串中的每个空格替换成"%20"，例如“We are happy.”，则输出“We%20are%20happy.”。
 */

public class ReplaceBlankTest {

    public static void main(String... args) {
        char[] string = new char[50];
        string[0] = ' ';
        string[1] = 'e';
        string[2] = ' ';
        string[3] = ' ';
        string[4] = 'r';
        string[5] = 'e';
        string[6] = ' ';
        string[7] = ' ';
        string[8] = 'a';
        string[9] = ' ';
        string[10] = 'p';
        string[11] = ' ';
        int length=replaceBlank(string,12);
        System.out.println(new String(string,0,length));
    }

    public static int replaceBlank(char string[], int useLength) {
        if (string.length < useLength || string == null) {
            return -1;
        }
        int whiteCount = 0;
        for (int i = 0; i < useLength; i++) {
            if (string[i] ==' ') {
                whiteCount++;
            }
        }
        int targthLength = 0;
        targthLength = whiteCount * 2 + useLength;

       int tmp=targthLength;

        if(whiteCount==0){
            return -1;
        }
        if(targthLength>string.length){
            return -1;
        }

        useLength--;
        targthLength--;
        while (useLength>=0&&useLength<targthLength){

            if(string[useLength]==' '){
                string[targthLength--]='%';
                string[targthLength--]='2';
                string[targthLength--]='0';
            }
            else {
                string[targthLength--]=string[useLength];
            }
            useLength--;
        }
        return tmp;
    }
}
