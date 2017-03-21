package com.hansheng.Algorithm;

/**
 * Created by hansheng on 17-3-21.
 * http://www.importnew.com/2329.html
 * 请写一段代码来计算给定文本内字符“A”的个数。分别用迭代和递归两种方式。
 */

public class StringSearch {


        public static int countA(String input) {
            if (input == null || input.length( ) == 0) {
                return 0;
            }

            int count = 0;
            for (int i = 0; i < input.length( ); i++) {
                if(input.substring(i, i+1).equals("A")){
                    count++;
                }
            }
            return count;
        }

        public static void main(String[ ] args) {
            System.out.println(countA("AAA rating"));     // Ans.3
        }

}
