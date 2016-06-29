package com.hansheng.mylibrary.decorator;




public class Whip extends Condiment{
    Beverage beverage;
    public Whip(Beverage beverage){
        this.beverage=beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription()+",whip";
    }

    @Override
    public double cost() {
        return .40+beverage.cost();
    }
}