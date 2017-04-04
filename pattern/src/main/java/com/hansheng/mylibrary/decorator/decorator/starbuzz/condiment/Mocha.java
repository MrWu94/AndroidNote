package com.hansheng.mylibrary.decorator.decorator.starbuzz.condiment;

import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.Beverage;

public class Mocha extends CondimentDecorator {
	Beverage beverage;
 
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}
 
	public double cost() {
		return .20 + beverage.cost();
	}
}
