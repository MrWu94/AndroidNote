package com.hansheng.mylibrary.decorator.decorator.starbuzz.condiment;

import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.Beverage;

/**
 * 调料装饰者
 */

public class Milk extends CondimentDecorator {
	Beverage beverage;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	public double cost() {
		return .10 + beverage.cost();
	}
}
