package com.hansheng.Finally;

/**
 * Created by mrwu on 2017/3/17.
 * 执行try块，执行到return语句时，先执行return的语句，--i，但是不返回到main方法，执行finally块，
 * 遇到finally块中的return语句，执行--i,并将值返回到main方法，这里就不会再回去返回try块中计算得到的值。
 * 结论：try-catch-finally都有return语句时，没有异常时，返回值是finally中的return返回的。
 */

public class NoException {
    public static void main(String... args) {

        System.out.println(NoException());
    }

    public static int NoException() {
        int i = 10;
        try {
            System.out.println("i in try block is：" + i);
            return --i;
        } catch (Exception e) {
            --i;
            System.out.println("i in catch - form try block is：" + i);
            return --i;
        } finally {
            System.out.println("i in finally - from try or catch block is：" + i);
            return --i;
        }
    }
}