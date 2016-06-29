package com.hansheng.mylibrary.decorator;




public class House extends Beverage {
    public House() {
        description = "House Blend coffee";
    }

    @Override
    public double cost() {
        return 0;
    }
}
