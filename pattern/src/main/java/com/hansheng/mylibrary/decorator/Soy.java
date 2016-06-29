package com.hansheng.mylibrary.decorator;




public class Soy extends Condiment{
    Beverage beverage;
    public Soy(Beverage beverage){
        this.beverage=beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription()+",soy";
    }

    @Override
    public double cost() {
        return .60+beverage.cost();
    }
}