package com.hansheng.mylibrary.decorator;

public class Text{
    public static void main(String arg0){
        Beverage beverage=new House();
        beverage=new Soy(beverage);
        beverage=new Whip(beverage);
        beverage=new Mocha(beverage);
        System.out.println(beverage.getDescription()+",&"+beverage.cost());
    }
}
