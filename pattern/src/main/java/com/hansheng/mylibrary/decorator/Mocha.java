package com.hansheng.mylibrary.decorator;




public class Mocha extends Condiment{
    Beverage beverage;
    public Mocha(Beverage beverage){
        this.beverage=beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription()+",mocha";
    }

    @Override
    public double cost() {
        return .20+beverage.cost();
    }
}