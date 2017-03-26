package com.hansheng.mylibrary.decorator.strategy.abstr;

import com.hansheng.mylibrary.decorator.strategy.impl.FlyNoWay;
import com.hansheng.mylibrary.decorator.strategy.impl.Squeak;

public class RubberDuck extends Duck {
 
	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeak();
	}
 
	public void display() {
		System.out.println("I'm a rubber duckie");
	}
}
