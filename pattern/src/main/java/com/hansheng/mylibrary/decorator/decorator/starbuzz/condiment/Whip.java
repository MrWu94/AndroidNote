package com.hansheng.mylibrary.decorator.decorator.starbuzz.condiment;

import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.Beverage;

public class Whip extends CondimentDecorator {
	Beverage beverage;
 
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}
 
	public double cost() {
		return .10 + beverage.cost();
	}
}
