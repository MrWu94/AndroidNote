package com.hansheng.Basic;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by hansheng on 2016/9/25.
 */

public class ArrayTest {


    public static void main(String... args) {

        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(getCurrentTime());
        String str = "hello";
        str.length();
        int a[] = {1, 2, 3, 4, 5};
        List list = Arrays.asList(1, 2, 3, 4);
        System.out.println("list=" + list.get(0));


        Integer integer1 = 3;//Integer.valueOf(3);
        Integer integer2 = 3;

        if (integer1 == integer2)
            System.out.println("integer1 == integer2");
        else
            System.out.println("integer1 != integer2");

        Integer integer3 = 300;
        Integer integer4 = 300;

        if (integer3 == integer4)
            System.out.println("integer3 == integer4");
        else
            System.out.println("integer3 != integer4");


    }

    private static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH：mm：ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        return time;
    }
}
