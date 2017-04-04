package com.hansheng.mylibrary.decorator.decorator.starbuzz;

import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.Beverage;
import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.DarkRoast;
import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.Espresso;
import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.HouseBlend;
import com.hansheng.mylibrary.decorator.decorator.starbuzz.condiment.Mocha;
import com.hansheng.mylibrary.decorator.decorator.starbuzz.condiment.Soy;
import com.hansheng.mylibrary.decorator.decorator.starbuzz.condiment.Whip;

public class StarbuzzCoffee {
 
	public static void main(String args[]) {
		Beverage beverage = new Espresso();
		System.out.println(beverage.getDescription() 
				+ " $" + beverage.cost());
 
		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		System.out.println(beverage2.getDescription() 
				+ " $" + beverage2.cost());
 
		Beverage beverage3 = new HouseBlend();
		beverage3 = new Soy(beverage3);
		beverage3 = new Mocha(beverage3);
		beverage3 = new Whip(beverage3);
		System.out.println(beverage3.getDescription() 
				+ " $" + beverage3.cost());
	}
}
