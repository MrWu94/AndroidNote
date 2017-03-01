package com.hansheng.NeverObjects;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hansheng on 17-3-1.
 * 避免创建不必要的对象
 */

public class Person {

    private static  final   Date dNow = new Date( );

    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static{

        Calendar gtmCal=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gtmCal.set(1946,Calendar.JANUARY,1,0,0,0);
        BOOM_START=gtmCal.getTime();
        gtmCal.set(1965,Calendar.JANUARY,1,0,0,0);
        BOOM_END=gtmCal.getTime();
    }
    public static void main(String... args) {
        // Instantiate a Date object
//        Date dNow = new Date( );
//        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//        System.out.println("Current Date: " + ft.format(dNow));
        System.out.println(isBabyBoomer(dNow));
        System.out.println(isBabyBoomera(dNow));

    }

    //每次调用的时候，都会创建一个Calender，一个TimeZone和两个Date的实例
    public static  boolean isBabyBoomer(Date date){
        Calendar gtmCal=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gtmCal.set(1946,Calendar.JANUARY,1,0,0,0);
        Date boomStart=gtmCal.getTime();
        gtmCal.set(1965,Calendar.JANUARY,1,0,0,0);
        Date boomEnd=gtmCal.getTime();

        return  dNow.compareTo(boomStart)>=0&&dNow.compareTo(boomEnd)<0;

    }

    //正确使用
    public static boolean isBabyBoomera(Date date){
        return  dNow.compareTo(BOOM_START)>=0&&dNow.compareTo(BOOM_END)<0;
    }
}
