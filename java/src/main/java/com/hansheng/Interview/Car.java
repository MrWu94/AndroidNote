package com.hansheng.Interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrwu on 2017/2/25.
 */



class Car extends Vehicle
{
    public static void main (String[] args)
    {
        new  Car(). run();



        List Listlist1 = new ArrayList();
        Listlist1.add(0);
        List Listlist2 = Listlist1;
        System.out.println(Listlist1.get(0) instanceof Integer);
        System.out.println(Listlist2.get(0) instanceof Integer);
    }
    private final void run()
    {
        System. out. println ("Car");
    }
}
class Vehicle
{
    private final void run()
    {
        System. out. println("Vehicle");
    }
}
