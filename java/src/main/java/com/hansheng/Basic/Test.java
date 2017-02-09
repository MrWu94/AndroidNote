package com.hansheng.Basic;

/**
 * Created by hansheng on 17-2-9.
 * 该题主要考察的是static属性和i++操作。
 * 匿名内部类，重写了equals
 * 因为i是static的，是类属性，所以不管有多少对象，都共用的一个变量。这里getNext()方法被调用了三次，所以进行了三次i++操作。
 * 但是由于getNext()操作的返回是：return i++; i++是先返回，后++，所以在println是，已经返回了i(此时i为3)，再进行自增的，所以这里结果为3
 */

public class Test {
    private static int i = 1;

    public int getNext() {
        return i++;
    }

    public static void main(String[] args) {
        Test test = new Test();
        Test testObject = new Test();
        test.getNext();
        testObject.getNext();
        System.out.println(testObject.getNext());
    }
}
