package com.hansheng.Algorithm;

/**
 * Created by mrwu on 2017/2/19.
 * <p>
 * 位运算符
 * Java定义了位运算符，应用于整数类型(int)，长整型(long)，短整型(short)，字符型(char)，和字节型(byte)等类型。
 * 位运算符作用在所有的位上，并且按位运算。假设a = 60，b = 13;它们的二进制格式表示将如下：
 * A = 0011 1100
 * B = 0000 1101
 * -----------------
 * A&b = 0000 1100
 * A | B = 0011 1101
 * A ^ B = 0011 0001
 * ~A= 1100 0011
 * <p>
 * << 	按位左移运算符。左操作数按位左移右操作数指定的位数。	A << 2得到240，即 1111 0000
 * >> 	按位右移运算符。左操作数按位右移右操作数指定的位数。	A >> 2得到15即 1111
 * >>> 	按位右移补零操作符。左操作数的值按右操作数指定的位数右移，移动得到的空位以零填充。A>>>2得到15即0000 1111
 */

public class BinaryDemo {

    public static void main(String... args){
        System.out.println(numberOfOne(16));
    }

    public static int  numberOfOne(int n){
        int result=0;
        for(int i=0;i<32;i++){
            result+=(n&1);
            n>>>=1;
        }
        return result;
    }

    /**
     * 请实现一个函数， 输入一个整数，输出该数二进制表示中1的个数。
     * 例如把9表示成二进制是1001 ，有2位是1. 因此如果输入9，该出2。
     * 【这种方法的效率更高】
     *
     * @param n 待的数字
     * @return 数字中二进制表表的1的数目
     */
    public static int numberOfOne2(int n) {
        // 记录数字中1的位数
        int result = 0;
        // 数字的二进制表示中有多少个1就进行多少次操作
        while (n != 0) {
            result++;
            // 从最右边的1开始，每一次操作都使n的最右的一个1变成了0，
            // 即使是符号位也会进行操作。
            n = (n - 1) & n;
        }
        // 返回求得的结果
        return result;
    }
}
